package org.strangeforest.test.coherence.serialization;


import org.junit.runner.*;
import org.junit.runners.*;

import static java.util.Arrays.*;

@RunWith(Parameterized.class)
public class Entity6_7AddEvolvableFieldPofSerializationEvolutionTest extends BasePofSerializationEvolutionTest {

	@Override protected String getOldPofConfig() {
		return "test6-pof-config.xml";
	}

	@Override protected String getNewPofConfig() {
		return "test7-pof-config.xml";
	}

	@Override protected String[] getIgnoredFields() {
		return new String[] {"description", "m_nDataVersion", "m_binFuture"};
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
			{Entity6.class, Entity7.class, new Entity6(1L, "Pera", 1, "Konj"), new Entity7(1L, "Pera", "Pera Konj", 1, "Konj")},
			{Entity6.class, Entity7.class, new Entity6(2L, "Zika", 2, "Pomada"), new Entity7(2L, "Zika", "Zika Pomada", 2, "Pomada")}
		});
	}
}
