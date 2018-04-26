package com.freewire.parser;

/**
 * Generic parser Interface
 * @param <T> - Source Type
 * @param <V> - Result
 * @param <S> - Strategy
 */
public interface GenericParser<T, V, S> {
	public V parse(T source, S strategy);
}
