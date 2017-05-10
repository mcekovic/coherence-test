package org.strangeforest.test.coherence.serialization;


import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;

import com.tangosol.io.pof.*;
import com.tangosol.util.*;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class Entity1PofSerializationTest extends BasePofSerializationTest {

	@Override protected String getPofConfig() {
		return "test1-pof-config.xml";
	}

	@Parameterized.Parameters(name = "Pof serialization of {0}")
	public static Iterable<Object[]> parameters() {
		return asList(new Object[][] {
			{Entity1.class, new Entity1(1L, "Pera")},
			{Entity1.class, new Entity1(2L, "Zika")}
		});
	}
}
