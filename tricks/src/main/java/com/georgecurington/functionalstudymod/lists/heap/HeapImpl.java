/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.heap;

import java.util.List;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * Heap Sort Time Complexity:
 * 2 comparisons per node based on height of tree:
 * O ( 2 * log n ) which theta(log n)
 * During the maxHeapify, you need to travel from the root 
 * to the leaf which is Log n , and since there are two comparisons
 * per node you have 2 * log n which if Theta of Log n 
 * 
 * Heap Sort Space Complexity is based on the number of recursive calls
 * made. Since we travel down at most log n levels:
 * Space complexity is O( log n ) 
 * height = number edges from a node to the leaf
 * height of complete binary tree = max number of edges from root
 * to a leaf.
 * 
 * Given a height, what is the maximun number of nodes in a complete
 * binary tree.
 * Examples:
 * Ht          1     2     3     4
 * Max nodes   3     7     15    31 
 * 
 * 2 power(h+1)  -  1 = formula = maximum number of nodes in a complete btree
 * 
 * 3 power(h + 1) - 1
 * 
 * Leafs of a complete binary tree have the following property:
 * if number of nodes is n, then
 * (Math.floor(n/2) + 1) to n are all leaves 
 * 
 *  n / 2 power ( h + 2 )  == number of nodes at any level h.
 * 
 * </pre>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 5, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class HeapImpl<T extends Comparable<? super T>> implements Heap<T> {
	private static final boolean DEBUG = false;
	private int heap_size;
	@Override
	public int getLeftIndex(int i) {
		int r = (i << 1) + 1;
		return r;
	}

	@Override
	public int getRightIndex(int i) {
		int r = ((i << 1) + 2) ;
		return r;
	}

	/**
	 * <pre>
	 * Not sure if you can use a binary right shift for division by two in 
	 * this case since we are using 0 and the beginning of the array
	 * </pre>
	 */
	@Override
	public int getParent(int i) {
		if ( i <= 0 ) {
			throw new IllegalArgumentException("cannot get root's parent");
		}
		int n=0;
		if ( i % 2 == 0 ) {
			n = ( i >> 1) -  1;
		} else {
			n = ( i >> 1 );
		}

		return n;
	}
	

	private int getParentOld(int i) {
		if ( i <= 0 ) {
			throw new IllegalArgumentException("cannot get root's parent");
		}
		Double n = (double)i;
		double l = (Math.ceil(n/2.0)) - 1;
		
		if ( DEBUG ) {
			Utility.p("i = " + i + ",returning  " + l + " (i >> 1)=" + (i >> 1));
		}
		return (int)l;
	}

	@Override
	public int leafStart(int numberNodes) {
		// TODO Auto-generated method stub
		return (int) (Math.floor(numberNodes / 2) + 1);
	}

	@Override
	public int getHeap_size() {
		// TODO Auto-generated method stub
		return heap_size - 1;
	}

	@Override
	public int getNmbrNodesBasedOnHeight(int height) {
		if ( height <= 0 ) {
			throw new IllegalArgumentException("cannot not get height of empty tree");
		}
		int n = (int) (Math.pow(2,height+1) - 1);
		return n;
	}

	@Override
	public int getNmbrNodesBasedOnHeightTernary(int height) {
		if ( height <= 0 ) {
			throw new IllegalArgumentException("cannot not get height of empty tree");
		}
		int n = (int) (Math.pow(3,height+1) - 1)/2;
		return n;
	}

	@Override
	public int getHeightOfCompleteBinaryTree(int nmbrNodes) {
		double h1 = nmbrNodes;
		double h2 = Math.ceil(Math.log(h1));
		System.out.println("h2 = " + h2);
		
		return (int) h2;
	}

	@Override
	public int getLeafStart(int p) {
		int s = Math.floorDiv(p, 2) + 1;
		return s;
	}

	@Override
	public void maxHeapify(List<T> list, int i) {
		
		int largest = 0;
		/** get left child **/
		int l = getLeftIndex(i);
		/** get right child **/
		int r = getRightIndex(i);
		
		/** setup endpoint conditions **/
		boolean lbol = l < getSize();
		boolean rbol = r < getSize();
		
		if ( lbol && (list.get(l).compareTo(list.get(i)) > 0) ) {
			largest = l;
		}
		else {
				largest = i;
		}
		
		if (rbol && (list.get(r).compareTo(list.get(largest)) > 0)  ){
			largest = r ;
		}
		
		if ( largest != i ){
			exchange(list,i,largest);
			if ( DEBUG ) {
			System.out.println(list);
			}
			maxHeapify(list,largest);
		}
		
		
	}
	
	@Override
	public T extractMax(List<T> list) {
		if (getSize() < getMin() ) {
			throw new IllegalAccessError("heap underflow");
		}
		int min = getMin();
		T max = list.get(min);
		list.set(min, list.get(getSize()-1));
		setSize(getSize()-1);
		maxHeapify(list, min);
		return max;
	}

	private int getMin() {
		return 0;
	}

	@Override
	public void exchange(List<T> list, int i, int largest) {
		T temp = list.get(i);
		list.set(i, list.get(largest));
		list.set(largest, temp);	
	}

	@Override
	public void setSize(int size) {
		this.heap_size = size;
		
	}
	
	@Override
	public int getSize() {
		return this.heap_size;
		
	}



}
