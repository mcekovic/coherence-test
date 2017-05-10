package org.strangeforest.test.coherence.serialization;

import com.tangosol.io.pof.*;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class PofTestUtil {

	public static ConfigurablePofContext createPofContext(String pofConfig, Class<?> entityClass) {
		ConfigurablePofContext pofContext = new ConfigurablePofContext(pofConfig);
		assertThat(pofContext.isUserType(entityClass)).describedAs("Pof serializer for type %s is not available.", entityClass).isTrue();

		int userTypeIdentifier = pofContext.getUserTypeIdentifier(entityClass);

		assertThat(pofContext.getClass(userTypeIdentifier)).describedAs("Missing serialization for class %s." + entityClass).isEqualTo(entityClass);
		return pofContext;
	}
}
