/**
 * 
 */
package com.georgecurington.functionalstudymod.mathstuff.recursive;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.georgecurington.functionalstudymod.concurrent.memoizer.Computable;

/**
 * <pre>
 * This class contains varous versions of a function to 
 * compute the Fibonacci series.
 * The series follows the general rule:
 * The Rule is xn = xn-1 + xn-2
 *
 * where:
 *
 *     xn is term number "n"
 *     xn-1 is the previous term (n-1)
 *     xn-2 is the term before that (n-2)
 *     
 * There three versions presented here:
 * </pre>
 * <UL>
 * <LI>iterative version</LI>
 * <LI>recursive version</LI>
 * <LI>cached version</LI>
 * </UL>
 * <pre>
 * In addition, I will try to present a BigInteger version
 * of each of the long versions.
 * Here are some fib sequences:
 * <b>
 * n =	0	1	2	3	4	5	6	7	8	9	10
 * xn =	0	1	1	2	3	5	8	13	21	34	55
 * </b>
 * </pre>
 * 
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class FibonacciSeries {
	private final Map<Long,Long> cache = new ConcurrentHashMap<>();
	private final Map<BigInteger,BigInteger> cacheBig = new ConcurrentHashMap<>();

	private final BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);
	/**
	 * 
	 */
	public FibonacciSeries() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param n the number who's fib we are calculating
	 * @return the fibinacci number for n
	 */
	public long fibIterative(int n) {
		long a = 0, b = 1;

		for (int i = 0; i < n; i++) {
			a = a + b;
			b = a - b;
		}
		return a;
	}
	
	/**
	 * <pre>
	 * Recursive version of the Fibonacci sequence.
	 * The termination condition is n <= 1 in which case we just return n.
	 * </pre>
	 * @param n
	 * @return
	 */
	public long fibRecursive(long n) {
		if ( n <= 1 ){
			return n;
		} else {
			return fibRecursive(n-1) + fibRecursive(n-2);
		}
	}
	
	public BigInteger fibRecursiveBig(BigInteger n) {
		if ( n.compareTo(BigInteger.ONE ) <= 0 ){
			return n;
		} else {
			return fibRecursiveBig(n.subtract(BigInteger.ONE)).add(fibRecursiveBig(n.subtract(TWO)));
		}
	}
	
	public BigInteger fibRecursiveBigCache(BigInteger n) {

		BigInteger a = cacheBig.computeIfAbsent(n, this::fibBigCache);
		return a;

	}
	
	public BigInteger fibBigCache(BigInteger n) {
		if ( n.compareTo(BigInteger.ONE ) <= 0 ){
			return n;
		} else if (cacheBig.containsKey(n)){
			return cacheBig.get(n);
		} else {
			return fibBigCache(n.subtract(BigInteger.ONE)).add(fibBigCache(n.subtract(TWO)));
		}
	}
	
	public long fibRecursiveCache(long n){
		Long a = cache.computeIfAbsent(n, this::fibCache);
		return a;
	}
	
	private long fibCache(long n){
		if ( n <= 1 ){
			return n;
		} else if (cache.get(n) != null  ){
			return cache.get(n);
		} else {
			return fibCache(n-1) + fibCache(n-2);
		}
	}
	

}
