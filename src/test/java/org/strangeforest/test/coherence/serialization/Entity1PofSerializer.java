package org.strangeforest.test.coherence.serialization;

import java.io.*;

import org.strangeforest.test.coherence.*;

import com.tangosol.io.pof.*;
import com.tangosol.util.*;
import com.tangosol.util.extractor.*;

public class Entity1PofSerializer implements PofSerializer {

	private static final int VERSION = 1;

	private static final int ID = 0;
	private static final int NAME = 1;

	@Override public void serialize(PofWriter out, Object obj) throws IOException {
		Entity1 entity = (Entity1)obj;
		out.setVersionId(VERSION);
		out.writeLong(ID, entity.getId());
		out.writeString(NAME, entity.getName());
		out.writeRemainder(null);
	}

	@Override public Entity1 deserialize(PofReader in) throws IOException {
		int version = in.getVersionId();
		Entity1 entity = new Entity1(
			in.readLong(ID),
			in.readString(NAME)
		);
		in.readRemainder();
		return entity;
	}
}