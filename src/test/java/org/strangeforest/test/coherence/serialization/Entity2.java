package org.strangeforest.test.coherence.serialization;

public class Entity2 {

	private final long id;
	private final String name;
	private final String description;

	public Entity2(long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
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
}
