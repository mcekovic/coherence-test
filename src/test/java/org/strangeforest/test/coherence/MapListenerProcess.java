package org.strangeforest.test.coherence;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import org.littlegrid.*;
import org.littlegrid.impl.*;

import com.google.common.base.*;
import com.tangosol.net.*;
import com.tangosol.util.*;

public class MapListenerProcess {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Listener process started: " + Arrays.toString(args));
		try {
			int id = Integer.parseInt(args[0]);
			long count = Long.parseLong(args[1]);
			System.out.printf("Listener process %1$d started, expecting %1$d inserts.%n", id, count);

			ClusterMemberGroup cluster = ClusterMemberGroupUtils.newBuilder()
				                             .setClusterMemberGroupInstanceClassName(SimpleKeepAliveClusterMemberGroup.class.getName())
				                             .setStorageEnabledCount(0)
				                             .setCacheConfiguration("top-test-cache-config.xml")
				                             .buildAndConfigureForStorageDisabledClient();

			NamedCache cache = CacheFactory.getCache("BigItem");
			SpyingMapListener listener = new SpyingMapListener(id, count);
			Stopwatch stopwatch = Stopwatch.createStarted();
			cache.addMapListener(listener);
			System.out.printf("Listener %1$d registered.%n", id);

			listener.waitFor();

			System.out.printf("Listener %1$d completed in %2$s (%3$.0f inserts/sec).%n", id, stopwatch.stop(), 1000.0*listener.getInserts()/stopwatch.elapsed(TimeUnit.MILLISECONDS));

			ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(cluster);
		}
		catch (Throwable th) {
			th.printStackTrace();
		}
	}

	private static class SpyingMapListener extends AbstractMapListener {

		private final int id;
		private final long count;
		private final Semaphore semaphore;
		private AtomicLong inserts = new AtomicLong();
		private AtomicLong updates = new AtomicLong();
		private AtomicLong deletes = new AtomicLong();

		public SpyingMapListener(int id, long count) {
			this.id = id;
			this.count = count;
			semaphore = new Semaphore(0);
		}

		public long getInserts() {
			return inserts.get();
		}

		public long getUpdates() {
			return updates.get();
		}

		public long getDeletes() {
			return deletes.get();
		}

		@Override public void entryInserted(MapEvent evt) {
			long count = inserts.incrementAndGet();
			if (count % 1000 == 0)
				System.out.printf("Inserts[%1$d]: %2$d%n", id, count);
			if (count >= this.count || (Long)evt.getKey() == this.count - 1)
				semaphore.release();
		}

		@Override public void entryUpdated(MapEvent evt) {
			long count = updates.incrementAndGet();
			if (count % 1000 == 0)
				System.out.printf("Updates[%1$d]: %2$d%n", id, count);
		}

		@Override public void entryDeleted(MapEvent evt) {
			long count = deletes.incrementAndGet();
			if (count % 1000 == 0)
				System.out.printf("Deletes[%1$d]: %2$d%n", id, count);
		}

		public void waitFor() throws InterruptedException {
			semaphore.acquire();
		}
	}
}
