package org.strangeforest.test.coherence.serialization;

public class Entity1 {

	private final long id;
	private final String name;

	public Entity1(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
