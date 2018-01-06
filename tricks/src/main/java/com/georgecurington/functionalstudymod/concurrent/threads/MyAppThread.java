/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.threads;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

/**
 * @author George Curington
 *
 */
public class MyAppThread extends Thread {
 
	public static final String DEFAULT_NAME = "MyAppThread";
	private static volatile boolean debugLifecycle = true;
	private static final AtomicInteger created = new AtomicInteger();
	private static final AtomicInteger alive = new AtomicInteger();
	private static final Logger logger=Logger.getLogger(MyAppThread.class);
	
	
	/**
	 * 
	 */
	public MyAppThread() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param target
	 */
	public MyAppThread(Runnable target) {
		this(target,DEFAULT_NAME);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param target
	 * @param name
	 */
	public MyAppThread(Runnable target, String name) {
		super(target, name + "-" + created.incrementAndGet());
		setUncaughtExceptionHandler (
		    new Thread.UncaughtExceptionHandler(){
		    	public void uncaughtException(Thread t,Throwable e){
		    		logger.fatal("UNCAUGHT in thread " + t.getName(),e);
		    	}
		    });
	}
	
	public void run(){
		// Copy debug flag to ensure consistent value throughout.
		boolean debug = debugLifecycle;
		if (debug) {
			logger.debug("Created " + getName());
		}
		
		try {
			alive.incrementAndGet();
			super.run();
		} finally {
			alive.decrementAndGet();
			if (debug){
				logger.debug("Exiting " + getName());
			}
		}
	}
	
	public static int getThreadsCreated() { return created.get();}
	public static int getThreadsAlive() { return alive.get();}
	public static boolean getDebug() { return debugLifecycle;}
	public static void setDebug(boolean b) { debugLifecycle = b; }
	
	/**
	 * @param name
	 */
	public MyAppThread(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param group
	 * @param target
	 */
	public MyAppThread(ThreadGroup group, Runnable target) {
		super(group, target);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param group
	 * @param name
	 */
	public MyAppThread(ThreadGroup group, String name) {
		super(group, name);
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param group
	 * @param target
	 * @param name
	 */
	public MyAppThread(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param group
	 * @param target
	 * @param name
	 * @param stackSize
	 */
	public MyAppThread(ThreadGroup group, Runnable target, String name, long stackSize) {
		super(group, target, name, stackSize);
		// TODO Auto-generated constructor stub
	}

}
