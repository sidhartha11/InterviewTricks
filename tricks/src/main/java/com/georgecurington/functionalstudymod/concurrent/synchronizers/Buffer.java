/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.synchronizers;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public interface Buffer<T> {

	T take() throws Exception, InterruptedException;

	void put(T v) throws Exception, InterruptedException;

}
