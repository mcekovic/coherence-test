package org.strangeforest.test.coherence.serialization;

import com.tangosol.io.*;

public class Entity7 extends AbstractEvolvable {

	private final long id;
	private final String name;
	private final String description;
	private final int category;
	private final String type;

	public Entity7(long id, String name, String description, int category, String type) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.type = type;
		setDataVersion(Entity7PofSerializer.VERSION);
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getCategory() {
		return category;
	}

	public String getType() {
		return type;
	}

	@Override public int getImplVersion() {
		return Entity7PofSerializer.VERSION;
	}
}
