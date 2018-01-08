/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.memoizer;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * The first naive and inefficient approach to creating a scalable cache.
 * This approach used a simple HashMap. And since HashMap is not Thread Safe,
 * the implementation forces total synchronization by locking the 
 * instance object via the implicit synchronized construct. 
 * </pre>
 * 
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class Memoizer1<A,V> implements Computable<A,V> {

	private final Map<A,V> cache = new HashMap<A,V>();
	private final Computable<A,V> c;
	/**
	 * 
	 */
	public Memoizer1(Computable<A,V> c) {
		this.c = c;
	}

	/**
	 * <pre>
	 * This approach of surrounding the instance with synchronized is very poor due
	 * to the high contention caused by multiple threads trying to grab the object lock.
	 * If the computation takes a long time, many threads will have to wait for their 
	 * turn to enter the compute method.
	 * </pre>
	 * @param arg The value used as argument to the expensive compute function
	 * @see com.georgecurington.functionalstudymod.concurrent.memoizer.Computable#compute(java.lang.Object)
	 */
	@Override
	public synchronized V compute(A arg) {
		V result = cache.get(arg);
		if ( result == null ) {
			result = c.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}
}
