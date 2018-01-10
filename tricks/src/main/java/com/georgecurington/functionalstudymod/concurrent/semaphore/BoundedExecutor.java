/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.semaphore;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * <pre>
 * From Concurrency In Practice:
 * Using a Semaphore to bound a thread pool
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class BoundedExecutor {
	private final Executor exec;
	private final Semaphore semaphore;

	public BoundedExecutor(Executor exec, int bound) {
		this.exec = exec;
		this.semaphore = new Semaphore(bound);
	}

	public void submitTask(final Runnable command) throws InterruptedException {
		System.out.println("calling acquire");
		semaphore.acquire();
		System.out.println("acquired");
		try {
			exec.execute(new Runnable() {

				@Override
				public void run() {
					try {
						command.run();
					} finally {
						semaphore.release();
					}

				}

			});
		} catch (RejectedExecutionException e) {
			System.out.println("Exception:" + e.getMessage());
			semaphore.release();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(100);
		BoundedExecutor bexec = new BoundedExecutor(exec, 10);
		IntStream.range(0, 99).forEach(index -> {
			try {
				bexec.submitTask(new Runnable() {

					@Override
					public void run() {
						System.out.println("running");
					}
				});
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		exec.shutdown();
		try {
			while (!exec.awaitTermination(1000, TimeUnit.MILLISECONDS)) {

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
