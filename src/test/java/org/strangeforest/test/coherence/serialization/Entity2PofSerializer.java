package org.strangeforest.test.coherence.serialization;

import java.io.*;

import com.tangosol.io.pof.*;

public class Entity2PofSerializer implements PofSerializer {

	private static final int VERSION = 1;

	private static final int ID = 0;
	private static final int NAME = 1;
	private static final int DESCRIPTION = 2;

	@Override public void serialize(PofWriter out, Object obj) throws IOException {
		Entity2 entity = (Entity2)obj;
		out.setVersionId(VERSION);
		out.writeLong(ID, entity.getId());
		out.writeString(NAME, entity.getName());
		out.writeString(DESCRIPTION, entity.getDescription());
		out.writeRemainder(null);
	}

	@Override public Entity2 deserialize(PofReader in) throws IOException {
		int version = in.getVersionId();
		Entity2 entity = new Entity2(
			in.readLong(ID),
			in.readString(NAME),
			in.readString(DESCRIPTION)
		);
		in.readRemainder();
		return entity;
	}
}