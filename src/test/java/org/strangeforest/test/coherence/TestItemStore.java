package org.strangeforest.test.coherence;

import com.tangosol.net.cache.*;

import static java.lang.String.*;

public class TestItemStore extends AbstractCacheStore {

	private int loadCount;
	private int storeCount;

	public TestItemStore() {
		System.out.println("Store is created: " + this);
	}

	@Override public TestItem load(Object key) {
		loadCount++;
		return new TestItem((Long)key, valueOf(key));
	}

	@Override public void store(Object key, Object value) {
		storeCount++;
//		System.out.println(format("Storing [%1$s:%2$s]", key, value));
	}

	public int getLoadCount() {
		return loadCount;
	}

	public int getStoreCount() {
		return storeCount;
	}

	public void resetCounters() {
		loadCount = 0;
		storeCount = 0;
	}
}
