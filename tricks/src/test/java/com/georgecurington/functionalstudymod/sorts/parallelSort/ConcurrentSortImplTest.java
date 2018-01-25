/**
 * 
 */
package com.georgecurington.functionalstudymod.sorts.parallelSort;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.sorts.GSort;
import com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl;
import com.georgecurington.functionalstudymod.sorts.quicksort.GQuickSort;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 23, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class ConcurrentSortImplTest {

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
	 * Test method for {@link com.georgecurington.functionalstudymod.sorts.parallelSort.ConcurrentSortImpl#ConcurrentSortImpl(int)}.
	 */
	@Ignore
	public void testConcurrentSortImpl() {
		Utility.p("running concurrent sort");
		ConcurrentSort<Integer> concurrentSort = new ConcurrentSortImpl<>(100_000);
		IntStream.rangeClosed(0, 9).forEach(x -> {
			List<Integer> data = new ArrayList<>();
			IntStream.rangeClosed(1, 10_000).forEach(p -> {
				int i = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
				data.add(i);
			});
			concurrentSort.process(data);
		});
		concurrentSort.stop();
	}
	
	@Ignore
	public void testConcurrentSortImpl2() {
		Utility.p("running concurrent sort");
		List<Integer> data = new ArrayList<>();
		IntStream.rangeClosed(0, 20).forEach(x -> {		
			IntStream.rangeClosed(1, 10_117).forEach(p -> {
				int i = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
				data.add(i);
			});
		});
		Utility.p("loaded up " + data.size());
		ConcurrentSort<Integer> concurrentSort = new ConcurrentSortImpl<>(data.size());
		double chunkPercentage = 10 ; 
		double chunks = Math.floor(((double)data.size())/chunkPercentage);
		double leftOver = data.size() % chunkPercentage;
		double chunksXpercentage = chunks * chunkPercentage;
		Utility.p("chunksXpercentage=" + chunksXpercentage + ", leftOver=" + leftOver + ", data.size()=" + data.size() + ", chunkPercentage=" + chunkPercentage + ", chunks = " + chunks);
		
		AtomicInteger ai = new AtomicInteger(0);
		List<Integer> remainderChunk = new ArrayList<>();
		/** first get any remainder that did not divide evenly **/
		if ( leftOver > 0 ) {
		IntStream.rangeClosed(0, (int) (leftOver-1)).forEach( p -> {
			remainderChunk.add(data.get(ai.get()));
			ai.incrementAndGet();
		});
		concurrentSort.process(remainderChunk);
		}
		
		IntStream.rangeClosed(0, (int) (chunkPercentage-1)).forEach( p -> {
			List<Integer> oneChunk = new ArrayList<>();
			IntStream.rangeClosed(0, (int) (chunks-1)).forEach( x -> {
				oneChunk.add(data.get(ai.get()));
				ai.incrementAndGet();
			});
			concurrentSort.process(oneChunk);
			
		});
		concurrentSort.stop();
		List<Integer> list = concurrentSort.getSortedList();
		list.stream().limit(100).forEach(System.out::println);
	}
	@Ignore
	public void testBigFileNonParallel() {
		Utility.p("running concurrent sort");
		List<Integer> data = new ArrayList<>();
		IntStream.rangeClosed(0, 1000).forEach(x -> {		
			IntStream.rangeClosed(1, 10_117).forEach(p -> {
				int i = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
				data.add(i);
			});
		});
		Utility.p("loaded up " + data.size());
		GSort<Integer> sort = new GQuickSort<>();
		long start = System.currentTimeMillis();
		sort.sort(data);
		long end = System.currentTimeMillis();
		Utility.p(String.format("elasped:%dms, %dsecs", (end - start) , ((end - start)/1000)));
		data.stream().limit(100).forEach(System.out::println);
	}
	
	@Test
	public void testConcurrentSortMergeSort() {
		GSort<Integer> qsort = new GQuickSort<Integer>();
		GSort<Integer> msort = new GMergeImpl<Integer>();
		List<Integer> data = new ArrayList<>();
		List<Integer> data2 = new ArrayList<>();
		List<Integer> data3 = new ArrayList<>();

		Utility.p("loading data " );
		IntStream.rangeClosed(0, 1000).forEach(x -> {		
			IntStream.rangeClosed(1, 10_117).forEach(p -> {
				int i = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
				data.add(i);
			});
		});
 		data2.addAll(data);
 		data3.addAll(data);
		Utility.p("loaded up " + data.size());

		Utility.p("running concurrent merge sort");

		long start = System.currentTimeMillis();
		ConcurrentSort<Integer> concurrentSort = new ConcurrentSortImpl<>(msort::sort);
		List<Integer> list = concurrentSort.processFullFile(data);
		long end = System.currentTimeMillis();
		Utility.p(String.format("elasped:%dms, %dsecs", (end - start) , ((end - start)/1000)));
		list.stream().limit(4).forEach(System.out::println);
		
		
		Utility.p("running concurrent quick sort");

		start = System.currentTimeMillis();
		concurrentSort = new ConcurrentSortImpl<>(qsort::sort);
		list = concurrentSort.processFullFile(data2);
		end = System.currentTimeMillis();
		Utility.p(String.format("elasped:%dms, %dsecs", (end - start) , ((end - start)/1000)));
		list.stream().limit(4).forEach(System.out::println);
		
		Utility.p("running library sort");
		qsort = new GQuickSort<Integer>();

		start = System.currentTimeMillis();
//		Collections.sort(data3);
		qsort.sort(data3);

		end = System.currentTimeMillis();
		Utility.p(String.format("elasped:%dms, %dsecs", (end - start) , ((end - start)/1000)));
		data3.stream().limit(10).forEach(System.out::println);
	}

}
