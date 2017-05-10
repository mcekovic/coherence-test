package org.strangeforest.test.coherence.serialization;


import org.junit.runner.*;
import org.junit.runners.*;

import static java.util.Arrays.*;

@RunWith(Parameterized.class)
public class Entity3_4AddFieldInMiddlePofSerializationEvolutionTest extends BasePofSerializationEvolutionTest {

	@Override protected String getOldPofConfig() {
		return "test3-pof-config.xml";
	}

	@Override protected String getNewPofConfig() {
		return "test4-pof-config.xml";
	}

	@Override protected String[] getIgnoredFields() {
		return new String[] {"category"};
	}

	@Override protected boolean isSameBinaryOldNewOld() {
		return true;
	}

	@Override protected boolean isSameBinaryNewOldNew() {
		return false;
	}

	@Parameterized.Parameters(name = "Pof serialization of {0} to {1}")
	public static Iterable<Object[]> parameters() {
		return asList(new Object[][] {
			{Entity3.class, Entity4.class, new Entity3(1L, "Pera", "Pera Konj", "Konj"), new Entity4(1L, "Pera", "Pera Konj", "Sisar", "Konj")},
			{Entity3.class, Entity4.class, new Entity3(2L, "Zika", "Zika Pomada", "Pomada"), new Entity4(2L, "Zika", "Zika Pomada", "Kozmetika", "Pomada")}
		});
	}
}
