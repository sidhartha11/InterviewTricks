/**
 * 
 */
package com.georgecurington.functionalstudymod.sorts.quicksort;

import java.util.List;
import java.util.Objects;

import com.georgecurington.functionalstudymod.sorts.GSort;
import com.georgecurington.functionalstudymod.sorts.GSortPartition;

/**
 * @author george Curington
 * @since December 24th 2017
 *        <p>
 *        <B>RECURSIVE QUICK SORT</B>
 *        </p>
 *        <p>
 *        Divide And Conquor Sorting
 *        </P>
 * 
 *        <pre>
 * Recursive quick sort.
 * This is a divide and conquor sort. The object is to find a pivot
 * point in the input array and sort the elements to the left and 
 * right of the pivot point.
 *        </pre>
 * <p> Time complexity </p>
 * <pre>
 * worst case running time O( n^2 ) 
 * best case running time O( nlogn)
 * average case running time O (nlogn)
 * </pre>
 * 
 */
public class GQuickSort<T extends Comparable<? super T>> implements GSort<T>, GSortPartition<T> {

	private List<T> data;
	private T experimentalPivot;

	/**
	
	 * 
	 */
	public GQuickSort() {
		// TODO Auto-generated constructor stub
	}
	
	public GQuickSort(List<T> data){
		this.data = data;
	}
	
	public GQuickSort(List<T> data,T experimentalPivot){
		this.experimentalPivot = experimentalPivot;
		this.data = data;
	}

	@Override
	public void sort(List<T> data) 
	{
		if ( this.data == null ){
			this.data = data;
		}
		sort(data, 0, data.size() - 1);

	}
	
	@Override
	public void sort() {
		Objects.requireNonNull(data);
		sort(data);
	}
	
	@Override
	public void sort(boolean experimental) {
		Objects.requireNonNull(data);
		Objects.requireNonNull(experimentalPivot);
		sort(data, 0, data.size() - 1,experimentalPivot);
	}
	
	/**
	 * <p>NOTE: THis is an experimental sorting method used to test an input</>
	 * <p>Pivot point. It is not meant to sort an actual list properly</p>
	 * 
	 * @param data
	 * @param start
	 * @param end
	 * @param experimentalPivot
	 */
	protected void sort(List<T> data, int start, int end,T experimentalPivot) {
		int pIndex = 0;
		if (start < end) {
			pIndex = partition(data, start, end, experimentalPivot);
			sort(data, start, pIndex - 1);
			sort(data, pIndex + 1, end);
		}
	}
	protected void sort(List<T> data, int start, int end) {
		int pIndex = 0;
		if (start < end) {
			pIndex = partition(data, start, end);
			sort(data, start, pIndex - 1);
			sort(data, pIndex + 1, end);
		}
	}
	
	/**
	 * <p>partition</p>
	 * Moves all the elements less than the pivot point to the left side and 
	 * thus leaves those greater than the pivot on the right side. 
	 * @param data The collection containing the data to be sorted
	 * @param start the starting element of the collection being partitioned
	 * @param end The end element being sorted.
	 * @return The pivot point of the collection being sorted.
	 * @see com.georgecurington.functionalstudymod.sorts.GSort#partition(java.util.List, int, int)
	 */
	@Override
	public int partition(List<T> data, int start, int end) {
		/** select the right most element as the pivot **/
		T pivot = data.get(end);
		/** partition index will initially be set to start-1 **/
		int pIndex = start - 1;
		/**
		 * Here we will scan the whole list from start till end-1 and make sure
		 * all the elements less than the pivot are pushed to the left of the
		 * partition index(pIndex). partition index will be adjusted
		 * accordingly.
		 */
		for (int i = start; i < end; i++) {
			if (data.get(i).compareTo(pivot) <= 0) {
				/**
				 * swap the element at partition index with the element at the
				 * current loop counter(i). And then increment the partition
				 * index by 1.
				 */
				pIndex++;
				swap(i, pIndex, data);

			}
		}
		/**
		 * last step is to swap the element at the end with the partitionindex
		 * which is the pivot in this implementation
		 */
		swap(pIndex + 1, end, data);
		return pIndex + 1;
	}
	
	protected int partition(List<T> data, int start, int end, T pivot ) {
		/** select the right most element as the pivot **/
//		T pivot = data.get(end);
		/** partition index will initially be set to start-1 **/
		int pIndex = start - 1;
		/**
		 * Here we will scan the whole list from start till end-1 and make sure
		 * all the elements less than the pivot are pushed to the left of the
		 * partition index(pIndex). partition index will be adjusted
		 * accordingly.
		 */
		for (int i = start; i < end; i++) {
			if (data.get(i).compareTo(pivot) <= 0) {
				/**
				 * swap the element at partition index with the element at the
				 * current loop counter(i). And then increment the partition
				 * index by 1.
				 */
				pIndex++;
				swap(i, pIndex, data);

			}
		}
		/**
		 * last step is to swap the element at the end with the partitionindex
		 * which is the pivot in this implementation
		 */
		swap(pIndex + 1, end, data);
		return pIndex + 1;
	}


	protected void swap(int i, int pIndex, List<T> data) {
		T temp = data.get(i);
		data.set(i, data.get(pIndex));
		data.set(pIndex, temp);

	}

	@Override
	public List<T> getList() {
		// TODO Auto-generated method stub
		return data;
	}

}
