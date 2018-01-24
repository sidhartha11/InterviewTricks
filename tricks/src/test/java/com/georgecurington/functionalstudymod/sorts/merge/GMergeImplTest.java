/**
 * 
 */
package com.georgecurington.functionalstudymod.sorts.merge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.lists.queue.GQueueImpl;
import com.georgecurington.functionalstudymod.sorts.GSort;
import com.georgecurington.functionalstudymod.sorts.bubblesort.GBubbleSort;
import com.georgecurington.functionalstudymod.sorts.quicksort.GQuickSort;
import com.georgecurington.functionalstudymod.utilities.Pair;
import com.georgecurington.functionalstudymod.utilities.PairImpl;

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
	private static final List<Integer> POISONPILL = new ArrayList<>();

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

	@Ignore
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
	
	@Ignore
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
	
	@Ignore
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
	
	@Ignore
	public void testtwowaymerge() {
		List<Integer> data = new ArrayList<>();
		List<Integer> data2 = new ArrayList<>();
//		System.out.println("loading");
		long start = System.currentTimeMillis();

		IntStream.rangeClosed(0, 10).forEach(p -> {
			int i = ThreadLocalRandom.current().nextInt(0, 80);
			data.add(i);
			i = ThreadLocalRandom.current().nextInt(0, 80);
			data2.add(i);
		});
		Collections.sort(data);
		Collections.sort(data2);
		System.out.println("\ndata:\n");
		data.forEach(System.out::println);
		System.out.println("\ndata2:\n");
		data2.forEach(System.out::println);
		
		GSort<Integer> sort = new GMergeImpl<>();
		List<Integer> merged = ((Merge<Integer>)sort).twowaymerge(data, data2);
		System.out.println("\nmerged:\n");
		merged.forEach(System.out::println);

		
	}
	private static BlockingQueue<List<Integer>> queue = new LinkedBlockingQueue<>(1000);
	@Test
	public void testParallelMerge() {
		ExecutorService sorterexec = Executors.newCachedThreadPool();
		Future<List<Integer>> futs = sorterexec.submit(new Collector<Integer>(queue));
		
		IntStream.rangeClosed(0, 9).forEach(x -> {
		List<Integer> data = new ArrayList<>();
		IntStream.rangeClosed(0, 9).forEach(p -> {
			int i = ThreadLocalRandom.current().nextInt(0, 80);
			data.add(i);
		});
		sorterexec.submit(new Sorter<Integer>(data,queue));
		});
		/** put a poison pill on the queue **/
		sorterexec.submit(new Sorter<Integer>(POISONPILL,queue));
		try {
			List<Integer>  merged = futs.get();
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sorterexec.shutdown();
		try {
			while ( !sorterexec.awaitTermination(1000, TimeUnit.MILLISECONDS)){
				System.out.println("waiting..." );
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static class Sorter<T extends Comparable<? super T>> implements Callable<Void>{

		private final List<T> list;
		private final BlockingQueue<List<T>> queue;
		
		public Sorter(List<T> list, BlockingQueue<List<T>> queue) {
			this.list = list;
			this.queue = queue;
		}
		@Override
		public Void call() throws Exception {
			Utility.p("sorting one item and putting in queue:" + list.size());
//			if ( list == POISONPILL ){
//				
//			}
			GSort<T> sort = new GMergeImpl<T>(list);
			sort.sort();
			queue.put(list);
			return null;
		}
		
	}
	
	public static class Collector<T extends Comparable<? super T>> implements Callable<List<T>>{


		private final BlockingQueue<List<T>> queue;
		
		public Collector(BlockingQueue<List<T>> queue) {
			this.queue = queue;
		}
		@Override
		public List<T> call() throws Exception {
			ExecutorService exec = Executors.newCachedThreadPool();
			List<List<T>> lists = new ArrayList<List<T>>();
			while ( true ) {
				List<T> list = queue.take();
				Utility.p("collector got one item:" + list.size());
//				if (list.equals(POISONPILL)) {
//					System.out.println("poison pill encountered");
//					return lists.get(0);
//				}
				lists.add(list);
				if ( lists.size() == 2 ){

					Pair<List<T>, List<T>> pr = new PairImpl<>(lists.get(0), lists.get(1));
					Utility.p("collecting 2 items " + pr.getLeft().size() + ":" + pr.getRight().size());
					Utility.p("submitting 2 items to merger:" + pr.getLeft().size() + ":" + pr.getRight().size());
				    Utility.p(pr.toString());
				    exec.submit(new Merger<T>(queue,pr));
				    lists.clear();
				}
			}
		}
		
	}
	
	public static class Merger<T extends Comparable<? super T>> implements Callable<Void>{


		private final BlockingQueue<List<T>> queue;
		private final Pair<List<T>, List<T>> pr;
		
		public Merger(BlockingQueue<List<T>> queue,Pair<List<T>, List<T>> pr) {
			this.queue = queue;
			this.pr = pr;
		}
		@Override
		public Void call() throws Exception {
			GSort<T> sort = new GMergeImpl<T>();
			Utility.p("merging " + pr.getLeft().size() + ":" + pr.getRight().size());
			List<T> m = ((Merge)sort).twowaymerge(pr.getLeft(), pr.getRight());
			Utility.p("merged " + m.size());
			queue.put(m);
			return null;
		}
		
	}

}
