/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.georgecurington.functionalstudymod.utilities.Pair;
import com.georgecurington.functionalstudymod.utilities.PairImpl;

/**
 * @author George Curington
 *
 */
public final class Utility {
	public static final int CAPACITY = 100;
	public static final int NMBRTHREADS = 10;
	public static final ExecutorService executor = Executors.newFixedThreadPool(NMBRTHREADS);
	public static final ExecutorService taskExec = Executors.newFixedThreadPool(NMBRTHREADS);
	public static final int BUFSIZ = 1024;
	public static final long TIMEOUT = 10000;
	public static final TimeUnit UNIT = TimeUnit.MILLISECONDS;
	private static final Logger logger = Logger.getLogger(Utility.class);
	public static final long _2SECONDS = 2000;
	public static final long _10SECONDS = 10000;
	public static final long _4SECONDS = 4000;

	/**
	 * 
	 */
	private Utility() {
		// TODO Auto-generated constructor stub
	}

	public static void renderText(CharSequence source) {
		log("rendering text");
		try {
			Thread.sleep(ThreadLocalRandom.current().nextLong(250));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static void log(String msg) {
		logger.debug(msg);
	}


	public static RuntimeException launderThrowable(Throwable t) {
		if (t instanceof RuntimeException) {
			return (RuntimeException) t;
		} else if (t instanceof Error) {
			throw (Error) t;
		} else {
			throw new IllegalStateException("Not unchecked", t);
		}
	}

	/**
	 * Creates a thread pool that creates new threads as needed, but will reuse
	 * previously constructed threads when they are available, and uses the
	 * provided ThreadFactory to create new threads when needed.
	 * 
	 * @param threadFactory
	 *            the factory to use when creating new threads
	 * @return the newly created thread pool
	 * @throws NullPointerException
	 *             if threadFactory is null
	 */
	public static ExecutorService myCachedThreadPool(String poolName) {
		return new TimingThreadPool(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
				new MyThreadFactory(poolName));
	}

	public static ExecutorService myCachedThreadPool(int nThreads, String poolName) {
		return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(), new MyThreadFactory(poolName));
	}

	public static Pair<Future<?>,ExecutorService> oneOff(Callable<Void> c) {
		ExecutorService exec = myCachedThreadPool("oneOff");
		Future<?> f = exec.submit(c);
		Pair<Future<?>,ExecutorService> pair = new PairImpl<>(f,exec);
		return pair;
	}
	public static int calcNmbrThreads(int cores, double serviceP, double waitP) {
		/**
		 * threads = numberCores( 1 + (waittimeP/serviceP) waittimeP I think
		 * would be very small. serviceP should be very large. So I should get 8
		 * * ( 1 + .10/.90 ) In addition, I will add 1 to this formula to give
		 * an extra thread just in case one is suspended for any particular
		 * reasson.
		 */
		// int cores =Runtime.getRuntime().availableProcessors();
		// double serviceP = .90;
		// double waitP = .10;
		int nmbrThreads = (int) (cores * (1 + (waitP / serviceP))) + 1;
		return nmbrThreads;
	}

	public static void p(String string) {
		System.out.println(Thread.currentThread().getName() + ":" + string);
		
	}
	
	public static void p(Object string) {
		System.out.println(Thread.currentThread().getName() + ":" + string);
		
	}
	
	public static String centerNumber(int disp, int nmbr) {
		String n = String.valueOf(nmbr);
		int d = disp - n.length();
		StringBuilder acc = new StringBuilder();
		acc.append(nmbr);
		for ( int i = 0 ; i < d ; i++ ){
			acc.append(" ");
		}
		return acc.toString();
	}
	
	public static String convertBol(int disp, int sz , boolean b) {
		int d = disp - sz;
		StringBuilder acc = new StringBuilder();

		if ( b ) 
			acc.append("t"); 
		else 
			acc.append("f");
		for ( int i = 0 ; i < d ; i++ ){
			acc.append(" ");
		}
		return acc.toString();
	}
}
