package org.strangeforest.test.coherence.serialization;


import org.junit.runner.*;
import org.junit.runners.*;

import static java.util.Arrays.*;

@RunWith(Parameterized.class)
public class Entity2_3AddFieldNewVersionPofSerializationEvolutionTest extends BasePofSerializationEvolutionTest {

	@Override protected String getOldPofConfig() {
		return "test2-pof-config.xml";
	}

	@Override protected String getNewPofConfig() {
		return "test3-pof-config.xml";
	}

	@Override protected String[] getIgnoredFields() {
		return new String[] {"type"};
	}

	@Override protected boolean isSameBinaryOldNewOld() {
		return false;
	}

	@Override protected boolean isSameBinaryNewOldNew() {
		return false;
	}

	@Parameterized.Parameters(name = "Pof serialization of {0} to {1}")
	public static Iterable<Object[]> parameters() {
		return asList(new Object[][] {
			{Entity2.class, Entity3.class, new Entity2(1L, "Pera", "Pera Konj"), new Entity3(1L, "Pera", "Pera Konj", "Konj")},
			{Entity2.class, Entity3.class, new Entity2(2L, "Zika", "Zika Pomada"), new Entity3(2L, "Zika", "Zika Pomada", "Pomada")}
		});
	}
}
