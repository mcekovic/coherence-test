package org.strangeforest.test.coherence.serialization;


import org.junit.*;
import org.junit.runners.*;

import com.tangosol.io.pof.*;
import com.tangosol.util.*;

import static org.assertj.core.api.Assertions.*;

public abstract class BasePofSerializationTest {

	@Parameterized.Parameter(0) public Class<?> entityClass;
	@Parameterized.Parameter(1) public Object original;

	protected abstract String getPofConfig();

	@Test
	public void pofSerializeDeserialize() {
		ConfigurablePofContext pofContext = PofTestUtil.createPofContext(getPofConfig(), entityClass);

		Binary binary = ExternalizableHelper.toBinary(original, pofContext);
		Object deserialized = ExternalizableHelper.fromBinary(binary, pofContext);
		Binary deserializedThanSerializedBinary = ExternalizableHelper.toBinary(deserialized, pofContext);

		assertThat(deserialized).describedAs("Deserialized instance not same as original.").isEqualToComparingFieldByFieldRecursively(original);
		assertThat(binary).describedAs("Serialized binaries mismatch.").isEqualTo(deserializedThanSerializedBinary);
	}

}
