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

import com.georgecurington.functionalstudymod.lists.queue.GQueueImpl;
import com.georgecurington.functionalstudymod.sorts.GSort;
import com.georgecurington.functionalstudymod.sorts.bubblesort.GBubbleSort;
import com.georgecurington.functionalstudymod.sorts.quicksort.GQuickSort;
import com.georgecurington.functionalstudymod.utilities.Pair;

/**
 * <pre>
 * <p><b>INTERVIEW TRICKS</b></p>
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
	 * Ignore method for
	 * {@link com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl#GMergeImpl(java.util.List)}.
	 */
	@Ignore
	public void testGMergeImplListOfT() {
		fail("Not yet implemented");
	}

	/**
	 * Ignore method for
	 * {@link com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl#GMergeImpl()}.
	 */
	@Ignore
	public void testGMergeImpl() {
		GSort<Integer> sort = new GMergeImpl<>();
		assertNotNull("ctr error", sort);
	}

	/**
	 * Ignore method for
	 * {@link com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl#getList()}.
	 */
	@Ignore
	public void testGetList() {
		fail("Not yet implemented");
	}

	/**
	 * Ignore method for
	 * {@link com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl#sort(java.util.List)}.
	 */
	@Ignore
	public void testSortListOfT() {
		fail("Not yet implemented");
	}

	/**
	 * Ignore method for
	 * {@link com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl#sort()}.
	 */
	@Ignore
	public void testSort() {
		fail("Not yet implemented");
	}

	/**
	 * Ignore method for
	 * {@link com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl#merge(java.util.List, int, int, int)}.
	 */
	@Ignore
	public void testCreateSublists() {
		List<Integer> data = Arrays.asList(0, 3, 5, 6, 10, 80, 2, 3, 7, 11, 15, 80, 80);
		// Arrays.asList(3,5,6,10,80,2,3,7,11,15);
		GSort<Integer> sort = new GMergeImpl<>(data);
		assertNotNull("ctr error", sort);
		/** create a fake array with each half sorted **/

		/** determine what p,q,r should be **/
		int p = 0;
		int r = data.size();
		int q = ((Merge<Integer>) sort).midpoint(p, r);

		@SuppressWarnings("unchecked")
		Pair<List<Integer>, List<Integer>> pair = (Pair<List<Integer>, List<Integer>>) ((Merge<Integer>) sort)
				.getHalves(data, p, q, r);

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
				// Arrays.asList(0,3,5,6,10,80,2,3,7,11,15,80,80);
				Arrays.asList(0, 3, 5, 6, 10, 11, 1, 2, 3, 7, 11, 15, 80);

		List<Integer> data2 =
				// Arrays.asList(0,3,5,6,10,80,2,3,7,11,15,80,80);
				Arrays.asList(0, 3, 5, 6, 10, 11, 1, 2, 3, 7, 11, 15, 80);

		Collections.sort(data2);
		System.out.println(data2);

		/** determine what p,q,r should be **/
		int size = data.size();
		int midpoint = (int) Math.floor(size / 2);
		int p = 0;
		int q = midpoint;
		int r = data.size();

		((Merge<Integer>) sort).merge(data, p, q, r);

		System.out.println(data);

		assertEquals("merge failed", data2.toString(), data.toString());

	}

	@Ignore
	public void testMidpoint() {
		GSort<Integer> sort = new GMergeImpl<>();
		assertNotNull("ctr error", sort);
		/** create a fake array with each half sorted **/
		List<Integer> data = Arrays.asList(0, 3, 5, 6, 10, 11, 1, 2, 3, 7, 11, 15, 80);

		List<Integer> data2 = Arrays.asList(0, 3, 5, 6, 10, 11, 1, 2, 3, 7, 11, 15, 80);

		Collections.sort(data2);
		System.out.println(data2);

		/** determine what p,q,r should be **/

		int size = data.size();

		int p = 0;
		int r = data.size();
		int midpoint = ((Merge<Integer>) sort).midpoint(p, r);
		int q = midpoint;

		System.out.println("midpoint=" + midpoint);

		((Merge<Integer>) sort).merge(data, p, q, r);

		System.out.println(data);

		assertEquals("merge failed", data2.toString(), data.toString());

	}

	@Ignore
	public void testmerge_sort() {
		GSort<Integer> sort = new GMergeImpl<>();
		assertNotNull("ctr error", sort);
		/** create a fake array with each half sorted **/
		List<Integer> data = Arrays.asList(0, 3, 5, 6, 10, 11, 1, 2, 3, 7, 11, 15, 80);

		List<Integer> data2 = Arrays.asList(0, 3, 5, 6, 10, 11, 1, 2, 3, 7, 11, 15, 80);

		Collections.sort(data2);
		System.out.println(data2);

		/** determine what p,q,r should be **/

		int size = data.size();

		int p = 0;
		int r = data.size();
		int midpoint = ((Merge<Integer>) sort).midpoint(p, r);
		int q = midpoint;

		System.out.println("midpoint=" + midpoint);

		((Merge<Integer>) sort).merge_sort(data, p, r - 1);

		System.out.println(data);

		assertEquals("merge failed", data2.toString(), data.toString());

	}

	@Ignore
	public void testsort() {
		List<Integer> data = Arrays.asList(0, 3, 5, 6, 10, 80, 2, 3, 7, 11, 15, 80, 80);
		List<Integer> data2 = Arrays.asList(0, 3, 5, 6, 10, 80, 2, 3, 7, 11, 15, 80, 80);

		GSort<Integer> sort = new GMergeImpl<>(data);
		assertNotNull("ctr error", sort);

		Collections.sort(data2);
		System.out.println(data2);

		sort.sort();

		System.out.println(data);

		// assertEquals("merge failed", data2.toString(), data.toString());

	}

	@Test
	public void testBigMergeSort() {
		System.out.println("testing mergesort");
		IntStream.rangeClosed(0,10).forEach(r -> {	
		/** create a fake array with each half sorted **/
		List<Integer> data = new ArrayList<>();
//		System.out.println("loading");
		long start = System.currentTimeMillis();

		IntStream.rangeClosed(0, 10_000_000).forEach(p -> {
			int i = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
			data.add(i);
		});

		long end = System.currentTimeMillis();
		String fmt = String.format("elapsed in ms:%d, in secs:%d", end - start,((end - start) / 1000));
//		System.out.println(fmt);
		GSort<Integer> sort = new GMergeImpl<>(data);
		assertNotNull("ctr error", sort);
//		System.out.println("sorting");
		start = System.currentTimeMillis();
		sort.sort();
		end = System.currentTimeMillis();

//		data.stream().limit(100).forEach(System.out::println);
		fmt = String.format("elapsed in ms:%d, in secs:%d", end - start,((end - start) / 1000));
		System.out.println(fmt);
		
		
	});
	}
	
	@Test
	public void testBigQuickSort() {
		System.out.println("testing quicksort");
		IntStream.rangeClosed(0,10).forEach(r -> {
		/** create a fake array with each half sorted **/
		List<Integer> data = new ArrayList<>();
//		System.out.println("loading");
		long start = System.currentTimeMillis();

		IntStream.rangeClosed(0, 10_000_000).forEach(p -> {
			int i = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
			data.add(i);
		});
		long end = System.currentTimeMillis();
		String fmt = String.format("elapsed in ms:%d, in secs:%d", end - start,((end - start) / 1000));
//		System.out.println(fmt);
		
		GSort<Integer> sort = new GQuickSort<>(data);
		assertNotNull("ctr error", sort);


//		System.out.println("sorting");
		start = System.currentTimeMillis();
		sort.sort();
		end = System.currentTimeMillis();

//		data.stream().limit(100).forEach(System.out::println);
		fmt = String.format("elapsed in ms:%d, in secs:%d", end - start,((end - start) / 1000));
		System.out.println(fmt);
		
		});
	}
	
	@Test
	public void testBigBubbleSort() {
		System.out.println("testing bubbleesort");
		IntStream.rangeClosed(0,10).forEach(r -> {	
		/** create a fake array with each half sorted **/
		List<Integer> data = new ArrayList<>();
//		System.out.println("loading");
		long start = System.currentTimeMillis();

		IntStream.rangeClosed(0, 10_000_000).forEach(p -> {
			int i = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
			data.add(i);
		});

		long end = System.currentTimeMillis();
		String fmt = String.format("elapsed in ms:%d, in secs:%d", end - start,((end - start) / 1000));
//		System.out.println(fmt);
		GSort<Integer> sort = new GBubbleSort<>(data);
		assertNotNull("ctr error", sort);
//		System.out.println("sorting");
		start = System.currentTimeMillis();
		sort.sort();
		end = System.currentTimeMillis();

//		data.stream().limit(100).forEach(System.out::println);
		fmt = String.format("elapsed in ms:%d, in secs:%d", end - start,((end - start) / 1000));
		System.out.println(fmt);
		
		
	});
	}

}
