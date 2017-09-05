package org.strangeforest.test.coherence;

import java.util.concurrent.*;

import org.littlegrid.*;
import org.littlegrid.impl.*;
import org.testng.annotations.*;

import com.google.common.base.*;
import com.tangosol.net.*;
import com.tangosol.net.cache.*;
import com.tangosol.util.*;
import com.tangosol.util.filter.*;

import static org.assertj.core.api.Assertions.*;

public class CoherenceMiscPT {

	private ClusterMemberGroup cluster;

	private static final int ITEM_COUNT     = 10000;

	private static final int WARM_UP_GET_COUNT = 10;
	private static final int GET_COUNT      = 100000;

	@BeforeClass
	public void startGrid() {
		cluster = ClusterMemberGroupUtils.newBuilder()
			.setClusterMemberGroupInstanceClassName(SimpleKeepAliveClusterMemberGroup.class.getName())
			.setStorageEnabledCount(2)
			.setExtendProxyCount(2)
			.setExtendPort(9099)
			.setCacheConfiguration("top-test-cache-config.xml")
			.setExtendProxyCacheConfiguration("top-test-cache-config.xml")
			.setClientCacheConfiguration("test-client-cache-config.xml")
			.buildAndConfigureForExtendClient();
//		DistributedCacheService service = (DistributedCacheService) CacheFactory.getCache("TestItem").getCacheService();
//		System.out.println("Partitions: " + service.getPartitionCount());
		createFixture("TestItem");
		createFixture("TestItem2");
		createFixture("TestItem3");
	}

	@AfterClass
	public void stopGrid() {
		ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(cluster);
	}

	@Test
	public void nearCachePresentGetById() throws InterruptedException {
		cacheGetById("nearCachePresentGetById", "TestItem", false, WARM_UP_GET_COUNT, GET_COUNT);
	}

	@Test
	public void nearCacheAllGetById() throws InterruptedException {
		cacheGetById("nearCacheAllGetById", "TestItem3", false, WARM_UP_GET_COUNT, GET_COUNT);
	}

	@Test(enabled = false)
	public void cacheGetById() throws InterruptedException {
		cacheGetById("cacheGetById", "TestItem2", false, WARM_UP_GET_COUNT/10, GET_COUNT/1000);
	}

	@Test(enabled = false)
	public void cqcNearCachePresentGetById() throws InterruptedException {
		cacheGetById("cqcNearCachePresentGetById", "TestItem", true, WARM_UP_GET_COUNT, GET_COUNT);
	}

	@Test(enabled = false)
	public void cqcNearCacheAllGetById() throws InterruptedException {
		cacheGetById("cqcNearCacheAllGetById", "TestItem3", true, WARM_UP_GET_COUNT, GET_COUNT);
	}

	@Test(enabled = false)
	public void cqcCacheGetById() throws InterruptedException {
		cacheGetById("cqcCacheGetById", "TestItem2", true, WARM_UP_GET_COUNT, GET_COUNT);
	}

	private void cacheGetById(String name, String cacheName, boolean cqc, int warmUpCount, int count) throws InterruptedException {
		System.out.println(name);
		NamedCache cache = CacheFactory.getCache(cacheName);

		if (cqc) {
//		   Filter filter = new EqualsFilter(TestItemPofSerializer.NAME_EXTRACTOR, "Pera");
//		   Filter filter2 = new EqualsFilter(TestItemPofSerializer.NAME_EXTRACTOR2, "Pera");
			ContinuousQueryCache cqCache = new ContinuousQueryCache(cache, AlwaysFilter.INSTANCE, DUMMY_LISTENER);
//			cqCache.setCacheValues(true);
			cache = cqCache;
		}

		testCacheGet(cache, warmUpCount, null);

		Thread.sleep(1000L);
		
		Stopwatch watch = Stopwatch.createStarted();

		testCacheGet(cache, count, null);

		System.out.println(watch.toString());
		long totalCount = ITEM_COUNT * count;
		System.out.println(watch.elapsed(TimeUnit.MILLISECONDS)/totalCount + "ms");
		System.out.println(watch.elapsed(TimeUnit.MICROSECONDS)/totalCount + "us");
		System.out.println(watch.elapsed(TimeUnit.NANOSECONDS)/totalCount + "ns");
	}

	private static void createFixture(String cacheName) {
		NamedCache cache = CacheFactory.getCache(cacheName);
		for (int i = 0; i < ITEM_COUNT; i++)
			cache.put(i, new TestItem(i, "Pera"));
	}

	private void testCacheGet(NamedCache cache, int count, Filter filter) {
		for (int j = 0; j < count; j++) {
			for (int i = 0; i < ITEM_COUNT; i++) {
				TestItem item = (TestItem)cache.get(i);
				assertThat(item).isNotNull();
//				TestItem item = (TestItem)(ccCache.getAll(singleton(i))).get(i);
//				if (filter != null) {
//					Iterator<Map.Entry> iter = ccCache.entrySet(new InKeySetFilter(filter, singleton(i))).iterator();
//					if (iter.hasNext())
//						iter.next().getValue();
//				}
			}
		}
	}

	private static class MyContinuousQueryCache extends ContinuousQueryCache {

		public MyContinuousQueryCache(NamedCache cache, Filter filter, boolean cacheValues, MapListener listener) {
			super(cache, filter, cacheValues, listener, null);
		}
	}

	private static final MapListener DUMMY_LISTENER = new MapListener() {
		@Override public void entryInserted(MapEvent mapEvent) {

		}

		@Override public void entryUpdated(MapEvent mapEvent) {

		}

		@Override public void entryDeleted(MapEvent mapEvent) {

		}
	};
}
