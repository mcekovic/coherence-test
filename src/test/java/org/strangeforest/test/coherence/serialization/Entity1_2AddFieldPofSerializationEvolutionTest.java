package org.strangeforest.test.coherence.serialization;


import org.junit.runner.*;
import org.junit.runners.*;

import static java.util.Arrays.*;

@RunWith(Parameterized.class)
public class Entity1_2AddFieldPofSerializationEvolutionTest extends BasePofSerializationEvolutionTest {

	@Override protected String getOldPofConfig() {
		return "test1-pof-config.xml";
	}

	@Override protected String getNewPofConfig() {
		return "test2-pof-config.xml";
	}

	@Override protected String[] getIgnoredFields() {
		return new String[] {"description"};
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
			{Entity1.class, Entity2.class, new Entity1(1L, "Pera"), new Entity2(1L, "Pera", "Pera Konj")},
			{Entity1.class, Entity2.class, new Entity1(2L, "Zika"), new Entity2(2L, "Zika", "Zika Pomada")}
		});
	}
}
