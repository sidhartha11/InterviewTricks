/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.stack;

import com.georgecurington.functionalstudymod.lists.linkedlist.GLinkedListImpl;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class GStackImpl<E> extends GLinkedListImpl<E> implements GStack<E> {
	private static final boolean DEBUG=false;
	
	/**
	 * 
	 */
	public GStackImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public E pop() {
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
	public boolean push(E data) {
		return insertHead(data);
	}
	
	@Override
	public int size() {
		return super.size();
	}

}
