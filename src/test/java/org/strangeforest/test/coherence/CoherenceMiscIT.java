package org.strangeforest.test.coherence;

import org.littlegrid.*;
import org.littlegrid.impl.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.testng.*;
import org.testng.annotations.*;

import com.tangosol.net.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CoherenceMiscIT {

	private ClusterMemberGroup cluster;

	@BeforeClass
	public void startGrid() {
		cluster = ClusterMemberGroupUtils.newBuilder()
			.setClusterMemberGroupInstanceClassName(SimpleKeepAliveClusterMemberGroup.class.getName())
			.setStorageEnabledCount(2)
			.setCacheConfiguration("test-cache-config.xml")
			.setAdditionalSystemProperty("tangosol.xml.validation.disable", true)
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
		cache.put(1, new TestItem(1, "Pera"));

		TestItem item1 = (TestItem)cache.get(1);
		item1.setName("Mika");

		TestItem item2 = (TestItem)cache.get(1);

		assertThat(item2.getName()).isEqualTo("Pera");
	}
}
