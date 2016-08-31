package org.strangeforest.test.coherence;

import com.tangosol.io.pof.*;

import java.io.*;

public class TestItemPofSerializer implements PofSerializer {

	private static final int VERSION = 0;

	private static final int ID = 0;
	private static final int NAME = 1;
	private static final int DESCRIPTION = 2;

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