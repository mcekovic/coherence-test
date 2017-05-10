package org.strangeforest.test.coherence.serialization;


import org.assertj.core.api.*;
import org.junit.runner.*;
import org.junit.runners.*;

import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(Parameterized.class)
public class Entity4_5ChangeFieldTypePofSerializationEvolutionTest extends BasePofSerializationEvolutionTest {

	@Override protected String getOldPofConfig() {
		return "test4-pof-config.xml";
	}

	@Override protected String getNewPofConfig() {
		return "test5-pof-config.xml";
	}

	@Override protected String[] getIgnoredFields() {
		return new String[] {"category", "m_nDataVersion", "m_binFuture"};
	}

	@Override protected boolean isSameBinaryOldNewOld() {
		return false;
	}

	@Override protected boolean isSameBinaryNewOldNew() {
		return false;
	}

	@Override protected void assertEqual(Object oldEntity, Object newEntity) {
		Entity4 oldE = (Entity4)oldEntity;
		Entity5 newE = (Entity5)newEntity;
		assertThat(newE.getCategory()).isEqualTo(Integer.parseInt(oldE.getCategory()));
	}

	@Parameterized.Parameters(name = "Pof serialization of {0} to {1}")
	public static Iterable<Object[]> parameters() {
		return asList(new Object[][] {
			{Entity4.class, Entity5.class, new Entity4(1L, "Pera", "Pera Konj", "1", "Konj"), new Entity5(1L, "Pera", "Pera Konj", 1, "Konj")},
			{Entity4.class, Entity5.class, new Entity4(2L, "Zika", "Zika Pomada", "2", "Pomada"), new Entity5(2L, "Zika", "Zika Pomada", 2, "Pomada")}
		});
	}
}
