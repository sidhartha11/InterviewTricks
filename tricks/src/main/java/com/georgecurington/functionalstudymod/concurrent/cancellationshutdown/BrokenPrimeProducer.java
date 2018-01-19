/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <pre>
 * Thread is part of the java.lang package
 * Items in this implementation:
 * BlockingQueue is an interface in the java.util.concurrent package.
 * Interface BlockingQueue<E>
 * All Superinterfaces
 * Collection<E>, Iterable<E>, Queue<E>
 * All Subinterfaces
 * BlockingDeque<E>, TransferQueue<E>
 * 
 * All Known Implementing Classes:
 * ArrayBlockingQueue, DelayQueue, LinkedBlockingDeque, 
 * LinkedBlockingQueue, LinkedTransferQueue, 
 * PriorityBlockingQueue, SynchronousQueue
 * 
 * public interface BlockingQueue<E>
 * extends Queue<E>
 * 
 * Note:
 * void Run method of Thread does not require that you implement
 * it. 
 * </pre>
 * 
 * @author george
 *
 */
public class BrokenPrimeProducer extends Thread {
	private static final int MAXQ = 10;
	private final BlockingQueue<BigInteger> queue;
	private volatile boolean cancelled = false;
	/**
	 * 
	 */
	public BrokenPrimeProducer(){
		this.queue = null;
	}
	public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue ; 
	}

	/**
	 * @param target
	 */
	public BrokenPrimeProducer(Runnable target) {
		super(target);
		queue = null;
	}

	/**
	 * @param name
	 */
	public BrokenPrimeProducer(String name) {
		super(name);
		queue = null;
	}

	/**
	 * @param group
	 * @param target
	 */
	public BrokenPrimeProducer(ThreadGroup group, Runnable target) {
		super(group, target);
		queue = null;
	}

	/**
	 * @param group
	 * @param name
	 */
	public BrokenPrimeProducer(ThreadGroup group, String name) {
		super(group, name);
		queue = null;
	}

	/**
	 * @param target
	 * @param name
	 */
	public BrokenPrimeProducer(Runnable target, String name) {
		super(target, name);
		queue = null;
	}

	/**
	 * @param group
	 * @param target
	 * @param name
	 */
	public BrokenPrimeProducer(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
		queue = null;
	}

	/**
	 * @param group
	 * @param target
	 * @param name
	 * @param stackSize
	 */
	public BrokenPrimeProducer(ThreadGroup group, Runnable target, String name, long stackSize) {
		super(group, target, name, stackSize);
		queue = null;
	}
	
	public void run() {
		try { 
			BigInteger p = BigInteger.ONE;
			int cntr=0;
			while (!cancelled ) {
				p = p.nextProbablePrime();
				BigInteger np = p;
				System.out.println((++cntr) + ":putting " + np);
				queue.put(np);
			}
		} catch ( InterruptedException consumed ){}
	}
	
	public void cancel() { cancelled = true; }
	
	void consumePrimes() throws InterruptedException {
		BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<>(MAXQ);
		BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
		producer.start();
		Thread.sleep(1000);
		try {
			while(needMorePrimes()) {
				consume(primes.take());
			}
		} finally {
			producer.cancel();
		}
	}
	private void consume(BigInteger take) {
		System.out.println("consumed prime:" + take);
		
	}
	int cntr=0;
	private boolean needMorePrimes() {
		return false;
//		cntr++;
//		if ( cntr > 5 )
//		return false;
//		return true;
	}
	
	public static void main(String...strings ) throws InterruptedException{
		BrokenPrimeProducer bpp = new BrokenPrimeProducer();
		bpp.consumePrimes();
	}

}
