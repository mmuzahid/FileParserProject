package com.freewire.parser.output;

public class Keyword {
	private String name;
	private int count;

	public Keyword(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}
	
	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == null || !(otherObject instanceof Keyword)) {
			return false;
		}
		Keyword otherKeyword = (Keyword) otherObject;
		return this.getName().equals(otherKeyword.getName());
	}

	@Override
	public String toString() {
		return this.getName() + " [" + getCount() + "]";
	}

}
