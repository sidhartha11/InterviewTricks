/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.deadlock1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * @author george
 *
 */
public class Main {

	public Main() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		explicitLockUsage();
//		dynamicLockOrderingWithOutDeadLock();
		
	}

	private static void explicitLockUsage() {
		/** create an instance of the locking class **/
		ExplicitLockExample lock = new ExplicitLockExample();
		/** create an instance of the worker task **/
		Account myAccount = new AccountImpl(100l);
		Account yourAccount = new AccountImpl(100l);
		Worker3 worker1 = new Worker3(CallType.myAccountYourAccountExplicit, lock,myAccount,yourAccount,1000l,TimeUnit.NANOSECONDS);
		Worker3 worker2 = new Worker3(CallType.yourAccountMyAccountExplicit, lock,myAccount,yourAccount,10001,TimeUnit.NANOSECONDS);
		List<Worker3> f1 = Arrays.asList(worker1,worker2);
		/** submit the workers to an ExecutorService **/
		ExecutorService exec = Executors.newCachedThreadPool();
		/** execute a number of tasks */
		IntStream.range(0,2000).forEach(p -> {
			List<Worker3> f = Arrays.asList(worker1,worker2);
			f.stream().forEach(exec::submit);
		});
		
		exec.shutdown();
		try {
			while(!exec.awaitTermination(1000, TimeUnit.MILLISECONDS)){
				Utility.p("waiting for shutdown");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 */
	private static void dynamicLockOrderingWithOutDeadLock() {
		/** create an instance of the locking class **/
		DynamicLockOrderExample lock = new DynamicLockOrderExample();
		/** create an instance of the worker task **/
		Account myAccount = new AccountImpl(100l);
		Account yourAccount = new AccountImpl(100l);
		Worker2 worker1 = new Worker2(CallType.myAccountYourAccount, lock,myAccount,yourAccount);
		Worker2 worker2 = new Worker2(CallType.yourAccountMyAccount, lock,myAccount,yourAccount);
		List<Worker2> f1 = Arrays.asList(worker1,worker2);
		/** submit the workers to an ExecutorService **/
		ExecutorService exec = Executors.newCachedThreadPool();
		/** execute a number of tasks */
		IntStream.range(0,2000).forEach(p -> {
			List<Worker2> f = Arrays.asList(worker1,worker2);
			f.stream().forEach(exec::submit);
		});
		
		exec.shutdown();
		try {
			while(!exec.awaitTermination(1000, TimeUnit.MILLISECONDS)){
				Utility.p("waiting for shutdown");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private static void dynamicLockOrderingWithDeadLock() {
		dynamicLockOrderingWithOutDeadLock();
	}

	/**
	 * <pre>
	 * From Concurrency In Practice. 
	 * 10.1.1. Lock-ordering Deadlocks
	 * 
	 */
	private static void lockOrderingDeadLocks() {
		/** create an instance of the locking class **/
		LeftRightDeadLock lock = new LeftRightDeadLock();
		/** create an instance of the worker task **/
		Worker worker1 = new Worker(CallType.callLeft, lock);
		Worker worker2 = new Worker(CallType.callRight, lock);
		List<Worker> f1 = Arrays.asList(worker1,worker2);
		/** submit the workers to an ExecutorService **/
		ExecutorService exec = Executors.newCachedThreadPool();
		/** execute a number of tasks */
		IntStream.range(0,9).forEach(p -> {
			List<Worker> f = Arrays.asList(worker1,worker2);
			f.stream().forEach(exec::submit);
		});
		
		exec.shutdown();
		try {
			while(!exec.awaitTermination(1000, TimeUnit.MILLISECONDS)){
				Utility.p("waiting for shutdown");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
