/**
 * 
 */
package com.georgecurington.functionalstudymod.sorts.merge;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.sorts.GSort;
import com.georgecurington.functionalstudymod.utilities.Pair;
import com.georgecurington.functionalstudymod.utilities.PairImpl;

/**
 * <pre>
 * <p><b>INTERVIEW TRICKS</b></p>
 * 
 * Implementation of merge sort for generic lists.
 * 
 * Time and Space Complexity
 * 
 * if the number of elements of the list is n , then we will have  
 * ceiling of log n + 1 levels   = O(log n)
 * merging  space complexity  = O(n)
 * so total space complexity is O( n + logn ) = O (n)
 * 
 * Time Complexity:
 * </pre>
 * <pre>
 * {@code
 * merge-sort ( A , p , r )             T(n)
 * {
 *     if p < r {
 *       q = floor ( p + r / 2 ) 
 *       merge-sort ( A , p , q )       T(n/2)
 *       merge-sort ( A , q + 1 , r )   T(n/2)
 *       merge ( A , p , q , r )        O(n)
 * }
 * }
 * so total time complexity is : T(n) = 2 * T(n/2) + O(n)
 * which using some math formula comes out to:
 * O( n log n )  which is both best case and worst case. 
 * Even if the list was originally sorted 
 *
 * }
 * </pre>
 * <p>
 * ================================================
 * </p>
 * <br>
 * 
 * @author George Curington
 * @version 1.0.0
 * @since Jan 21, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class GMergeImpl<T extends Comparable<? super T>> implements GSort<T>, Merge<T> {
	protected List<T> data;
	public static final boolean DEBUG=false;

	/**
	 * 
	 */
	public GMergeImpl(List<T> data) {
		Objects.requireNonNull(data);
		this.data = data;
	}

	public GMergeImpl() {
	}

	@Override
	public List<T> getList() {
		return this.data;
	}

	@Override
	public void sort(List<T> data) {
		Objects.requireNonNull(data);
		this.data = data;
		merge_sort(data,0,data.size()-1);
	}

	@Override
	public void sort() {
		Objects.requireNonNull(data);
		merge_sort(data,0,data.size()-1);
	}

	@Override
	public void sort(boolean experimentalPivot) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <pre>
	 * The merge method is the heart of the merge sort
	 * algorithm. The purpos here is to merge the two
	 * halves of the input list with each half being considered
	 * already sorted. So this method will simply merge the 
	 * two halves. 
	 * The input parameters can be described fully as follows:
	 * p is the first index location in the first sorted half of data.
	 * q is the last index location in first sorted half of data.
	 * q+1 is the first index location in the second sorted half of data.
	 * r is the last index location in the second sorted half of data.
	 * 
	 * This method represents an out-of-place procedure. In that the data elements
	 * are moved out of the original data list into two sub lists that represent
	 * the two ordered partitions of the original list.
	 * 
	 * Upon entry into the method the followings index variables are set up:
	 * 
	 * n1 = q - p + 1 
	 * n2 = r - q 
	 * Where n1 and n2 represent the number of elements in the two sublists that 
	 * must be created. 
	 * hence:
	 * List<T> left = new ArrayList<>(n1 + 1);
	 * List<T> right = new ArrayList<>(n2 + 1);
	 * 
	 * Note the extra element location specified: n1 + 1.
	 * This is done because of the author's approach to the algorithm. It is not 
	 * required. The author uses an infinity value to automate the processing loop.
	 * I am trying to hack the infinity issue by using the added element of null.
	 * 
	 * </pre>
	 * 
	 * @param data
	 *            the list to be sorted
	 * @param p
	 *            the first index of the first half
	 * @param q
	 *            the last index of the first half
	 * @param r
	 *            the last index of the list
	 */
	@Override
	public void merge(List<T> a, int p, int q, int r) {
		/** get the two halves first **/
		Pair<List<T>, List<T>> pair = (Pair<List<T>, List<T>>) getHalves(a, p, q, r);
		List<T> L = pair.getLeft();
		List<T> R = pair.getRight();
		
		/** number elements in first list **/
		int n1 = q - p + 1;
		/** number elements in the second list **/
		int n2 = r - q;

		
		/** reinitialize the index pointers **/
		int i = 0;
		int j = 0;

		/** count all the elements in the input list **/
		boolean firstExhausted = false, secondExhausted = false;
		for (int k = p; k <= r; k++) {
			if (i >= n1) {
				/** first list exhausted **/
				firstExhausted = true;
			}
			if (j >= n2) {
				/** second list exhausted **/
				secondExhausted = true;
			}

			if (firstExhausted || secondExhausted) {
				if (firstExhausted) {
					/** copy all of the remaining second array **/
					a.set(k, R.get(j));
					j++;
				} else {
					/** copy all of the remaining first array **/
					a.set(k, L.get(i));
					i++;
				}
			} else {
				/** just continue merging **/
				if (L.get(i).compareTo(R.get(j)) <= 0) {
					/** Left list element smaller **/
					a.set(k, L.get(i));
					i++;
				} else {
					a.set(k, R.get(j));
					j++;
				}
			}
		}

	}
	
	@Override
	public void mergeInfinity(List<T> data, int p, int q, int r) {
		/** get the two halves first **/
		Pair<List<T>, List<T>> pair = (Pair<List<T>, List<T>>) getHalvesInfinity(data, p, q, r);
		List<T> left = pair.getLeft();
		List<T> right = pair.getRight();
		/** now merge the two sorted list **/
		int i = 0, j = 0;
		int leftInfinity = left.size() - 1;
		int rightInfinity = right.size() - 1;
		for (int k = p; k <= r; k++) {
			if (i == leftInfinity && j == rightInfinity) {
				break;
			} else if (i == leftInfinity) {
				data.set(k, right.get(j));
				j++;
			} else if (j == rightInfinity) {
				data.set(k, left.get(i));
				i++;
			} else if ((left.get(i).compareTo(right.get(j)) <= 0)) {
				data.set(k, left.get(i));
				i++;
			} else {
				data.set(k, right.get(j));
				j++;
			}
		}
	}

	/**
	 * @param L
	 * @param R
	 */
	@Override
	public List<T> twowaymerge(List<T> L, List<T> R) {
		boolean firstExhausted = false, secondExhausted = false;
		List<T> a = new ArrayList<>();
		int p = 0, i = 0, j = 0;
		int n1 = L.size();
		int n2 = R.size();
		int r = Math.min(L.size(), R.size());
//		for (int k = p; k <= r; k++) {
		while ( true ) {

			if (i >= n1) {
				/** first list exhausted **/
				firstExhausted = true;
			}
			if (j >= n2) {
				/** second list exhausted **/
				secondExhausted = true;
			}
			if ( firstExhausted && secondExhausted ){
				break;
			}
			if (firstExhausted || secondExhausted) {
				if (firstExhausted) {
					/** copy all of the remaining second array **/
					a.add(R.get(j));
					j++;
				} else {
					/** copy all of the remaining first array **/
					a.add(L.get(i));
					i++;
				}
			} else {
				/** just continue merging **/
				if (L.get(i).compareTo(R.get(j)) <= 0) {
					/** Left list element smaller **/
					a.add(L.get(i));
					i++;
				} else {
					a.add(R.get(j));
					j++;
				}
			}
		}
		
		return a;
	}
	/**
	 * <pre>
	 * split the input list into two parts using the midpoint
	 * to determine where to split.
	 * if we have a list A such that:
	 * A = 2,5,7,8,9
	 * and 
	 * p = 0 
	 * r = A.size - 1 = 4
	 * midpoint = 2
	 * Note that this implementation puts a null element at the 
	 * end of each sublist to try an mimic infinity at the end of the 
	 * list. This was only partially successful.
	 * note:
	 * A possible optimization would be to create a global list and simply 
	 * use it repeatedly.
	 * The midpoint is determined by:
	 * </pre>
	 *
	 * {@code int midpoint = (int) Math.floor(((p + r)) / 2); }
	 * 
	 * @param data
	 * @param p the start of the list
	 * @param midpoint the midpoint of the list
	 * @param r the end of the list
	 * 
	 * @return this will return the two sublist as individual lists.
	 * @see com.georgecurington.functionalstudymod.utilities.Pair<List<T>, List<T>>
	 */
	@Override
	public Pair<List<T>, List<T>> getHalvesInfinity(List<T> data, int p, int midpoint, int r) {

		int n1 = (midpoint - p) + 1; /** size of the first half **/
		int n2 = r - midpoint ; /** size of the second half **/
		List<T> left = new ArrayList<>(n1 + 1);
		List<T> right = new ArrayList<>(n2 + 1);
		/** get left half first **/
		for (int i = 0; i < n1; i++) {
			left.add(data.get(p + i));
		}
		/** put one duplicate element to stand for infinity **/
		left.add(null);

		/** get right half **/
		for (int i = 0; i < n2; i++) {
			right.add(data.get((midpoint + 1) + i));
		}
		/** put one duplicate element to stand for infinity **/
		right.add(null);
		if ( DEBUG ) {
		Utility.p("left=" + left);
		Utility.p("right=" + right);
		}
		return new PairImpl<>(left, right);
	}
	
	@Override
	public Pair<List<T>, List<T>> getHalves(List<T> data, int p, int midpoint, int r) {

		/** number elements in first list **/
		int n1 = midpoint - p + 1;
		/** number elements in the second list **/
		int n2 = r - midpoint;

		List<T> left = new ArrayList<>(n1);
		List<T> right = new ArrayList<>(n2);

		/** copy the first array **/
		int i = 0, j = 0;
		for (i = 0; i < n1; i++) {
			left.add(data.get(p + i));
		}

		/** copy the second array **/
		for (j = 0; j < n2; j++) {
			right.add(data.get(midpoint + j + 1));
		}
		if ( DEBUG ) {
		Utility.p("left=" + left);
		Utility.p("right=" + right);
		}
		return new PairImpl<>(left, right);
	}
	@Override
	public void merge_sort(List<T> data, int p, int r) {
		if (p < r) {
			int q = midpoint(p, r);
			merge_sort(data, p, q);
			merge_sort(data, q + 1, r);
			merge(data, p, q, r);
		}
	}

	public int midpoint(int p, int r) {
		int midpoint = (int) Math.floor(((p + r)) / 2);
		return midpoint;
	}

}
