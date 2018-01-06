/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

/**
 * <pre>
 * <b>beforeExecute and afterExecute method</b>
 * Simple customization of ThreadPoolExecutor. Here only the 
 * beforeExecute and afterExecute methods are utilized to instrument
 * the ExecutorService. THread startup and thread end is logged.
 * Also the number of threads executed and the total time used is
 * logged. 
 * </pre>
 * <pre>
 * The basic method of customizing ExecutorService framework is as follows:
 * <ul>
 * <li>
 * extend ThreadPoolExecutor and override various instrumentation methods that hook into<br>
 * the lifecycle of the tasks that thread execute.
 * </li> 
 * <li>
 * Pass a custom ThreadFactory implementation to the customized ThreadPoolExecutor to give<br> each 
 * thread executed a "proper" factory derived name.<br>This is done by  implementing the newThread method of ThreadFactory.
 * </li> 
 * <li>
 * Create a custom Thread class for use by the newThread method of ThreadFactory. This is where the name of the<br>
 * of the thread is actually set up. There are several other methods of thread that can be overriden to further<br>
 * custimize your Executor service. @see setUncaughtExceptionHandler 
 * </li>  
 * </ul>
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 * 
 *
 */
public class TimingThreadPool extends ThreadPoolExecutor {
	private static final Logger logger = Logger.getLogger(TimingThreadPool.class);

	private static final boolean DEBUG = true;
	
	/**
	 * Since the thread that starts this task will be the same thread
	 * that ends this task, you can use ThreadLocal to save 
	 * information from the begging and end of execution of the
	 * task.
	 */
	private final ThreadLocal<Long> startTime = 
			new ThreadLocal<Long>();
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();
	
	/**
	 * INSTRUMENTATION AREA. 
	 */
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		if (DEBUG ) {
		logger.info(String.format("Thread %s: start %s", t,r));
		}
		/** set the starting time of the task for this thread **/
		startTime.set(System.nanoTime());
	}
	
	/** 
	 * <pre>
	 * This method is overriden from ThreadPoolExecutor and is used for debugging.
	 * The amount of time this task executed within the thread is logged.
	 * Also , if the thread threw an uncaught exception, that too is logged.
	 * </pre>
	 * @param Runnable r The exiting task
	 * @param Throwable t If non-null, then this task threw an uncaught Exception.
	 * @see java.util.concurrent.ThreadPoolExecutor#afterExecute(java.lang.Runnable, java.lang.Throwable)
	 */
	protected void afterExecute(Runnable r, Throwable t){
		try {
			long endTime = System.nanoTime();
			long taskTime = endTime - startTime.get();
			if (DEBUG ) {
				logger.info(String.format("Task %s: end executed for %d ms", r,taskTime));
				}
			numTasks.incrementAndGet();
			totalTime.addAndGet(taskTime);
			if ( t != null ){
				logger.info(String.format("task %s threw an uncaught exception:%s", r,t.getMessage()));
			}
		} finally {
			super.afterExecute(r,t);
		}
	}
	
	/**
	 * <pre>
	 * This method is overriden from ThreadPoolExecutor.
	 * It is mainly used for debugging purposes and logs the 
	 * termination of the executor service
	 * </pre>
	 * @see java.util.concurrent.ThreadPoolExecutor#terminated()
	 */
	protected void terminated(){
		try {
			logger.info(String.format("Terminated: avg time=%dns", 
					totalTime.get()));
		} finally {
			super.terminated();
		}
	}
	
	/**
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 */
	public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 * @param threadFactory
	 */
	public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 * @param handler
	 */
	public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 * @param threadFactory
	 * @param handler
	 */
	public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
		// TODO Auto-generated constructor stub
	}
	

}
