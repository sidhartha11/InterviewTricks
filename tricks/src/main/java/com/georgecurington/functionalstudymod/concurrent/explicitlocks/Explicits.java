/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.explicitlocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 30, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public interface Explicits {

	static AtomicInteger ai = new AtomicInteger(0);
	static long TIMEOUTV = 10000;
	static Boolean trySendOnShared(Lock lock,String message, long timeout, TimeUnit unit) throws InterruptedException {
//		long nanosToLock = unit.toNanos(timeout)
//				- estimatedNanosToSend(message);
		
		long nanosToLock = timeout;
		
		/**
		 * tryLock
         * public boolean tryLock(long timeout,
         *                       TimeUnit unit)
         *                throws InterruptedException
         *
         * Acquires the lock if it is not held by another thread within the given waiting time and the current thread has not been interrupted. 
         * Acquires the lock if it is not held by another thread and returns immediately with the value true, 
         * setting the lock hold count to one. If this lock has been set to use a fair ordering policy then an available lock will 
         * not be acquired if any other threads are waiting for the lock. This is in contrast to the tryLock() method. 
         * If you want a timed tryLock that does permit barging on a fair lock then combine the timed and un-timed forms together: 
         * 
         *  
         * if (lock.tryLock() ||
         * lock.tryLock(timeout, unit)) {
         * ...
         * }
         *
		 */
		Utility.p("nanaseconds being used = " + nanosToLock);
		if ( !lock.tryLock(nanosToLock, TimeUnit.MILLISECONDS)){
			Utility.p("failed to acquire lock before timeout");
			return new Boolean(false);
		}
		
		try {
			return new Boolean(sendOnSharedLine(message));
		} finally {
			lock.unlock();
		}
	}

	static Boolean sendOnSharedLine(String message) throws InterruptedException {
		TimeUnit.NANOSECONDS.sleep(2000);
		Utility.p((ai.incrementAndGet()) + "---sending shared line message");
		return true;
	}

	int FACTOR = 100;

	static long estimatedNanosToSend(String message) {
		long l = message == null ? 0 : (message.length() * FACTOR);
		return l;
	}

}
