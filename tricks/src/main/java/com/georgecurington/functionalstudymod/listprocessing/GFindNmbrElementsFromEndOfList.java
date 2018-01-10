/**
 * 
 */
package com.georgecurington.functionalstudymod.listprocessing;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * This is a simple class that uses an internal Deque to maintain
 * a collection of the last n elements in a list from the end of the 
 * list. In this case, the list is an unsorted list with no way of 
 * indexing from the end of the list to the last n elements.
 * 
 * Note:
 * A simple java Stream solution is as follows:
 * </pre>
 * {@code
 * l.stream().skip(l.size()-(4+1)).limit(4).forEach(System.out::println);
 * }
 * <pre>
 * Assuming we are looking for the last 4 elements inserted into 
 * the list.
 * However, here we are looking for a different type of solution.
 * </pre>
 * 
 * 
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class GFindNmbrElementsFromEndOfList<T extends Comparable<? super T>> 
    implements GFindNmbrElementsFromEndOfListIntf<T> {
	private final List<T> list = new LinkedList<>();

	private final int nmbrElementsNeeded;
	private Deque<T> deque;
	/**
	 * 
	 */
	public GFindNmbrElementsFromEndOfList(int nmbrElementsNeeded) {
		this.nmbrElementsNeeded = nmbrElementsNeeded;
		deque = new Deque(nmbrElementsNeeded+1);
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.Set#add(java.lang.Object)
	 */
	@Override
	public boolean add(T arg0) {
		deque.push(arg0);
		boolean b =  list.add(arg0);
		return b;
	}

	/**
	 * @return
	 * @see java.util.Set#size()
	 */
	@Override
	public int size() {
		return list.size();
	}
	
	@Override
	public List<T> getNelementsFromEndOfList() {
		// TODO Auto-generated method stub
		return deque.getStack();
	}
	
	private static class Deque<T> {
		private final ArrayDeque<T> deque = new ArrayDeque<>();
		private final int nmbrElements;
		private int cntr=0;

		/**
		 * @param stack
		 */
		public Deque(int nmbrElements) {
			super();
			this.nmbrElements = nmbrElements;
		}
		
		public void push(T element) {
			if (cntr < nmbrElements ){
				deque.addLast(element);
				cntr++;
			} else {
				deque.pollFirst();
				deque.addLast(element);
			}
		}
		
		public List<T> getStack(){
			return deque.stream().limit(nmbrElements-1).collect(Collectors.toList());
		}
	}
}
