package org.strangeforest.test.coherence.serialization;


import org.junit.runner.*;
import org.junit.runners.*;

import static java.util.Arrays.*;

@RunWith(Parameterized.class)
public class Entity3PofSerializationTest extends BasePofSerializationTest {

	@Override protected String getPofConfig() {
		return "test3-pof-config.xml";
	}

	@Parameterized.Parameters(name = "Pof serialization of {0}")
	public static Iterable<Object[]> parameters() {
		return asList(new Object[][] {
			{Entity3.class, new Entity3(1L, "Pera", "Pera Konj", "Konj")},
			{Entity3.class, new Entity3(2L, "Zika", "Zika Pomada", "Pomada")}
		});
	}
}
