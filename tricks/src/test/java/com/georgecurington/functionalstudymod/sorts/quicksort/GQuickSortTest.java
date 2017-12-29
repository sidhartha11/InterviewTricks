/**
 * 
 */
package com.georgecurington.functionalstudymod.sorts.quicksort;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.georgecurington.functionalstudymod.sorts.GSort;
import com.georgecurington.functionalstudymod.sorts.TestData;

/**
 * @author george
 *
 */
public class GQuickSortTest {

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
	 * Test method for {@link com.georgecurington.functionalstudymod.sorts.quicksort.GQuickSort#GQuickSort()}.
	 */
	@Test
	public void testGQuickSort() {
		GSort<String> sorter = new GQuickSort<>(Arrays.asList("charlie","apple","zorro","xenobia","andy","andy"));
		sorter.sort();
		assertEquals(
				"list not sorted properly"
				,sorter.getList().toString()
				,"[andy, andy, apple, charlie, xenobia, zorro]"
				);
	}
	
	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.sorts.quicksort.GQuickSort#GQuickSort()}.
	 */
	@Test
	public void testGQuickSortBig() {
		GSort<String> sorter = new GQuickSort<>(Arrays.asList(TestData.data));
		sorter.sort();
		/** just print it **/
		sorter.getList().forEach(System.out::println);
	}
	
	@Ignore
	public void testGQuickSortAlreadySorted() {
		GSort<String> sorter = new GQuickSort<>(Arrays.asList(
				"andy", 
				"andy", 
				"apple", 
				"apple", 
				"apple", 
				"apple", 
				"apple", 
				"charlie", 
				"xenobia", 
				"zorro"),"apple");
		sorter.sort(true);

	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.sorts.quicksort.GQuickSort#sort(java.util.List)}.
	 */
	@Ignore
	public void testSortListOfT() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.sorts.quicksort.GQuickSort#sort(java.util.List, int, int)}.
	 */
	@Ignore
	public void testSortListOfTIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.sorts.quicksort.GQuickSort#partition(java.util.List, int, int)}.
	 */
	@Ignore
	public void testPartition() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.sorts.quicksort.GQuickSort#swap(int, int, java.util.List)}.
	 */
	@Ignore
	public void testSwap() {
		fail("Not yet implemented");
	}

}
