/**
 * 
 */
package com.georgecurington.functionalstudymod.sorts.merge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.georgecurington.functionalstudymod.sorts.GSort;
import com.georgecurington.functionalstudymod.utilities.Pair;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 21, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class GMergeImplTest {

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
	 * Ignore method for {@link com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl#GMergeImpl(java.util.List)}.
	 */
	@Ignore
	public void testGMergeImplListOfT() {
		fail("Not yet implemented");
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl#GMergeImpl()}.
	 */
	@Ignore
	public void testGMergeImpl() {
		GSort<Integer> sort = new GMergeImpl<>();
		assertNotNull("ctr error", sort);
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl#getList()}.
	 */
	@Ignore
	public void testGetList() {
		fail("Not yet implemented");
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl#sort(java.util.List)}.
	 */
	@Ignore
	public void testSortListOfT() {
		fail("Not yet implemented");
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl#sort()}.
	 */
	@Ignore
	public void testSort() {
		fail("Not yet implemented");
	}

	/**
	 * Ignore method for {@link com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl#merge(java.util.List, int, int, int)}.
	 */
	@Ignore
	public void testCreateSublists() {
		List<Integer> data = 
		Arrays.asList(0,3,5,6,10,80,2,3,7,11,15,80,80);
//		Arrays.asList(3,5,6,10,80,2,3,7,11,15);
		GSort<Integer> sort = new GMergeImpl<>(data);
		assertNotNull("ctr error", sort);
		/** create a fake array with each half sorted **/

		
		/** determine what p,q,r should be **/
		int p = 0;
		int r = data.size();
		int q = midpoint(p,r);
		
		
		@SuppressWarnings("unchecked")
		Pair<List<Integer>, List<Integer>> pair = (Pair<List<Integer>, List<Integer>>)((Merge<Integer>)sort).getHalves(data,p,q,r);
		
		System.out.println("data=" + data);
		System.out.println("pair=" + pair);

	}
	
	@SuppressWarnings("unchecked")
	@Ignore
	public void testMerge() {
		GSort<Integer> sort = new GMergeImpl<>();
		assertNotNull("ctr error", sort);
		/** create a fake array with each half sorted **/
		List<Integer> data = 
//		Arrays.asList(0,3,5,6,10,80,2,3,7,11,15,80,80);
		Arrays.asList(0,3,5,6,10,11,1,2,3,7,11,15,80);
		
		List<Integer> data2 = 
//				Arrays.asList(0,3,5,6,10,80,2,3,7,11,15,80,80);
				Arrays.asList(0,3,5,6,10,11,1,2,3,7,11,15,80);
		
		Collections.sort(data2);
		System.out.println(data2);
		
		/** determine what p,q,r should be **/
		int size = data.size();
		int midpoint = (int) Math.floor(size/2);
		int p = 0;
		int q = midpoint;
		int r = data.size();

       ((Merge<Integer>)sort).merge(data,p,q,r);
       
       System.out.println(data);
       
       assertEquals("merge failed", data2.toString(), data.toString());


	}
	
	@Ignore
	public void testMidpoint() {
		GSort<Integer> sort = new GMergeImpl<>();
		assertNotNull("ctr error", sort);
		/** create a fake array with each half sorted **/
		List<Integer> data = 
		Arrays.asList(0,3,5,6,10,11,1,2,3,7,11,15,80);
		
		List<Integer> data2 = 
				Arrays.asList(0,3,5,6,10,11,1,2,3,7,11,15,80);
		
		Collections.sort(data2);
		System.out.println(data2);
		
		/** determine what p,q,r should be **/
		
		int size = data.size();
		

		int p = 0;
		int r = data.size();
		int midpoint = midpoint(p, r);
		int q = midpoint;
		
		System.out.println("midpoint=" + midpoint);
		
	    ((Merge<Integer>)sort).merge(data,p,q,r);

       
       System.out.println(data);
       
       assertEquals("merge failed", data2.toString(), data.toString());


	}

	@Ignore
	public void testmerge_sort() {
		GSort<Integer> sort = new GMergeImpl<>();
		assertNotNull("ctr error", sort);
		/** create a fake array with each half sorted **/
		List<Integer> data = 
		Arrays.asList(0,3,5,6,10,11,1,2,3,7,11,15,80);
		
		List<Integer> data2 = 
				Arrays.asList(0,3,5,6,10,11,1,2,3,7,11,15,80);
		
		Collections.sort(data2);
		System.out.println(data2);
		
		/** determine what p,q,r should be **/
		
		int size = data.size();
		

		int p = 0;
		int r = data.size();
		int midpoint = midpoint(p, r);
		int q = midpoint;
		
		System.out.println("midpoint=" + midpoint);

	   ((Merge<Integer>)sort).merge_sort(data,p,r-1);
       
       System.out.println(data);
       
       assertEquals("merge failed", data2.toString(), data.toString());


	}
	
	@Test
	public void testsort() {
		List<Integer> data = 
		Arrays.asList(0,3,5,6,10,80,2,3,7,11,15,80,80);
		List<Integer> data2 = 
		Arrays.asList(0,3,5,6,10,80,2,3,7,11,15,80,80);
		
		
		GSort<Integer> sort = new GMergeImpl<>(data);
		assertNotNull("ctr error", sort);
		/** create a fake array with each half sorted **/
//		List<Integer> data = 
//		Arrays.asList(
//				36,
//				59,
//				16,
//				47,
//				61,
//				36,
//				64,
//				68,
//				79,
//				74,
//				62
//				);
//		
//		List<Integer> data2 = 
//				Arrays.asList
//				(
//						36,
//						59,
//						16,
//						47,
//						61,
//						36,
//						64,
//						68,
//						79,
//						74,
//						62
//
//				);
		
		Collections.sort(data2);
		System.out.println(data2);

	   sort.sort();
       
       System.out.println(data);
       
 //      assertEquals("merge failed", data2.toString(), data.toString());


	}
	
	@Ignore
	public void testBigsort() {
		GSort<Integer> sort = new GMergeImpl<>();
		assertNotNull("ctr error", sort);
		/** create a fake array with each half sorted **/
		List<Integer> data = new ArrayList<>();
		IntStream.rangeClosed(0,1000).forEach(p -> {	
			int i = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
			data.add(i);
		});
//	       data.forEach(System.out::println);
//	       System.out.println("====================");

//		Collections.sort(data2);

	   sort.sort(data);
       
       data.forEach(System.out::println);
	}
	/**
	 * @param p
	 * @param r
	 * @return
	 */
//	private int midpoint(int p, int r) {
//		int midpoint = (int) Math.floor(((p + r)-1)/2);
//		return midpoint;
//	}
	private int midpoint(int p, int r) {
		int midpoint = (int) Math.floor(((p + r)) / 2);
		return midpoint;
	}
	/**
	 * @param p
	 * @param r
	 * @return
	 */
	private int midpoint1(int p, int r) {
		int midpoint = (int) Math.floor(((r-1)-p)/2);
		return midpoint;
	}

}
