package org.strangeforest.test.coherence;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import org.littlegrid.*;
import org.littlegrid.impl.*;
import org.mockito.*;
import org.testng.annotations.*;

import com.google.common.base.*;
import com.tangosol.io.pof.*;
import com.tangosol.net.*;
import com.tangosol.net.cache.*;
import com.tangosol.util.*;

import static java.lang.String.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CoherenceListenerThrougputIT {

	private ClusterMemberGroup cluster;
	private List<Process> processes = new CopyOnWriteArrayList<>();

	private static final int STORAGE_NODES = 3;
	private static final int LISTENER_COUNT = 20;
	private static final long LOAD_SIZE = 100000;
	private static final int BATCH_SIZE = 10;
	private static final String TEXT = "Pera_Konjina,_Velikiii,_Najveciii,_Sa_Magarecim_Usima!!!_";

	@BeforeClass
	public void startGrid() throws IOException {
		cluster = ClusterMemberGroupUtils.newBuilder()
			.setClusterMemberGroupInstanceClassName(SimpleKeepAliveClusterMemberGroup.class.getName())
			.setStorageEnabledCount(STORAGE_NODES)
			.setCacheConfiguration("top-test-cache-config.xml")
			.buildAndConfigureForStorageDisabledClient();

		for (int i = 1; i <= LISTENER_COUNT; i++) {
			ProcessBuilder processBuilder = new ProcessBuilder(java(), "-cp", System.getProperty("java.class.path"), "org.strangeforest.test.coherence.MapListenerProcess", String.valueOf(i), String.valueOf(LOAD_SIZE));
			processBuilder.directory(new File(System.getProperty("user.dir")));
			processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
			processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
			Process process = processBuilder.start();
			processes.add(process);
		}
	}

	private String java() {
		String javaHome = System.getenv("JAVA_HOME");
		if (javaHome == null)
			return "java";
		else
			return javaHome + "/bin/java";
	}

	@AfterClass
	public void stopGrid() throws InterruptedException {
		for (Process process : processes) {
			int exitCode = process.waitFor();
			systemOutPrintln("Process terminalted with code: " + exitCode);
		}
		ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(cluster);
	}

	@Test
	public void showSize() throws IOException {
		ConfigurablePofContext configurablePofContext = new ConfigurablePofContext("test-pof-config.xml");
		int userTypeIdentifier = configurablePofContext.getUserTypeIdentifier(BigItem.class);
		assertThat(configurablePofContext.getClass(userTypeIdentifier)).isEqualTo(BigItem.class);

		Binary binary = ExternalizableHelper.toBinary(new BigItem(12345L, TEXT + "123456"), configurablePofContext);
		systemOutPrintln("BigItem size: " + binary.length());
	}

	@Test
	public void listenerThroughputTest() {
		NamedCache cache = CacheFactory.getCache("BigItem");
		Stopwatch stopwatch = Stopwatch.createStarted();
		for (long i = 0; i < LOAD_SIZE; i += BATCH_SIZE) {
			Map<Long, BigItem> batch = new HashMap<>();
			for (long j = 0; j < BATCH_SIZE; j++) {
				long k = i + j;
				batch.put(k, new BigItem(k, TEXT + k));
			}
			cache.putAll(batch);
		}
		systemOutPrintln(format("Load completed in %1$s (%2$.0f items/sec)", stopwatch.stop(), 1000.0*LOAD_SIZE/stopwatch.elapsed(TimeUnit.MILLISECONDS)));
	}

	private synchronized void systemOutPrintln(String s) {
		System.out.println(s);
	}
}
