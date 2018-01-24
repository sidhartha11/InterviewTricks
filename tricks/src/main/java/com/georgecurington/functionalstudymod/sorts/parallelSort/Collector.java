package com.georgecurington.functionalstudymod.sorts.parallelSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.utilities.Pair;
import com.georgecurington.functionalstudymod.utilities.PairImpl;

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
public class Collector<T extends Comparable<? super T>> implements Callable<List<T>> {
	private static final boolean DEBUG = false;
	private final BlockingQueue<List<T>> queue;
	private final AtomicReference<Integer> stillCollecting;

	public Collector(BlockingQueue<List<T>> queue, AtomicReference<Integer> stillCollecting) {
		Utility.p("Collector Running");
		this.queue = queue;
		this.stillCollecting = stillCollecting;
	}

	@Override
	public List<T> call() throws Exception {
		Utility.p("Collector call method invoked");
		ExecutorService exec = Executors.newCachedThreadPool();
		List<List<T>> lists = new ArrayList<List<T>>();
		try {
			while (true) {
				if (Collector.DEBUG) {
					Utility.p("Collector inspecting queue");
				}
				List<T> list = queue.poll(1000, TimeUnit.MILLISECONDS);
				if (list == null) {
					Utility.p("waiting on sorted list");
					continue;
				}
				if (Collector.DEBUG) {
					Utility.p("collector got one item:" + list.size());
				}
				/**
				 * if the size of this list is equal to stillCollecting we are
				 * done
				 **/
				if (list.size() == stillCollecting.get()) {
					Utility.p("acquired " + stillCollecting.get() + " sized list, finished");
					return list;

				}
				lists.add(list);
				if (lists.size() == 2) {

					Pair<List<T>, List<T>> pr = new PairImpl<>(lists.get(0), lists.get(1));
					if (Collector.DEBUG) {
						Utility.p("collecting 2 items " + pr.getLeft().size() + ":" + pr.getRight().size());
						Utility.p("submitting 2 items to merger:" + pr.getLeft().size() + ":" + pr.getRight().size());
						Utility.p(pr.toString());
					}
					exec.submit(new Merger<T>(queue, pr));
					lists.clear();
				}
			}
		} finally {
			/** wait for the merger executor to complete **/
			Utility.p("shutting down Collector");
			exec.shutdown();
			while (!exec.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
				Utility.p("merger exec of Collector  waiting to shutdown");
			}
		}
	}
}