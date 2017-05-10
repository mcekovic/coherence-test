package org.strangeforest.test.coherence.serialization;


import org.junit.runner.*;
import org.junit.runners.*;

import static java.util.Arrays.*;

@RunWith(Parameterized.class)
public class Entity4PofSerializationTest extends BasePofSerializationTest {

	@Override protected String getPofConfig() {
		return "test4-pof-config.xml";
	}

	@Parameterized.Parameters(name = "Pof serialization of {0}")
	public static Iterable<Object[]> parameters() {
		return asList(new Object[][] {
			{Entity4.class, new Entity4(1L, "Pera", "Pera Konj", "Sisar", "Konj")},
			{Entity4.class, new Entity4(2L, "Zika", "Zika Pomada", "Kozmetika", "Pomada")}
		});
	}
}
