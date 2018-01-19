/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;


/**
 * <pre>
 * Exposed in Concurrency in Practice:
 * Method timedRunBad and timedRun attempt to use various 
 * methods of creating a controllable timedRun.
 * This is a very fragile approach at creating such a run.
 * This is an attempt to create a method that will run a given
 * task for a specific period of time. If the task does not finish
 * in the given time period, the method interrupt the parent calling 
 * Thread ( or the parent thread even if it was not the actual Thread
 * that called this method .. if that is possible ).
 * 
 * Rules Violated:
 * 1. you should know a calling Thread's interruption policy before
 * interrupting it. 
 * 2. if the task finishes before the timed scheduled by the cancelling 
 * thread, who knows what might happen in the calling thread. (note:
 * it is possible to use the ScheduledFuture returned by schedule to 
 * fix problem (2))
 * </pre>
 * @author George Curington
 *
 */
public class TimedRun {

	/** create an instance of ScheduledExecutorService **/
	private static final ScheduledExecutorService cancelExec =
			Executors.newScheduledThreadPool(1);
	/** create an Executor Service for use in nonschedule timedRun **/
	private static final ExecutorService taskExec = 
			Executors.newCachedThreadPool();
	/**
	 * 
	 */
	public TimedRun() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Task t = new Task();

		try {
			timedRun(t,1000,TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("mainthread caught exception:" + e);
		}

	}
	
	/** timeRun using Futures **/
	
	public static void timedRun
	(
			Runnable r,
			long timeout,
			TimeUnit unit
	) throws InterruptedException {
		
 /** submit
  * Future<?> submit(Runnable task)
  * Submits a Runnable task for execution and returns a 
  * Future representing that task. The Future's get method will 
  * return null upon successful completion.
  * 
  * Parameters:
  * task - the task to submit
  * Returns:a Future representing pending completion of the task
  * Throws:RejectedExecutionException - if the task cannot be scheduled for execution
  * NullPointerException - if the task is null
  */
		Future<?> task = taskExec.submit(r);
		try {
			System.out.println("calling task.get(timeout,unit):" + task.get(timeout, unit));
		} catch (ExecutionException e) {
			System.out.println("ExecutionException");
			throw Utility.launderThrowable(e);
		} catch (TimeoutException e) {
			/** task will be cancelled below **/
			System.out.println("timeout");
			e.printStackTrace();
		} finally {
 /**
  * Attempts to cancel execution of this task. 
  * This attempt will fail if the task has already completed, 
  * (note: Concurrency in practice says:
  * cancelling a completed task has no effect)
  * has already been cancelled, 
  * or could not be cancelled for some other reason. 
  * If successful, and this task has not started when cancel is called, 
  * this task should never run. If the task has already started, 
  * then the mayInterruptIfRunning parameter determines whether the 
  * thread executing this task should be interrupted in an attempt 
  * to stop the task. 
  * After this method returns, subsequent calls to isDone() 
  * will always return true. Subsequent calls to isCancelled() 
  * will always return true if this method returned true.
  */
			System.out.println("task.cancelcalled");
			task.cancel(true);
		}
		
	}
	public static void timedRunUsingOldTechnique 
	(
		final Runnable r,
		long timeout,
		TimeUnit unit
	) throws InterruptedException {
		
	
	/**
	 * Create an inner class to execute the input Runnable
	 */
	class RethrowableTask implements Runnable {
		
		/** volatile Throwable variable to save an exception by Runnable **/		
		private volatile Throwable t;
		
		public void run() {
			try {
				r.run();
			} catch (Throwable t){
				this.t = t;
			}
		}
		/** method used to rethrow an Exception created by Runnable **/
		void rethrow() {
			if ( t != null ) {
				throw Utility.launderThrowable(t);
			} else if ( Thread.currentThread().isInterrupted()){
				System.out.println("we were interrupted");
				//throw new InterruptedException("Runnable died");
		} else {
			System.out.println("task ended without notification");
		}
		
	}	
	} // end inner class declaration
	
	/** create the RethrowableTask **/
	RethrowableTask task = new RethrowableTask();
	final Thread taskThread = new Thread(task);
	taskThread.start();

	/** schedule the cancel task **/
	cancelExec.schedule
	(
			new Runnable(){

				@Override
				public void run() {
					taskThread.interrupt();					
				}
				
			}, 
			timeout, 
			unit
	);
	
	/** join the inner task first **/
	/** note: join completes with unit times out
	 *  currently Java API cannot determine if this was
	 *  caused by an exception or the acual timeout
	 */
	System.out.println("joining on task:" + timeout);
	taskThread.join(unit.toMillis(timeout));
	System.out.println("joined");
	/** now rethrow the task, just in case an exception was caught **/
	task.rethrow();
	System.out.println("exiting");
	}
	
	public static class Task implements Runnable {

		@Override
		public void run() {
			try {
				System.out.println("Task is sleeping");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("Task was interrupted");
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void timedRunBad(
			Runnable r,
			long timeout , 
			TimeUnit unit) {
		
		/** get a pointer to the Thread that is curently running **/
		final Thread taskThread = Thread.currentThread();
		
		/** schedule a cancellation task to interrupt the currently running thread **/
		cancelExec.schedule(
				/** task to execute **/
				new Runnable(){
					@Override
					public void run() {
						taskThread.interrupt();
					}
				}, 
				/** time to wait before running **/
				timeout, 
				/** time units, millis, nanos etc **/
				unit
				);
		
		/** now execute the Runnable owned by the Thread **/
		r.run();
		
	}

}
