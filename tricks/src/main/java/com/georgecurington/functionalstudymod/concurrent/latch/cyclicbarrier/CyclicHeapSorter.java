/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.latch.cyclicbarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.lists.heap.Heap;
import com.georgecurington.functionalstudymod.lists.heap.HeapImpl;

/**
 * <pre>
 * <p><b>INTERVIEW TRICKS</b></p>
 * </pre>
 * <p>
 * ================================================
 * </p>
 * 
 * <pre>
 * This contrived example of a Cyclic Barrier works as follows:
 * multiple task will create heaps from input arrays and return
 * the largest items in each heap when they finish. The Runnable component
 * of the barrier will retrive the items processed via a queue.
 * </pre>
 * 
 * <br>
 * 
 * @author George Curington
 * @version 1.0.0
 * @since Feb 8, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class CyclicHeapSorter {
	volatile Integer POISONPILL = new Integer(Integer.MAX_VALUE);
	volatile BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
	private static final boolean DEBUG = false;

	/**
	 * 
	 */
	public CyclicHeapSorter() {
		doSimulation(10);
	}

	private void doSimulation(int n) {

		/**
		 * Create a executor
		 */
		ExecutorService exec = new ThreadPoolExecutor(
				20, /** corePoolSize -- number to keep in the pool **/
				100, /** maximum size of pool **/
				0, /** keep alive time when #threads > core **/
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r, java.util.concurrent.ThreadPoolExecutor executor) {
						Utility.p("task failed:" + r.toString());
					}
				});

		List<Future<Integer>> futs = new ArrayList<>();
		CyclicBarrier barrier = new CyclicBarrier(n, new WorkerRunner<Integer>(queue));

		for (int i = 0; i < n; i++) {
			List<Integer> list = new ArrayList<>();
			IntStream.rangeClosed(1, 10).forEach(p -> {
				list.add(ThreadLocalRandom.current().nextInt(0, 10_000));
			});
			if (DEBUG) {
				Utility.p("--------------------------------");
				list.stream().limit(10).forEach(System.out::println);
				Utility.p("--------------------------------");
			}

			futs.add(exec.submit(new Worker<Integer>(list, barrier, queue)));

		}

		Utility.p("putting poison pill in main");

		try {
			queue.put(POISONPILL);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/** now here we just submit all the workers **/

		for (Future<Integer> ele : futs) {
			if (DEBUG)
				Utility.p("getting future");
			try {
				int i = ele.get();
				if (DEBUG)
					Utility.p("futs.get()=" + i);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		exec.shutdown();
		try {
			while (!exec.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
				Utility.p("waiting to terminate");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CyclicHeapSorter();

	}

	class WorkerRunner<T extends Comparable<? super T>> implements Runnable {
		private final BlockingQueue<T> queue;

		public WorkerRunner(BlockingQueue<T> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			Utility.p("cyclicbarrier finished, Runnable executing");
			Utility.p("processing queue:" + queue.size());
			T v = null;
			try {
				while ((v = queue.take()) != POISONPILL) {

					Utility.p("processing max " + v);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				Utility.p("POISON PILL PROCESSED");
			}
		}
	}

	class Worker<T extends Comparable<? super T>> implements Callable<T> {
		private final List<T> list;
		private final CyclicBarrier barrier;
		private final BlockingQueue<T> queue;

		public Worker(List<T> list, CyclicBarrier barrier, BlockingQueue<T> queue) {
			this.list = list;
			this.barrier = barrier;
			this.queue = queue;
		}

		@Override
		public T call() throws Exception {
			Heap<T> heap = new HeapImpl<T>();
			if (DEBUG)
				Utility.p("calling buildHeap");
			heap.buildHeap(list, true);
			T r = heap.extractMax(list);
			if (DEBUG) {
				Utility.p("..max=" + r);
				Utility.p("waiting at barrier point:" + barrier.getNumberWaiting() + ":" + barrier.getParties());
			}
			queue.put(r);
			if (DEBUG)
				Utility.p("queue=" + queue);
			barrier.await();
			if (DEBUG)
				Utility.p("past barrier point adding " + r);

			return r;
		}

	}

}
