package com.georgecurington.functionalstudymod.lists.stack;

public interface GStack<E> {
	E pop();
	E peek();
	boolean push(E data);
	boolean isEmpty();
	int size();
}
