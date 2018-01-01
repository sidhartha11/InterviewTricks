/**
 * 
 */
package com.georgecurington.functionalstudymod.queue.priority;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import com.georgecurington.functionalstudymod.testdata.TestData;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 * 
 *          <pre>
 * This code attempts to implement a running collection of the largest N elements
 * received from a possibly infinite input source.
 *          </pre>
 *
 */

public class JavaPriorityQueueExamples<E extends Comparable<? super E>> {
	private static final boolean DEBUG = false;
	private final Set<E> visited;
	private final Queue<E> maxheap;
	private final int limit;

	/**
	 * 
	 */
	public JavaPriorityQueueExamples(int limit) {
		this.limit = limit;
		visited = new HashSet<>(limit);
		maxheap = new PriorityQueue<E>(limit, new Comparator<E>() {
			@Override
			public int compare(E o1, E o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		});
	}

	public void add(E element) {
		if (maxheap.size() == limit) {
			if (element.compareTo(maxheap.peek()) > 0 && visited.contains(element) == false) {
				if (DEBUG) {
					System.out.println("adding new large element:" + element);
				}
				visited.remove(maxheap.peek());
				visited.add(element);
				maxheap.remove();
				maxheap.add(element);
				if (DEBUG) {
					try {
						System.out.println(maxheap);
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			/** with this condition the list may never reach the limit **/
			if (DEBUG) {
				System.out.println("empty adding:" + element);
			}
			visited.add(element);
			maxheap.add(element);
		}
	}

	/**
	 * @return the maxheap
	 */
	public final Object[] getMaxheap() {
		return maxheap.toArray();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	//	largestStringSet();
		largestNumberSet();

	}

	private static void largestStringSet() {
		JavaPriorityQueueExamples<String> heap = new JavaPriorityQueueExamples<>(10);
		IntStream.range(0, 1000000).forEach(p -> {
			String s = TestData.getSaltString(2, 10, -1);
			heap.add(s);
		});
		
		Arrays.stream(heap.getMaxheap()).sorted().forEach(p -> {
			System.out.println(p);
		});
	}

	private static void largestNumberSet() {
		JavaPriorityQueueExamples<Integer> heap = new JavaPriorityQueueExamples<>(10);
		IntStream.range(0, 1000000).forEach(p -> {
			int i = ThreadLocalRandom.current().nextInt(0, 100000);
			heap.add(i);
		});
		
		Arrays.stream(heap.getMaxheap()).sorted().forEach(p -> {
			System.out.println(p);
		});
	}

}
