package org.strangeforest.test.coherence.serialization;

public class Entity3 {

	private final long id;
	private final String name;
	private final String description;
	private final String type;

	public Entity3(long id, String name, String description, String type) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
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

	public String getType() {
		return type;
	}
}
