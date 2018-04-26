package com.freewire.parser;

public interface ParseStrategy<T, V> {
	public V process(T source);
}
