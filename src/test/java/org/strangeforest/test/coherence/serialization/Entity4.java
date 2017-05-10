package org.strangeforest.test.coherence.serialization;

public class Entity4 {

	private final long id;
	private final String name;
	private final String description;
	private final String category;
	private final String type;

	public Entity4(long id, String name, String description, String category, String type) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
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

	public String getCategory() {
		return category;
	}

	public String getType() {
		return type;
	}
}
