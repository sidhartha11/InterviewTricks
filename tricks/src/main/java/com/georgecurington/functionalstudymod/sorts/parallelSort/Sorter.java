package com.georgecurington.functionalstudymod.sorts.parallelSort;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.sorts.GSort;
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
 * @param <T>
 */
public class Sorter<T extends Comparable<? super T>> implements Callable<List<T>> {
	private static final boolean DEBUG = false;
	private final List<T> list;
	private final BlockingQueue<List<T>> queue;
	private Supplier<GSort<T>> supplier;
	private Consumer<List<T>>  sorter;
	
	public Sorter(List<T> list, BlockingQueue<List<T>> queue) {
		this.list = list;
		this.queue = queue;
	}
	
	public Sorter(List<T> list, BlockingQueue<List<T>> queue, Consumer<List<T>>  sorter ) {
		this.list = list;
		this.queue = queue;
		this.sorter = sorter ;
	}

	@Override
	public List<T> call() throws Exception {
		try {
			if (Sorter.DEBUG) {
				Utility.p("sorting one item and putting in queue:" + list.size());
			}
			
			if ( sorter != null ){
				sorter.accept(list);
			} else {
				GSort<T> sort = new GQuickSort<T>(list);
				sort.sort();
			}
			
			queue.put(list);
		} finally {
			if (Sorter.DEBUG) {
				Utility.p("returning from sorter call:" + list);
			} else {
				if (Sorter.DEBUG) {
					Utility.p("returning from sorter call:" + list.size());
				}
			}
		}
		return list;
	}

}