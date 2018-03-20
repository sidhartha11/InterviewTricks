/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.invokeanyall;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * Simple review of invokeAny and invokeAll.
 * Also test of isDone used against a list of futures returned 
 * via submitting a callable multiple times and storing the Futures
 * in a list.
 * </pre>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 14, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class TestInvokeAnyAll {
	private static final ExecutorService exec = Executors.newCachedThreadPool();

	/**
	 * 
	 */
	public TestInvokeAnyAll() {
		testSubmitList();
	}

	private void testSubmitList() {
		IntConsumer consumer = x -> {
			Utility.p("consumer accepting seed of " + x);
			int r = (2 * x)/x;
			
		};
		List<Task> tasks = new ArrayList<>();
		final List<Future<String>> futs = new ArrayList<>();
		IntStream.iterate(0, i -> i + 1).limit(10).forEach(p -> {
			Task t = new Task(consumer);
			futs.add(exec.submit(t));
		});
		Utility.p("size of list that was created=" + futs.size());
		
		/** iterate thru the tasks and check done status **/
		int donecntr = futs.size();
		int loopcntr = 0;
		while ( donecntr > 0 ){
			loopcntr++;
			for ( Future<String> ele : futs ){
				if ( ele.isDone()){
					donecntr--;
					Utility.p(":" + (loopcntr) + ":" + (donecntr) + ":-->isDone");
				}
			}
		}
		
		/** iterate thru the tasks executed **/
		counter = 0 ; 
		for ( Future<String> ele : futs ) {
			try {
				Utility.p((++counter) + " --> getting " + ele.get());
			} catch (InterruptedException | ExecutionException e) {
				Utility.p(counter + "--> exception caught");
				// e.printStackTrace();
			}
		}
		
		stopExector(exec);
		
	}

	/**
	 * invokeAny
     * <T> T invokeAny(Collection<? extends Callable<T>> tasks)
     * throws InterruptedException,
     * ExecutionException
     *
     * Executes the given tasks, returning the result of one that has 
     * completed successfully (i.e., without throwing an exception), 
     * if any do. Upon normal or exceptional return, tasks that have not 
     * completed are cancelled. The results of this method are undefined 
     * if the given collection is modified while this operation is in progress.
     * Type Parameters:
     * T - the type of the values returned from the tasks
     * Parameters:tasks - the collection of tasks
     * Returns:the result returned by one of the tasks
     * Throws:InterruptedException - if interrupted while waiting
     * NullPointerException - if tasks or any element task subject to execution 
     * is null
     * IllegalArgumentException - if tasks is empty
     * ExecutionException - if no task successfully completes
     * RejectedExecutionException - if tasks cannot be scheduled for execution
     */
	private void testInvokeAllAny() {
			IntConsumer consumer = x -> {
				Utility.p("consumer accepting seed of " + x);
				int r = (2 * x)/x;
				
			};
			List<Task> tasks = new ArrayList<>();
			List<Future<String>> futs = null;
			IntStream.iterate(0, i -> i + 1).limit(10).forEach(p -> {
				Task t = new Task(consumer);
				tasks.add(t);
			});
			
			/** invokeAll test **/
			String result=null;
			try {
				result = exec.invokeAny(tasks);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Utility.p("task that executed returned " + result);
			stopExector(exec);
		}


	/**
	 * invokeAll
     * <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
     * throws InterruptedException
     *
     * Executes the given tasks, returning a list of Futures holding their 
     * status and results when all complete. Future.isDone() is true for 
     * each element of the returned list. Note that a completed task could 
     * have terminated either normally or by throwing an exception. The 
     * results of this method are undefined if the given collection is 
     * modified while this operation is in progress.
     * 
     * Type Parameters:T - the type of the values returned from the tasks
     * Parameters:tasks - the collection of tasks
     * Returns:a list of Futures representing the tasks, 
     * in the same sequential order as produced by the iterator for the given 
     * task list, each of which has completed
     * Throws:InterruptedException - if interrupted while waiting, 
     * in which case unfinished tasks are cancelled
     * NullPointerException - if tasks or any of its elements are null
     * RejectedExecutionException - if any task cannot be scheduled for execution
	 * 
	 */
	int counter = 0 ; 
	private void testInvokeAll() {
		IntConsumer consumer = x -> {
			Utility.p("consumer accepting seed of " + x);
			int r = (2 * x)/x;
			
		};
		List<Task> tasks = new ArrayList<>();
		List<Future<String>> futs = null;
		IntStream.iterate(0, i -> i + 1).limit(10).forEach(p -> {
			Task t = new Task(consumer);
			tasks.add(t);
		});
		
		/** invokeAll test **/
		try {
			futs=exec.invokeAll(tasks);
			Utility.p("size of list that was returned=" + futs.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/** iterate thru the tasks and check done status **/
		int donecntr = futs.size();
		int loopcntr = 0;
		while ( donecntr > 0 ){
			loopcntr++;
			for ( Future<String> ele : futs ){
				if ( ele.isDone()){
					donecntr--;
					Utility.p((loopcntr) + ":" + (donecntr) + ":-->isDone");
				}
			}
		}
		
		/** iterate thru the tasks executed **/
		counter = 0 ; 
		for ( Future<String> ele : futs ) {
			try {
				Utility.p((++counter) + " --> getting " + ele.get());
			} catch (InterruptedException | ExecutionException e) {
				Utility.p(counter + "--> exception caught");
				// e.printStackTrace();
			}
		}
		
		stopExector(exec);
	}

	private void stopExector(ExecutorService exec) {
		exec.shutdown();
		try { 
		while (!exec.awaitTermination(1, TimeUnit.SECONDS)) {
			Utility.p("waiting...");
		}
		} catch ( Throwable t ) { 
			t.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestInvokeAnyAll();

	}

	/**
	 * 
	 */
	private static void simpleTest() {
		IntConsumer consumer = x -> {
			Utility.p("consumer accepting seed of " + x);
			int r = (2 * x)/x;
			
		};
		List<Future<String>> futs = new ArrayList<>();
		Task t = new Task(consumer);
		futs.add(exec.submit(t));
		try {
			Utility.p(futs.get(0).get());
		} catch (InterruptedException | ExecutionException e) {
			Utility.p("Exception discovered");
			e.printStackTrace();
		}
		
		exec.shutdown();
		try {
			while ( !exec.awaitTermination(1, TimeUnit.SECONDS)){
				Utility.p("waiting.....");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static class Task implements Callable<String> {
 
		
		private IntConsumer  consumer;

		public Task(IntConsumer consumer){
			this.consumer = consumer;
		}

		@Override
		public String call() throws Exception {
			/** generate a random value between 0 and some number **/
			int n = ThreadLocalRandom.current().nextInt(0,2);
			
//			Utility.p("seed sent=" + n);
			/** execute the consumer lambda **/
			TimeUnit.SECONDS.sleep(10);
			consumer.accept(n);
			return String.valueOf(n);
		}
		
	}

}
