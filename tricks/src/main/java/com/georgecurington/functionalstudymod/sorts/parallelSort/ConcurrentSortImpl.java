/**
 * 
 */
package com.georgecurington.functionalstudymod.sorts.parallelSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.sorts.GSort;
import com.georgecurington.functionalstudymod.sorts.merge.GMergeImpl;
import com.georgecurington.functionalstudymod.sorts.quicksort.GQuickSort;

/**
 * <pre>
 * <p><b>INTERVIEW TRICKS</b></p>
 * This Class is an experimental sorter and merger. The initial purpose of creating 
 * this class was just to play around with Java Concurrency, Executors , etc. 
 * 
 * After writing a simple implementation of a Merge Sort algorithm, I decided to extract 
 * the merge part of the merge sort as a stand alone method. Then I had the idea of breaking
 * up a large list into sub lists and pasing pairs to the twoway merge of Merge-Sort. 
 * Each clump of data would be processed via a BlockingQueue. When the twoway merge finishes it 
 * simply puts the resulting file back into the blocking queue.  This is repeated until
 * all the input is exhausted. 
 * so .. we end up with parallelization as follows:
 * 1. Callables sort each clump of data
 * 2. Callables merge each pair of sorted files. 
 * 3. A collector, also a Callable processes the sorted files by pairing them up
 * submitting a twowaymerge Callable for each pair. 
 * 4. And that's it.
 * 
 * One interesting feature are the inclusion of process and stop methods. These allow 
 * the use to continually apply new clumps of data to be sorted and merged. Interesing 
 * idea.
 * 
 * TBD ... need to allow input of variable percentage of data to be grouped. Currently
 * is is just a hard coded value. 
 * </pre>
 * <p>
 * ================================================
 * </p>
 * <br>
 * 
 * @author George Curington
 * @version 1.0.0
 * @since Jan 23, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class ConcurrentSortImpl<T extends Comparable<? super T>> implements ConcurrentSort<T> {

	private static final int QSIZ = 1000;
	private static final boolean DEBUG = false;
	private final BlockingQueue<List<T>> queue = new LinkedBlockingQueue<>(QSIZ);
	private final ExecutorService sorterexec = Executors.newCachedThreadPool();
	private final ExecutorService collectorexec = Executors.newCachedThreadPool();

	private final List<Future<List<T>>> futs = new ArrayList<>();
	private final Future<List<T>> collector;
	private final AtomicReference<Integer> stillCollecting = new AtomicReference<>();
	private List<T> sortedList;
	private Consumer<List<T>> sorter;

	/**
	 * 
	 */
	public ConcurrentSortImpl(int sizeOfData) {
		Utility.p("ConcurrentImpl ctr");
		this.stillCollecting.set(sizeOfData);
		collector = collectorexec.submit(new Collector<T>(queue, stillCollecting));
	}
	
	public ConcurrentSortImpl() {
		Utility.p("ConcurrentImpl ctr");
		collector = collectorexec.submit(new Collector<T>(queue, stillCollecting));
	}
	public ConcurrentSortImpl(int sizeOfData,Consumer<List<T>> sorter) {
		Utility.p("ConcurrentImpl ctr");
		this.stillCollecting.set(sizeOfData);
		this.sorter=sorter;
		collector = collectorexec.submit(new Collector<T>(queue, stillCollecting));
	}
	
	public ConcurrentSortImpl(Consumer<List<T>> sorter) {
		Utility.p("ConcurrentImpl ctr");
		collector = collectorexec.submit(new Collector<T>(queue, stillCollecting));
		this.sorter = sorter;
	}

	@Override
	public void stop() {

		/** wait for all the sorters and mergers to complete **/
		Utility.p("=========== waiting for sorters to complete ============");
		futs.stream().forEach(p -> {
			if ( DEBUG )
			Utility.p("getting future p.get()");
			List<T> list = null;
			try {
				list = p.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			if ( DEBUG )
			Utility.p("got future p.get() " + ((list == null) ? null : list.size()));
		});

		/** gatther up the collector **/
		try {
			List<T> sortedlist = collector.get();
			Utility.p("finished: size of sorted list:" + sortedlist.size());
			setSortedList(sortedlist);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		stopExecutor();
	}

	/**
	 * 
	 */
	private void stopExecutor() {
		collectorexec.shutdown();
		sorterexec.shutdown();
		try {
			while (!sorterexec.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
				System.out.println("waiting for sorters and mergers to shutdown");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			while (!collectorexec.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
				System.out.println("waiting for collector to shutdown");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void setSortedList(List<T> list){
		this.sortedList = list;
	}
	@Override
	public List<T> getSortedList(){
		return this.sortedList;
	}
	
	@Override
	public List<T> processFullFile(List<T> data) {
		/** sort a list and put it in the queue **/
		Utility.p("calling process data size is:" + data.size());
		Utility.p("loaded up " + data.size());
		this.stillCollecting.set(data.size());
		double chunkPercentage = 5 ; 
		double chunks = Math.floor(((double)data.size())/chunkPercentage);
		double leftOver = data.size() % chunkPercentage;
		double chunksXpercentage = chunks * chunkPercentage;
		Utility.p("chunksXpercentage=" + chunksXpercentage + ", leftOver=" + leftOver + ", data.size()=" + data.size() + ", chunkPercentage=" + chunkPercentage + ", chunks = " + chunks);
		
		AtomicInteger ai = new AtomicInteger(0);
		List<T> remainderChunk = new ArrayList<>();
		/** first get any remainder that did not divide evenly **/
		if ( leftOver > 0 ) {
		IntStream.rangeClosed(0, (int) (leftOver-1)).forEach( p -> {
			remainderChunk.add(data.get(ai.get()));
			ai.incrementAndGet();
		});
		process(remainderChunk);
		}
		
		IntStream.rangeClosed(0, (int) (chunkPercentage-1)).forEach( p -> {
			List<T> oneChunk = new ArrayList<>();
			IntStream.rangeClosed(0, (int) (chunks-1)).forEach( x -> {
				oneChunk.add(data.get(ai.get()));
				ai.incrementAndGet();
			});
			process(oneChunk);
			
		});
		stop();
		List<T> list = getSortedList();
		return list;
	}
	
	@Override
	public void process(List<T> data) {
		/** sort a list and put it in the queue **/
		GSort<T> sort = new GQuickSort<T>();
//		GSort<T> sort = new GMergeImpl<>();
		if ( DEBUG ){
		Utility.p("calling process data size is:" + data.size());
		}
		
		/** default sort method is quicksort unless this was specified via constructor **/
		if ( sorter == null ) {
		futs.add(sorterexec.submit(new Sorter<T>(data, queue,sort::sort)));
		} else {
			futs.add(sorterexec.submit(new Sorter<T>(data, queue,sorter)));

		}
	}

	public static void main(String... strings) {
		Utility.p("running concurrent sort");
		ConcurrentSort<Integer> concurrentSort = new ConcurrentSortImpl<>(100);
		IntStream.rangeClosed(0, 9).forEach(x -> {
			List<Integer> data = new ArrayList<>();
			IntStream.rangeClosed(0, 9).forEach(p -> {
				int i = ThreadLocalRandom.current().nextInt(0, 80);
				data.add(i);
			});
			concurrentSort.process(data);
		});
		concurrentSort.stop();
	}
}
