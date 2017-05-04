package org.strangeforest.test.coherence;

import org.littlegrid.*;
import org.littlegrid.impl.*;
import org.testng.annotations.*;

import com.tangosol.net.*;

import static java.lang.Thread.*;

public class ItemAdderIT {

	private ClusterMemberGroup cluster;

	private static final long TIMEOUT = 200L;

	@BeforeClass
	public void startGrid() {
		cluster = ClusterMemberGroupUtils.newBuilder()
			.setClusterMemberGroupInstanceClassName(SimpleKeepAliveClusterMemberGroup.class.getName())
			.setStorageEnabledCount(0)
			.setCacheConfiguration("top-test-cache-config.xml")
			.buildAndConfigureForStorageDisabledClient();
	}

	@AfterClass
	public void stopGrid() {
		ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(cluster);
	}

	@Test
	public void addItems() {
		NamedCache cache = CacheFactory.getCache("TestItem");
		for (long i = 0; true; i++) {
			try {
				Thread.sleep(1000L);
				cache.put(i, new TestItem(i, "Pera"));
				System.out.println(i);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
