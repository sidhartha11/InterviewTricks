/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * note: Runnable located in java.lang package
 * implementation notes:
 * BigInteger is an immutable class
 * public class BigInteger
 * extends Number
 * implements Comparable<BigInteger>,Serializable 
 * volatile usage:
 * Declaring a volatile Java variable means:
 * The value of this variable will never be cached thread-locally: 
 * all reads and writes will go straight to "main memory";
 * Access to the variable acts as though it is enclosed in a 
 * synchronized block, synchronized on itself.
 * 
 * </pre>
 * @author george
 *
 */
public class PrimeGenerator implements Runnable {

	private final List<BigInteger> primes = new ArrayList<>();
	private volatile boolean cancelled;
	/**
	 * 
	 */
	public PrimeGenerator() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		BigInteger p = BigInteger.ONE;
		while ( !cancelled ) {
			p = p.nextProbablePrime();
			synchronized(this) {
				primes.add(p);
			}
		}
	}
	
	public void cancel() { cancelled = true; }
	
	public synchronized List<BigInteger> get() {
		return new ArrayList<BigInteger>(primes);
	}
	
	/** generate a second of primes **/
	List<BigInteger> aSecondOfPrimes() throws InterruptedException {
		PrimeGenerator generator = new PrimeGenerator();
		new Thread(generator).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} finally {
			generator.cancel();
		}
		
		return generator.get();
	}
	
	public static void main(String...args) throws InterruptedException{
		PrimeGenerator prime = new PrimeGenerator();
		List<BigInteger> list = prime.aSecondOfPrimes();
		list.stream().forEach(System.out::println);
	}
}
