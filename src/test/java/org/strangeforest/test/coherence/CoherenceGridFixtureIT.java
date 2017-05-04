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

public class CoherenceGridFixtureIT {

	private ClusterMemberGroup cluster;

	private static final long TIMEOUT = 200L;

	@BeforeClass
	public void startGrid() {
		cluster = ClusterMemberGroupUtils.newBuilder()
			.setClusterMemberGroupInstanceClassName(SimpleKeepAliveClusterMemberGroup.class.getName())
			.setStorageEnabledCount(2)
			.setCacheConfiguration("top-test-cache-config.xml")
			.buildAndConfigureForStorageDisabledClient();
		DistributedCacheService service = (DistributedCacheService) CacheFactory.getCache("TestItem").getCacheService();
		System.out.println("Partitions: " + service.getPartitionCount());
	}

	@AfterClass
	public void stopGrid() {
		ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(cluster);
	}

	@Test
	public void countItems() throws InterruptedException {
		NamedCache cache = CacheFactory.getCache("TestItem");
		while (true) {
			Thread.sleep(1000L);
			System.out.println(cache.size());
			System.out.println(cache.values());
		}
	}
}
