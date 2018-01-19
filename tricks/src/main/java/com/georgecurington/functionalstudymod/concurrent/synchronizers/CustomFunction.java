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
@FunctionalInterface
public interface CustomFunction<T,R> {
	R apply(T t);
}
