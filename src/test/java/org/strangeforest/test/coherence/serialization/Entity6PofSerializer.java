package org.strangeforest.test.coherence.serialization;

import java.io.*;

import com.tangosol.io.pof.*;

public class Entity6PofSerializer implements PofSerializer {

	static final int VERSION = 6;

	private static final int ID = 0;
	private static final int NAME = 1;
	private static final int CATEGORY_INT = 4;
	private static final int TYPE = 5;

	@Override public void serialize(PofWriter out, Object obj) throws IOException {
		Entity6 entity = (Entity6)obj;
		out.setVersionId(entity.getDataVersion());
		out.writeLong(ID, entity.getId());
		out.writeString(NAME, entity.getName());
		out.writeInt(CATEGORY_INT, entity.getCategory());
		out.writeString(TYPE, entity.getType());
		out.writeRemainder(entity.getFutureData());
	}

	@Override public Entity6 deserialize(PofReader in) throws IOException {
		int version = in.getVersionId();
		Entity6 entity = new Entity6(
			in.readLong(ID),
			in.readString(NAME),
			in.readInt(CATEGORY_INT),
			in.readString(TYPE)
		);
		entity.setDataVersion(version);
		entity.setFutureData(in.readRemainder());
		return entity;
	}
}