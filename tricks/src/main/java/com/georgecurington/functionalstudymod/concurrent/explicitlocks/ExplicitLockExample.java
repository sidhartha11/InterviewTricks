/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.explicitlocks;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.georgecurington.functionalstudymod.concurrent.deadlock1.Account;
import com.georgecurington.functionalstudymod.concurrent.deadlock1.InsufficientFundsException;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * This example is from chapter 13 that covers Explicit Locks.
 * It solves the DeadLock caused by incorrect lock ordering by using
 * Reentrant Locks instead of a particular lock ordering.
 * </pre>
 * 
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 30, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class ExplicitLockExample {

	/**
	 * 
	 */
	public ExplicitLockExample() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean transferMoney ( Account fromAcct,
			Account toAcct ,
			Long amount,
			long timeout,
			TimeUnit unit
			) throws InsufficientFundsException , InterruptedException {
		long fixedDelay = getFixedDelayComponentNanos(timeout,unit);
		long randMod = getRandomDelayModulusNanos(timeout,unit);
		long stopTime = System.nanoTime() + unit.toNanos(timeout);
		
		while (true) {
			/**
			 * boolean tryLock()
             * Acquires the lock only if it is free at the time 
             * of invocation. Acquires the lock if it is available and returns immediately with the value true. If the lock is not available 
             * then this method will return immediately with the value false. 
			 */
			if (fromAcct.getLock().tryLock()) {
				try {
					if (toAcct.getLock().tryLock()) {
						try {
							if (fromAcct.getBalance().compareTo(amount) < 0 ) {
								throw new InsufficientFundsException();
							} else {
								fromAcct.debit(amount);
								toAcct.credit(amount);
								return true;
							}
						} finally {
							toAcct.getLock().unlock();
						}
					}
				} finally {
					/**
					 * All Explicit should released in finally blocks
					 * A Lock implementation will usually impose restrictions 
					 * on which thread can release a lock 
					 * (typically only the holder of the lock can release it) 
					 * and may throw an (unchecked) exception if the 
					 * restriction is violated. Any restrictions and the 
					 * exception type must be documented by that Lock 
					 * implementation.
					 * 
					 */
					fromAcct.getLock().unlock();
				}
			}
			if (System.nanoTime() > stopTime ) {
				return false;
			}
			TimeUnit
			.NANOSECONDS
			.sleep(fixedDelay 
					+ (ThreadLocalRandom.current().nextLong()
					 % randMod));
		}
	}

	private long getRandomDelayModulusNanos(long timeout, TimeUnit unit) {
		// TODO Auto-generated method stub
		return timeout;
	}

	private long getFixedDelayComponentNanos(long timeout, TimeUnit unit) {
		return timeout;
	}

}
