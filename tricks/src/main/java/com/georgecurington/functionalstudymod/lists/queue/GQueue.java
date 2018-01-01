/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.queue;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public interface GQueue<E> {
	E poll();
	E peek();
	boolean offer(E data);
	boolean isEmpty();
	int size();
}
