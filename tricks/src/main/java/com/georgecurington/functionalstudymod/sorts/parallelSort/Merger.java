package com.georgecurington.functionalstudymod.sorts.parallelSort;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.sorts.GSort;
import com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl;
import com.georgecurington.functionalstudymod.sorts.merge.Merge;
import com.georgecurington.functionalstudymod.utilities.Pair;

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
public class Merger<T extends Comparable<? super T>> implements Callable<Void>{
	private static final boolean DEBUG = false;
	private final BlockingQueue<List<T>> queue;
	private final Pair<List<T>, List<T>> pr;
	
	public Merger(BlockingQueue<List<T>> queue,Pair<List<T>, List<T>> pr) {
		this.queue = queue;
		this.pr = pr;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Void call() throws Exception {
		GSort<T> sort = new GMergeImpl<T>();
		if ( Merger.DEBUG ) {
		Utility.p("merging " + pr.getLeft().size() + ":" + pr.getRight().size());
		}
		List<T> m = ((Merge<T>)sort).twowaymerge(pr.getLeft(), pr.getRight());
		if (Merger.DEBUG)
		Utility.p("merged " + m.size());
		queue.put(m);
		return null;
	}
	
}