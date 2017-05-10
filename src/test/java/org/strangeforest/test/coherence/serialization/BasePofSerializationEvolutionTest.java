package org.strangeforest.test.coherence.serialization;


import org.junit.*;
import org.junit.runners.*;

import com.tangosol.io.pof.*;
import com.tangosol.util.*;

import static org.assertj.core.api.Assertions.*;

public abstract class BasePofSerializationEvolutionTest {

	@Parameterized.Parameter(0) public Class<?> oldEntityClass;
	@Parameterized.Parameter(1) public Class<?> newEntityClass;
	@Parameterized.Parameter(2) public Object oldOriginal;
	@Parameterized.Parameter(3) public Object newOriginal;

	protected abstract String getOldPofConfig();
	protected abstract String getNewPofConfig();
	protected abstract String[] getIgnoredFields();
	protected abstract boolean isSameBinaryOldNewOld();
	protected abstract boolean isSameBinaryNewOldNew();
	protected void assertEqual(Object oldEntity, Object newEntity) {};

	@Test
	public void pofSerialize1DeserializeTo2() {
		ConfigurablePofContext oldPofContext = PofTestUtil.createPofContext(getOldPofConfig(), oldEntityClass);
		ConfigurablePofContext newPofContext = PofTestUtil.createPofContext(getNewPofConfig(), newEntityClass);

		Binary oldBinary = ExternalizableHelper.toBinary(oldOriginal, oldPofContext);
		traceBinary(oldBinary);
		Object newDeserialized = ExternalizableHelper.fromBinary(oldBinary, newPofContext);
		Binary newDeserializedThanSerializedBinary = ExternalizableHelper.toBinary(newDeserialized, newPofContext);
		traceBinary(newDeserializedThanSerializedBinary);

		assertThat(newDeserialized).describedAs("Deserialized instance not same as original.").isEqualToIgnoringGivenFields(oldOriginal, getIgnoredFields());
		assertEqual(oldOriginal, newDeserialized);
		if (isSameBinaryOldNewOld())
			assertThat(oldBinary).describedAs("Serialized binaries mismatch.").isEqualTo(newDeserializedThanSerializedBinary);
		else
			assertThat(oldBinary).describedAs("Serialized binaries not mismatch.").isNotEqualTo(newDeserializedThanSerializedBinary);
	}

	@Test
	public void pofSerialize2DeserializeTo1() {
		ConfigurablePofContext oldPofContext = PofTestUtil.createPofContext(getOldPofConfig(), oldEntityClass);
		ConfigurablePofContext newPofContext2 = PofTestUtil.createPofContext(getNewPofConfig(), newEntityClass);

		Binary newBinary = ExternalizableHelper.toBinary(newOriginal, newPofContext2);
		traceBinary(newBinary);
		Object oldDeserialized = ExternalizableHelper.fromBinary(newBinary, oldPofContext);
		Binary oldDeserializedThanSerializedBinary = ExternalizableHelper.toBinary(oldDeserialized, oldPofContext);
		traceBinary(oldDeserializedThanSerializedBinary);

		assertThat(oldDeserialized).describedAs("Deserialized instance not same as original.").isEqualToIgnoringGivenFields(newOriginal, getIgnoredFields());
		if (isSameBinaryNewOldNew())
			assertThat(newBinary).describedAs("Serialized binaries mismatch.").isEqualTo(oldDeserializedThanSerializedBinary);
		else
			assertThat(newBinary).describedAs("Serialized binaries not mismatch.").isNotEqualTo(oldDeserializedThanSerializedBinary);
	}

	public static void traceBinary(Binary binary1) {
		System.out.println(binary1.length() + ": " + binary1.toString());
	}
}
