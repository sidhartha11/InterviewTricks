package com.georgecurington.functionalstudymod.lists.stack;
/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public interface GStack<E> {
	E pop();
	E peek();
	boolean push(E data);
	boolean isEmpty();
	int size();
}
