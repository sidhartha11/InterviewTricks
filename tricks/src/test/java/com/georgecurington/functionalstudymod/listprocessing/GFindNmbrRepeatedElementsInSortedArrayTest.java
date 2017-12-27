/**
 * 
 */
package com.georgecurington.functionalstudymod.listprocessing;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.georgecurington.functionalstudymod.sorts.GSort;
import com.georgecurington.functionalstudymod.sorts.TestData;
import com.georgecurington.functionalstudymod.sorts.quicksort.GQuickSort;
import com.georgecurington.functionalstudymod.utilities.Pair;

/**
 * @author george
 *
 */
public class GFindNmbrRepeatedElementsInSortedArrayTest {

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
	 * Test method for {@link com.georgecurington.functionalstudymod.listprocessing.GFindNmbrRepeatedElementsInSortedArray#GFindNmbrRepeatedElementsInSortedArray(java.util.List, java.lang.Comparable)}.
	 */
	@Test
	public void testGFindNmbrRepeatedElementsInSortedArray() {
		/** convert the test data array to a list for testing **/
		List<Integer> list = Arrays.asList(TestData.intdata);
		
		/** sort the array first **/
		GSort<Integer> sorter = new GQuickSort<>(list);
		sorter.sort();
		
		/** init the number to look for **/
		Integer lookingfor = 10;
		GFindNmbrRepeatedElementsInSortedArray<Integer> gfind = 
				new GFindNmbrRepeatedElementsInSortedArray<>(list,lookingfor);
		
		/** find all the recurring lookingfor elements **/
		/** should be 7 10s in the list **/
		List<Pair<Integer,Integer>> dups = gfind.findDuplicatedElementInSortedArray();
		assertEquals("count does not match",dups.size(),7);
		System.out.println("solution set:");
		dups.forEach(System.out::println);
	}
	
}
