/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.javalist;

import java.util.LinkedList;

import com.georgecurington.functionalstudymod.lists.queue.GQueue;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class GQueueUsingJava<E> implements GQueue<E> {
	private final LinkedList<E> list;
	/**
	 * @param list
	 */
	public GQueueUsingJava(LinkedList<E> list) {
		super();
		this.list = list;
	}
	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return list.poll();
	}
	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return list.peek();
	}
	@Override
	public boolean offer(E data) {
		// TODO Auto-generated method stub
		return list.offer(data);
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return list.isEmpty();
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

}
