/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.node;

/**
 * @author george
 *
 */
public interface Node<E> {
	E getData();
	Node<E> getNext();
	boolean insertNext(Node<E> next);
}
