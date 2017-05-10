package org.strangeforest.test.coherence.serialization;


import org.junit.runner.*;
import org.junit.runners.*;

import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(Parameterized.class)
public class Entity5_6RemoveFieldPofSerializationEvolutionTest extends BasePofSerializationEvolutionTest {

	@Override protected String getOldPofConfig() {
		return "test5-pof-config.xml";
	}

	@Override protected String getNewPofConfig() {
		return "test6-pof-config.xml";
	}

	@Override protected String[] getIgnoredFields() {
		return new String[] {"description", "m_nDataVersion", "m_binFuture"};
	}

	@Override protected boolean isSameBinaryOldNewOld() {
		return false;
	}

	@Override protected boolean isSameBinaryNewOldNew() {
		return true;
	}

	@Parameterized.Parameters(name = "Pof serialization of {0} to {1}")
	public static Iterable<Object[]> parameters() {
		return asList(new Object[][] {
			{Entity5.class, Entity6.class, new Entity5(1L, "Pera", "Pera Konj", 1, "Konj"), new Entity6(1L, "Pera", 1, "Konj")},
			{Entity5.class, Entity6.class, new Entity5(2L, "Zika", "Zika Pomada", 2, "Pomada"), new Entity6(2L, "Zika", 2, "Pomada")}
		});
	}
}
