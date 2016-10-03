package org.strangeforest.test.coherence;

import com.tangosol.net.cache.*;

import static java.lang.String.*;

public class BigItemStore extends AbstractCacheStore {

	private int loadCount;
	private int storeCount;

	public BigItemStore() {
		System.out.println("Store is created: " + this);
	}

	@Override public BigItem load(Object key) {
		loadCount++;
		return new BigItem((Long)key, valueOf(key));
	}

	@Override public void store(Object key, Object value) {
		storeCount++;
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
