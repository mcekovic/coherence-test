package org.strangeforest.test.coherence.serialization;


import org.junit.runner.*;
import org.junit.runners.*;

import static java.util.Arrays.*;

@RunWith(Parameterized.class)
public class Entity5PofSerializationTest extends BasePofSerializationTest {

	@Override protected String getPofConfig() {
		return "test5-pof-config.xml";
	}

	@Parameterized.Parameters(name = "Pof serialization of {0}")
	public static Iterable<Object[]> parameters() {
		return asList(new Object[][] {
			{Entity5.class, new Entity5(1L, "Pera", "Pera Konj", 0, "Konj")},
			{Entity5.class, new Entity5(2L, "Zika", "Zika Pomada", 1, "Pomada")}
		});
	}
}
