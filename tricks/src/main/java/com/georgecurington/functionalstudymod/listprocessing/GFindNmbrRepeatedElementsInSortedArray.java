/**
 * 
 */
package com.georgecurington.functionalstudymod.listprocessing;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.georgecurington.functionalstudymod.search.GSearch;
import com.georgecurington.functionalstudymod.search.binarysearch.GBinarySearch;
import com.georgecurington.functionalstudymod.sorts.GSort;
import com.georgecurington.functionalstudymod.sorts.TestData;
import com.georgecurington.functionalstudymod.sorts.quicksort.GQuickSort;
import com.georgecurington.functionalstudymod.utilities.Pair;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 * 
 * <p><center>Trick Question</center></p>
 * <pre>
 * Input:
 * Sorted Array
 * A number N to search for in the array.
 * Output:
 * A list containing each occurrence of the number N and its location
 * </pre>
 * <P>E.G.
 * The method works as follows:
 * <ul>
 * <li> Do a binary search on the list to locate the index of the element being searched for
 * <li> scan left and right for all identical elements.
 * </ul>
 * <pre>
 * NOTE: This package uses an internal quick sort and binary search to perform
 * this operation.
 * </pre>
 * 
 *
 */
public class GFindNmbrRepeatedElementsInSortedArray<T extends Comparable<? super T>> {
	private static final boolean DEBUG=true;
	private final List<T> list;
	private final T lookingfor;
	private int counter;
	/**
	 * 
	 */
	public GFindNmbrRepeatedElementsInSortedArray(List<T> list,T element) {
		Objects.requireNonNull(list);
		this.lookingfor = element;
		this.list = list;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/** convert the test data array to a list for testing **/
		List<Integer> list = Arrays.asList(TestData.intdata);
		
		/** now sort the list using GQuickSort **/
		GSort<Integer> sorter = new GQuickSort<>(list);
		sorter.sort();
		
		/** 10 is duplicated several times in the sorted array **/
		Integer lookingfor = 72;
		System.out.println("searching for " + lookingfor );
		
		GFindNmbrRepeatedElementsInSortedArray<Integer> gfind =
		new GFindNmbrRepeatedElementsInSortedArray<>
				(list,lookingfor);
		
		/** find all the recurring lookingfor elements **/
		List<Pair<Integer,Integer>> dups = gfind.findDuplicatedElementInSortedArray();
		System.out.println("solution set:");
		dups.forEach(System.out::println);

	}
	
	public List<Pair<T,Integer>> findDuplicatedElementInSortedArray() {
		/** perform binary search on the list **/
		GSearch<T> search = 
				new GBinarySearch<T>(list,lookingfor);
		
		/** test the find element and find index of element **/
		T found = search.find();
		int foundIndex = search.findIndex();
		if ( DEBUG ){
		System.out.println("found element:" + found + " at index " + foundIndex );
		}
		
		/** print out the sorted array for inspection **/
		if ( DEBUG ) {
		list.forEach(p ->  {
			counter++;
			String s = String.format("%02d:%02d ", counter-1,p);			
			System.out.print(s);
			if ( (counter % 20) == 0){
				System.out.println();
			}
		});
		}
		
		/** return a list of all the duplicates of the search element **/
		List<Pair<T,Integer>> duplicates = search.getDuplicates(list,lookingfor,foundIndex);
		
		/** print the list of found elements **/
		if (DEBUG) {
		System.out.println();
		duplicates.forEach(System.out::println);
		}
		
		return duplicates;
	}

}
