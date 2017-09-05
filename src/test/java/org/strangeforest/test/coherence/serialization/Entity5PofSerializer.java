package org.strangeforest.test.coherence.serialization;

import java.io.*;

import com.tangosol.io.pof.*;

public class Entity5PofSerializer implements PofSerializer {

	static final int VERSION = 5;

	private static final int ID = 0;
	private static final int NAME = 1;
	private static final int DESCRIPTION = 2;
	private static final int CATEGORY = 3;
	private static final int CATEGORY_INT = 4;
	private static final int TYPE = 5;

	@Override public void serialize(PofWriter out, Object obj) throws IOException {
		Entity5 entity = (Entity5)obj;
		out.setVersionId(entity.getDataVersion());
		out.writeLong(ID, entity.getId());
		out.writeString(NAME, entity.getName());
		out.writeString(DESCRIPTION, entity.getDescription());
		out.writeObject(CATEGORY, String.valueOf(entity.getCategory()));
		out.writeInt(CATEGORY_INT, entity.getCategory());
		out.writeString(TYPE, entity.getType());
		out.writeRemainder(entity.getFutureData());
	}

	@Override public Entity5 deserialize(PofReader in) throws IOException {
		int version = in.getVersionId();
		Entity5 entity = new Entity5(
			in.readLong(ID),
			in.readString(NAME),
			in.readString(DESCRIPTION),
			version >= VERSION ? in.readInt(CATEGORY_INT) : Integer.parseInt(in.readString(CATEGORY)),
			in.readString(TYPE)
		);
		entity.setDataVersion(version);
		entity.setFutureData(in.readRemainder());
		return entity;
	}
}