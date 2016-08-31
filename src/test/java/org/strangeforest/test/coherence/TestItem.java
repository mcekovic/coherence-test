package org.strangeforest.test.coherence;

import java.io.*;

import static java.lang.String.*;

public class TestItem implements Serializable {

	private long id;
	private String name;

	public TestItem() {}

	public TestItem(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override public String toString() {
		return format("TestItem{id=%d, name='%s'}", id, name);
	}
}
