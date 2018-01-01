/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.node;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public interface Node<E> {
	E getData();
	Node<E> getNext();
	boolean insertNext(Node<E> next);
}
