/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.javalist;

import java.util.LinkedList;

import com.georgecurington.functionalstudymod.lists.stack.GStack;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class GStackUsingJava<N> implements GStack<N> {
	
	private static final boolean DEBUG = false;
	private final LinkedList<N> list;

	/**
	 * @param list
	 */
	public GStackUsingJava(LinkedList<N> list) {
		super();
		this.list = list;
	}

	@Override
	public N pop() {
		// TODO Auto-generated method stub
		return list.pop();
	}

	@Override
	public N peek() {
		// TODO Auto-generated method stub
		return list.peek();
	}

	@Override
	public boolean push(N data) {
		try {
		list.push(data);
		return true;
		} catch ( Exception e){
			if ( DEBUG ){
				e.printStackTrace();
			}
		}
		return false;
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
