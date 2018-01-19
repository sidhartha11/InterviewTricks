/**
 * 
 */
package com.georgecurington.functionalstudymod.sorts.bubblesort;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.georgecurington.functionalstudymod.sorts.GSort;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * <pre>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 19, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * </pre>
 */
public class GBubbleSortTest {

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
	 * Test method for {@link com.georgecurington.functionalstudymod.sorts.bubblesort.GBubbleSort#GBubbleSort(java.util.List)}.
	 */
	@Test
	public void testGBubbleSort() {
		GSort<Integer> bubblesort = new GBubbleSort<>(Arrays.asList(10,5,0,100,2));
		bubblesort.sort();
		assertEquals("[0, 2, 5, 10, 100]", bubblesort.getList().toString());
	}

}
