package org.strangeforest.test.coherence.serialization;

import com.tangosol.io.*;

public class Entity6 extends AbstractEvolvable {

	private final long id;
	private final String name;
	private final int category;
	private final String type;

	public Entity6(long id, String name, int category, String type) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.type = type;
		setDataVersion(Entity6PofSerializer.VERSION);
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCategory() {
		return category;
	}

	public String getType() {
		return type;
	}

	@Override public int getImplVersion() {
		return Entity6PofSerializer.VERSION;
	}
}
