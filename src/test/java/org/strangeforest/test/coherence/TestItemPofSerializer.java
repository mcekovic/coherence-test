package org.strangeforest.test.coherence;

import com.tangosol.coherence.reporter.extractor.*;
import com.tangosol.io.pof.*;
import com.tangosol.io.pof.reflect.*;
import com.tangosol.util.*;
import com.tangosol.util.extractor.*;

import java.io.*;

public class TestItemPofSerializer implements PofSerializer {

	private static final int VERSION = 0;

	private static final int ID = 0;
	private static final int NAME = 1;

	public static final ValueExtractor NAME_EXTRACTOR = new PofExtractor(String.class, NAME);
	public static final ValueExtractor NAME_EXTRACTOR2 = new ReflectionExtractor("getName");

	@Override public void serialize(PofWriter out, Object obj) throws IOException {
		TestItem item = (TestItem)obj;
		out.setVersionId(VERSION);
		out.writeLong(ID, item.getId());
		out.writeString(NAME, item.getName());
		out.writeRemainder(null);
	}

	@Override public TestItem deserialize(PofReader in) throws IOException {
		TestItem item = new TestItem();
		item.setId(in.readLong(ID));
		item.setName(in.readString(NAME));
		in.readRemainder();
		return item;
	}
}