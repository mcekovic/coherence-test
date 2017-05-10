package org.strangeforest.test.coherence.serialization;

import java.io.*;

import com.tangosol.io.pof.*;

public class Entity4PofSerializer implements PofSerializer {

	private static final int VERSION = 3;

	private static final int ID = 0;
	private static final int NAME = 1;
	private static final int DESCRIPTION = 2;
	private static final int CATEGORY = 3;
	private static final int TYPE = 5;

	@Override public void serialize(PofWriter out, Object obj) throws IOException {
		Entity4 entity = (Entity4)obj;
		out.setVersionId(VERSION);
		out.writeLong(ID, entity.getId());
		out.writeString(NAME, entity.getName());
		out.writeString(DESCRIPTION, entity.getDescription());
		out.writeString(CATEGORY, entity.getCategory());
		out.writeString(TYPE, entity.getType());
		out.writeRemainder(null);
	}

	@Override public Entity4 deserialize(PofReader in) throws IOException {
		int version = in.getVersionId();
		Entity4 entity = new Entity4(
			in.readLong(ID),
			in.readString(NAME),
			in.readString(DESCRIPTION),
			in.readString(CATEGORY),
			in.readString(TYPE)
		);
		return entity;
	}
}