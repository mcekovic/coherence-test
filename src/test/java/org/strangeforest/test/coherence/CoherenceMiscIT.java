package org.strangeforest.test.coherence;

import java.util.concurrent.*;

import org.littlegrid.*;
import org.littlegrid.impl.*;
import org.mockito.*;
import org.testng.annotations.*;

import com.tangosol.net.*;
import com.tangosol.net.cache.*;
import com.tangosol.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CoherenceMiscIT {

	private ClusterMemberGroup cluster;

	private static final long TIMEOUT = 200L;

	@BeforeClass
	public void startGrid() {
		cluster = ClusterMemberGroupUtils.newBuilder()
			.setClusterMemberGroupInstanceClassName(SimpleKeepAliveClusterMemberGroup.class.getName())
			.setStorageEnabledCount(2)
			.setCacheConfiguration("top-test-cache-config.xml")
			.buildAndConfigureForStorageDisabledClient();
	}

	@AfterClass
	public void stopGrid() {
		ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(cluster);
	}

	@Test
	public void differentInstancesAreRetrievedForTheSameKey() {
		NamedCache cache = CacheFactory.getCache("TestItem");
		cache.put(1, new TestItem(1, "Pera"));

		Object item1 = cache.get(1);
		Object item2 = cache.get(1);

		assertThat(item1).isNotSameAs(item2);
		assertThat(item1).isEqualToComparingFieldByField(item2);
	}

	@Test
	public void changeOnObjectIsNotPropagated() {
		NamedCache cache = CacheFactory.getCache("TestItem");
		cache.put(2, new TestItem(2, "Pera2"));

		TestItem item1 = (TestItem)cache.get(2);
		item1.setName("Mika");

		TestItem item2 = (TestItem)cache.get(2);

		assertThat(item2.getName()).isEqualTo("Pera2");
	}

	@Test
	public void cacheInstancesAreTheSame() {
		NamedCache cache1 = CacheFactory.getCache("TestItem");
		NamedCache cache2 = CacheFactory.getCache("TestItem");

		assertThat(cache1.getCacheName()).isEqualTo(cache2.getCacheName());
		assertThat(cache1).isEqualTo(cache2);
		assertThat(cache1).isSameAs(cache2);
	}

	@Test
	public void whenMapListenerIsAddedNothingHappens() throws InterruptedException {
		NamedCache cache = CacheFactory.getCache("TestItem");
		cache.put(3,  new TestItem(3, "Pera3"));

		Semaphore semaphore = new Semaphore(0);
		MapListener listener = new ReleasingMapListener(semaphore);
		try {
			cache.addMapListener(listener);

			assertThat(semaphore.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS)).isFalse();
		}
		finally {
			cache.removeMapListener(listener);
		}
	}

	@Test
	public void whenMapListenerIsAddedNewItemsAreNotified() throws InterruptedException {
		NamedCache cache = CacheFactory.getCache("TestItem");

		Semaphore semaphore = new Semaphore(0);
		MapListener listener = spy(new ReleasingMapListener(semaphore));
		try {
			cache.addMapListener(listener);
			cache.put(4,  new TestItem(4, "Pera4"));

			assertThat(semaphore.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS)).isTrue();
			ArgumentCaptor<MapEvent> mapEventCaptor = ArgumentCaptor.forClass(MapEvent.class);
			verify(listener).entryInserted(mapEventCaptor.capture());
			verifyZeroInteractions(listener);
			MapEvent mapEvent = mapEventCaptor.getValue();
			assertThat(mapEvent).isNotNull();
			assertThat(mapEvent.getKey()).isEqualTo(4);
			assertThat(((TestItem)mapEvent.getNewValue()).getName()).isEqualTo("Pera4");
			assertThat(mapEvent.getOldValue()).isNull();
		}
		finally {
			cache.removeMapListener(listener);
		}
	}

	@Test
	public void whenNewItemsAreAddedContinuousQueryIsNotified() throws InterruptedException {
		NamedCache cache = CacheFactory.getCache("TestItem2");
		cache.put(5,  new TestItem(5, "Pera5"));

		Semaphore semaphore = new Semaphore(0);
		MapListener listener = spy(new ReleasingMapListener(semaphore));
		ContinuousQueryCache ccCache = new ContinuousQueryCache(cache, new NullFilter(), listener);
		cache.put(6,  new TestItem(6, "Pera6"));

		assertThat(semaphore.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS)).isTrue();

		ArgumentCaptor<MapEvent> mapEventCaptor = ArgumentCaptor.forClass(MapEvent.class);
		verify(listener, times(2)).entryInserted(mapEventCaptor.capture());
		MapEvent mapEvent = mapEventCaptor.getValue();
		assertThat(mapEvent).isNotNull();
		assertThat(mapEvent.getKey()).isEqualTo(6);
		assertThat(((TestItem)mapEvent.getNewValue()).getName()).isEqualTo("Pera6");
		assertThat(mapEvent.getOldValue()).isNull();
	}

	private static class ReleasingMapListener implements MapListener {

		private final Semaphore semaphore;

		public ReleasingMapListener(Semaphore semaphore) {
			this.semaphore = semaphore;
		}

		@Override public void entryInserted(MapEvent mapEvent) {
			semaphore.release(1);
		}

		@Override public void entryUpdated(MapEvent mapEvent) {
			semaphore.release(1);
		}

		@Override public void entryDeleted(MapEvent mapEvent) {
			semaphore.release(1);
		}
	}
}
