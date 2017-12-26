/**
 * 
 */
package com.georgecurington.functionalstudymod.search.binarysearch;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.georgecurington.functionalstudymod.search.GSearch;
import com.georgecurington.functionalstudymod.sorts.GSort;
import com.georgecurington.functionalstudymod.sorts.TestData;
import com.georgecurington.functionalstudymod.sorts.quicksort.GQuickSort;
import com.georgecurington.functionalstudymod.utilities.Pair;

/**
 * @author george
 *
 */
public class GBinarySearchTest {
	private int counter;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.search.binarysearch.GBinarySearch#GBinarySearch()}.
	 */
	@Ignore
	public void testGBinarySearch() {
		GSearch<String> search = new GBinarySearch<>();
		assertNotNull("binary search construction failure", search);
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.search.binarysearch.GBinarySearch#GBinarySearch(java.util.List, java.lang.Comparable)}.
	 */
	@Test
	public void testFindDuplicatedElementInSortedArray() {
		
		/** convert the test data array to a list for testing **/
		List<Integer> list = Arrays.asList(TestData.intdata);
		
		/** now sort the list using GQuickSort **/
		GSort<Integer> sorter = new GQuickSort<>(list);
		sorter.sort();
		
		/** 10 is duplicated several times in the sorted array **/
		Integer lookingfor = 10;
		System.out.println("searching for " + lookingfor );
		
		/** perform binary search on the list **/
		GSearch<Integer> search = 
				new GBinarySearch<>(list,lookingfor);
		
		/** test the find element and find index of element **/
		Integer found = search.find();
		int foundIndex = search.findIndex();
		assertNotNull("Binary Search Error", found);
		System.out.println("found element:" + found + " at index " + foundIndex );
		
		/** print out the sorted array for inspection **/
		list.forEach(p ->  {
			counter++;
			String s = String.format("%02d:%02d ", counter-1,p);			
			System.out.print(s);
			if ( (counter % 20) == 0){
				System.out.println();
			}
		});
		
		/** return a list of all the duplicates of the search element **/
		List<Pair<Integer,Integer>> duplicates = search.getDuplicates(list,lookingfor,foundIndex);
		assertNotNull("should not be null", duplicates);
		assertEquals("Should be 7 elements found", 7, duplicates.size());
		
		/** print the list of found elements **/
		System.out.println();
		duplicates.forEach(System.out::println);
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.search.binarysearch.GBinarySearch#find(java.util.List, java.lang.Comparable)}.
	 */
	@Ignore
	public void testFindListOfTT() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.search.binarysearch.GBinarySearch#findIndex(java.util.List, java.lang.Comparable)}.
	 */
	@Ignore
	public void testFindIndexListOfTT() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.search.binarysearch.GBinarySearch#find()}.
	 */
	@Ignore
	public void testFind() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.search.binarysearch.GBinarySearch#findIndex()}.
	 */
	@Ignore
	public void testFindIndex() {
		fail("Not yet implemented");
	}

}
