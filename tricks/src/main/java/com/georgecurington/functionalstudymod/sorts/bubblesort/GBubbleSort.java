/**
 * 
 */
package com.georgecurington.functionalstudymod.sorts.bubblesort;

import java.util.List;
import java.util.Objects;

import com.georgecurington.functionalstudymod.sorts.GSort;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p> 
 * Simple Bubble Sort routine.
 * This has time complexity O ( n-squared ). Note the nested
 * loops. Generally speaking each nesting adds a power to the 
 * complexity. 
 * <br>
 * <pre>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 19, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * @param <T>
 * </pre>
 */
public class GBubbleSort<T extends Comparable<? super T>> implements GSort<T> {
	protected List<T> data;
	/**
	 * 
	 */
	public GBubbleSort(List<T> data) {
		Objects.requireNonNull(data);
		this.data = data;
	}

	@Override
	public List<T> getList() {
		return this.data;
	}

	@Override
	public void sort(List<T> data) {
		if ( this.data == null ){
			this.data = data;
		}
		_sort(data);
	}

	@Override
	public void sort() {
		Objects.requireNonNull(data);
		_sort(data);
		
	}

	/**
	 * <pre>
	 * outer loop
	 * Scan the input list from beggining to end, 0 to size-1
	 *   inner loop
	 *   scan the input from index=1 to size - (outer index)
	 *     if ( element at i-1 is greater than element at i )
	 *         swap the elements with one another 
	 *   repeat inner loop
	 * repeat outer loop
	 * @param data
	 */
	protected void _sort(List<T> data) {
		for (int i=0; i<data.size();i++ )
			for (int x=1;x<data.size()-i;x++){
				swap(data,x);
			}
		
	}
	
	protected void swap(List<T> data, int i) {
		if (data.get(i-1).compareTo(data.get(i)) > 0){
			T temp = data.get(i-1);
			data.set(i-1, data.get(i));
			data.set(i, temp);
		}
		
	}
	@Override
	public void sort(boolean experimentalPivot) {
		throw new IllegalAccessError();
	}
}
