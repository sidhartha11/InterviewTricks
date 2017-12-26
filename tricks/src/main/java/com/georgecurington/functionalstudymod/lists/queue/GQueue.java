/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.queue;

/**
 * @author george
 *
 */
public interface GQueue<E> {
	E poll();
	E peek();
	boolean offer(E data);
	boolean isEmpty();
	int size();
}
