package org.strangeforest.test.coherence.serialization;


import org.junit.runner.*;
import org.junit.runners.*;

import static java.util.Arrays.*;

@RunWith(Parameterized.class)
public class Entity7PofSerializationTest extends BasePofSerializationTest {

	@Override protected String getPofConfig() {
		return "test7-pof-config.xml";
	}

	@Parameterized.Parameters(name = "Pof serialization of {0}")
	public static Iterable<Object[]> parameters() {
		return asList(new Object[][] {
			{Entity7.class, new Entity7(1L, "Pera", "Pera Konj", 0, "Konj")},
			{Entity7.class, new Entity7(2L, "Zika", "Zika Pomada", 1, "Pomada")}
		});
	}
}
