/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.threads;

import java.util.concurrent.ThreadFactory;

/**
 * <pre>
 * Simple usage of implementing ThreadFactory by overriding
 * newThread, passing in a custom factory name that can be used
 * in a customized Thread object that this factory uses to 
 * execute threads in an Executor Service. 
 * </pre>
 * <pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 * </pre>
 *
 */
public class MyThreadFactory implements ThreadFactory {
	private final String poolName;
	/**
	 * 
	 */
	public MyThreadFactory(String poolName) {
		this.poolName = poolName;
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
	 */
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new MyAppThread(r,poolName);
		t.setDaemon(true); // do not prevent termination of program
		return t;
	}

}
