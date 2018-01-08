/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.memoizer;

/**
 * <pre>
 * Taken from the book "Java Concurrency in Practice".
 * Here I will try to implement the Memoizer example , document it 
 * and test it. I am making a slight change to the Author's original 
 * presentation by including a FunctionalInterface and using it in
 * the memoizer2 exercise.
 * ==========================================
 * The Computable<A, V> interface in Listing 5.16 describes a function with 
 * input of type A and result of type V. ExpensiveFunction, 
 * which implements Computable, takes a long time to compute its result; 
 * we'd like to create a Computable wrapper that remembers the 
 * results of previous computations and encapsulates the caching process. 
 * (This technique is known as memoization.)
 * 
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
@FunctionalInterface
public interface Computable<A,V> {

//	V compute(A arg) throws InterruptedException;
	V compute(A arg) ;

}
