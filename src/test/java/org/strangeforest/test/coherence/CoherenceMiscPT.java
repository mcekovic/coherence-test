package org.strangeforest.test.coherence;

import java.util.*;
import java.util.regex.*;

import org.littlegrid.*;
import org.littlegrid.impl.*;
import org.testng.annotations.*;

import com.google.common.base.*;
import com.tangosol.net.*;
import com.tangosol.net.cache.*;
import com.tangosol.util.*;
import com.tangosol.util.filter.*;

import static java.util.Collections.*;

public class CoherenceMiscPT {

	private ClusterMemberGroup cluster;

	private static final int ITEM_COUNT = 1000;

	private static final int WARM_UP_GET_COUNT = 10;
	private static final int GET_COUNT = 1000;

	private static final long TIMEOUT = 200L;

	@BeforeClass
	public void startGrid() {
		cluster = ClusterMemberGroupUtils.newBuilder()
			.setClusterMemberGroupInstanceClassName(SimpleKeepAliveClusterMemberGroup.class.getName())
			.setStorageEnabledCount(2)
			.setCacheConfiguration("top-test-cache-config.xml")
			.setClientCacheConfiguration("test-client-cache-config.xml")
			.buildAndConfigureForStorageDisabledClient();
		DistributedCacheService service = (DistributedCacheService) CacheFactory.getCache("TestItem").getCacheService();
		System.out.println("Partitions: " + service.getPartitionCount());
	}

	@AfterClass
	public void stopGrid() {
		ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(cluster);
	}

	@Test
	public void queryContinuousQueryById() throws InterruptedException {
		NamedCache cache = CacheFactory.getCache("TestItem");
		for (int i = 0; i < ITEM_COUNT; i++)
			cache.put(i, new TestItem(i, "Pera"));

		Filter filter = new EqualsFilter(TestItemPofSerializer.NAME_EXTRACTOR, "Pera");
		Filter filter2 = new EqualsFilter(TestItemPofSerializer.NAME_EXTRACTOR2, "Pera");
		NamedCache testCache = new ContinuousQueryCache(cache, filter, DUMMY_LISTENER);
//		ccCache.setCacheValues(true);
		
		testCCQuery(cache, WARM_UP_GET_COUNT, null);

		Thread.sleep(1000L);
		
		Stopwatch watch = Stopwatch.createStarted();

		testCCQuery(cache, GET_COUNT, null);

		System.out.println(watch.toString());
	}

	private void testCCQuery(NamedCache ccCache, int count, Filter filter) {
		for (int j = 0; j < count; j++) {
			for (int i = 0; i < ITEM_COUNT; i++) {
				TestItem item = (TestItem)ccCache.get(i);
//				TestItem item = (TestItem)(ccCache.getAll(singleton(i))).get(i);
				if (filter != null) {
					Iterator<Map.Entry> iter = ccCache.entrySet(new InKeySetFilter(filter, singleton(i))).iterator();
					if (iter.hasNext())
						iter.next().getValue();
				}
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
