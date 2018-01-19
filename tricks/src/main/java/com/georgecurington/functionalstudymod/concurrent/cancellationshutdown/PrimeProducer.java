/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author george
 *
 */
public class PrimeProducer extends Thread {
	
	private final BlockingQueue<BigInteger> queue;
	
	PrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while ( !Thread.currentThread().isInterrupted()){
				queue.put(p = p.nextProbablePrime());
				System.out.println("put prime");
			}
		} catch (InterruptedException consumed) {}
	}
	
	public void cancel() { interrupt(); }
	
	public static void main(String...strings ) throws InterruptedException{
		BlockingQueue<BigInteger> queue = new LinkedBlockingQueue<>();
		PrimeProducer pp = new PrimeProducer(queue);
		try { 
		pp.start();
		Thread.sleep(4000);
		} finally {
			pp.cancel();
			pp.join();
		} 
	}

}
