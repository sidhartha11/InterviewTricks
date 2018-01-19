/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.synchronizers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import static com.georgecurington.functionalstudymod.concurrent.synchronizers.TestHarness.*;

/**
 * <pre>
 * The interesting thing to notice here is how the Lambda
 * functions are used to test the various buffer implementations.
 * {@code 
 * producerFunction
 * consumerFunction
 * }
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 * @see com.georgecurington.functionalstudymod.concurrent.synchronizers.TestHarness
 */
public class Main {
	private static final int BUFFERSIZE=20;
	/**
	 * 
	 */
	public Main() {
		// callGrumpy();
		// callSleepy();
		// callBounded();
		callExplicit();
	}

	private void callGrumpy() {
		Buffer<String> buffer = new GrumpyBoundedBuffer<>(BUFFERSIZE);
		Worker<Buffer<String>, Void> producer = new Worker<>(producerFunction, buffer);
		Worker<Buffer<String>, Void> consumer = new Worker<>(consumerFunction, buffer);
		List<Worker<Buffer<String>, Void>> calls = new ArrayList<>(Arrays.asList(producer, consumer));
		callExecutor(calls);
	}
	
	private void callSleepy() {
		Buffer<String> buffer = new SleepyBoundedBuffer<>(BUFFERSIZE);
		Worker<Buffer<String>, Void> producer = new Worker<>(producerFunction, buffer);
		Worker<Buffer<String>, Void> consumer = new Worker<>(consumerFunction, buffer);
		List<Worker<Buffer<String>, Void>> calls = new ArrayList<>(Arrays.asList(producer, consumer));
		callExecutor(calls);
	}
	
	private void callBounded() {
		Buffer<String> buffer = new BoundedBuffer<>(BUFFERSIZE);
		Worker<Buffer<String>, Void> producer = new Worker<>(producerFunction, buffer);
		Worker<Buffer<String>, Void> consumer = new Worker<>(consumerFunction, buffer);
		List<Worker<Buffer<String>, Void>> calls = new ArrayList<>(Arrays.asList(producer, consumer));
		callExecutor(calls);
	}
	
	private void callExplicit() {
		Buffer<String> buffer = new ConditionBoundedBuffer<>(BUFFERSIZE);
		Worker<Buffer<String>, Void> producer = new Worker<>(producerFunction, buffer);
		Worker<Buffer<String>, Void> consumer = new Worker<>(consumerFunction, buffer);
		List<Worker<Buffer<String>, Void>> calls = new ArrayList<>(Arrays.asList(producer, consumer));
		callExecutor(calls);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}

	/**
	 * @param exec
	 * @param calls
	 */
	private void callExecutor(List<Worker<Buffer<String>, Void>> calls) {
		ExecutorService exec = Executors.newCachedThreadPool();
		List<Future<Void>> futures = null;
		try {
			futures = exec.invokeAll(calls);

			futures.stream().forEach(p -> {
				try {
					System.out.println("future.get():" + p.get());
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			exec.shutdown();
			while (!exec.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
				System.out.println("waiting");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static class Worker<T, R> implements Callable<R> {
		private final CustomFunction<T, R> supplier;
		private final T buffer;

		public Worker(CustomFunction<T, R> supplier, T buffer) {
			this.supplier = supplier;
			this.buffer = buffer;
		}

		@Override
		public R call() throws Exception {
			R r = null;
			try {
				p(" called");
				r = supplier.apply(buffer);
			} finally {
				p(" exiting");
			}
			return r;
		}
	}

	public static void p(String msg) {
		System.out.println(Thread.currentThread().getName() + msg);
	}
}