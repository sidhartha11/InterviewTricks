/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.queue;

import com.georgecurington.functionalstudymod.lists.linkedlist.GLinkedListImpl;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class GQueueImpl<E> extends GLinkedListImpl<E> implements GQueue<E> {
	private static final boolean DEBUG=false;
	/**
	 * 
	 */
	public GQueueImpl() {
		super();
	}

	@Override
	public E poll() {
		if ( size == 0 ) {
			return null;
		}
		E ele = getHead();
		getNext();
		size--;
		return ele;
	}

	@Override
	public E peek() {
		try {
	        E data = getHead();
	        return data;
			} catch (Exception e){
				if ( DEBUG )
				e.printStackTrace();
			}
			return null;
	}
	@Override
	public boolean offer(E data) {
		return insertTail(data);
	}
	@Override
	public int size() {
		return super.size();
	}
}
