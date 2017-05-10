package org.strangeforest.test.coherence.serialization;


import org.junit.runner.*;
import org.junit.runners.*;

import static java.util.Arrays.*;

@RunWith(Parameterized.class)
public class Entity2PofSerializationTest extends BasePofSerializationTest {

	@Override protected String getPofConfig() {
		return "test2-pof-config.xml";
	}

	@Parameterized.Parameters(name = "Pof serialization of {0}")
	public static Iterable<Object[]> parameters() {
		return asList(new Object[][] {
			{Entity2.class, new Entity2(1L, "Pera", "Pera Konj")},
			{Entity2.class, new Entity2(2L, "Zika", "Zika Pomada")}
		});
	}
}
