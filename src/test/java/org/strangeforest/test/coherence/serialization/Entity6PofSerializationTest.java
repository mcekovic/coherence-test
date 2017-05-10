package org.strangeforest.test.coherence.serialization;


import org.junit.runner.*;
import org.junit.runners.*;

import static java.util.Arrays.*;

@RunWith(Parameterized.class)
public class Entity6PofSerializationTest extends BasePofSerializationTest {

	@Override protected String getPofConfig() {
		return "test6-pof-config.xml";
	}

	@Parameterized.Parameters(name = "Pof serialization of {0}")
	public static Iterable<Object[]> parameters() {
		return asList(new Object[][] {
			{Entity6.class, new Entity6(1L, "Pera", 0, "Konj")},
			{Entity6.class, new Entity6(2L, "Zika", 1, "Pomada")}
		});
	}
}
