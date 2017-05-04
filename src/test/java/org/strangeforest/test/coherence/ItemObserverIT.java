package org.strangeforest.test.coherence;

import org.littlegrid.*;
import org.littlegrid.impl.*;
import org.testng.annotations.*;

import com.tangosol.coherence.component.util.daemon.queueProcessor.service.grid.*;
import com.tangosol.net.*;
import com.tangosol.net.Service;
import com.tangosol.net.cache.*;
import com.tangosol.util.*;

public class ItemObserverIT {

	private ClusterMemberGroup cluster;

	private static final long TIMEOUT = 200L;

	@BeforeClass
	public void startGrid() {
		cluster = ClusterMemberGroupUtils.newBuilder()
			.setClusterMemberGroupInstanceClassName(SimpleKeepAliveClusterMemberGroup.class.getName())
			.setStorageEnabledCount(0)
			.setCacheConfiguration("top-test-cache-config.xml")
			.buildAndConfigureForStorageDisabledClient();
		Service service = CacheFactory.getCache("TestItem").getCacheService();
		service.addMemberListener(new MemberListener() {
			@Override public void memberJoined(MemberEvent memberEvent) {
				System.out.println(memberEvent);
			}
			@Override public void memberLeaving(MemberEvent memberEvent) {
				System.out.println(memberEvent);
			}
			@Override public void memberLeft(MemberEvent memberEvent) {
				System.out.println(memberEvent);
			}
		});
	}

	@AfterClass
	public void stopGrid() {
		ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(cluster);
	}

	@Test
	public void observeItems() {
		NamedCache cache = CacheFactory.getCache("TestItem");
		while (true) {
			try {
				Thread.sleep(1000L);
				System.out.println(cache.size());
				System.out.println(cache.values());
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Test
	public void observeItemsWithCQ() {
		NamedCache cache = CacheFactory.getCache("TestItem");
		ContinuousQueryCache ccCache = new ContinuousQueryCache(cache, new NullFilter(), new MapListener() {
			@Override public void entryInserted(MapEvent mapEvent) {
				System.out.println("Inserted: " + mapEvent);
			}
			@Override public void entryUpdated(MapEvent mapEvent) {
				System.out.println("Updated: " + mapEvent);
			}
			@Override public void entryDeleted(MapEvent mapEvent) {
				System.out.println("Deleted: " + mapEvent);
			}
		});
		while (true) {
			try {
				Thread.sleep(1000L);
				System.out.println(ccCache.size());
				System.out.println(ccCache.values());
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
