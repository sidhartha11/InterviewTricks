/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.memoizer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class Memoizer2<A,V> implements Computable<A, V> {
	private final Map<A,V> cache = new ConcurrentHashMap<A,V>();
	private final Computable<A,V> c;

	/**
	 * <pre>
	 * This method will compute an expensive value. If the value has
	 * previously been computed, it will be retrieved from the Thread Safe
	 * Concurrent Hash Map.
	 * Note: This deviates slightly from the original author's implementation
	 * by making use of a Functional Interface.
	 * This is an improvement of memoizer1 since the serialization introduced 
	 * in memoizer1 by using the synchronized keyword has been eliminated.
	 * There is still has a window of vulnerability. If two threads call compute at
	 * the same time, they both could end up computing the expensive function. This is 
	 * not a classical race condition but still poses a security risk if only one instance
	 * of the computed value was supposed to ever exist.
	 * The problem with Memoizer2 is that if one thread starts an expensive computation, 
	 * other threads are not aware that the computation is in progress and so 
	 * may start the same computation, as illustrated in Figure 5.3.
	 * </pre>
	 * 
	 * @param c The expensive function that computes the cached value.
	 */
	public Memoizer2(Computable<A, V> c) {
		super();
		this.c = c;
	}

	@Override
	public V compute(A arg){
		V result = cache.computeIfAbsent(arg,c::compute);
		return result;
	}

}
