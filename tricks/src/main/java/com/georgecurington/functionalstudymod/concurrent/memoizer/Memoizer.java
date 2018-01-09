/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.memoizer;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * @author george
 *
 */
public class Memoizer<A, V> implements Computable<A, V> {

	private static final boolean DEBUG = true;

	private final ConcurrentHashMap<A, Future<V>> cache = 
			new ConcurrentHashMap<>();
	
	private final Computable<A,V> c;
	/**
	 * @param c
	 */
	public Memoizer(Computable<A, V> c) {
		super();
		this.c = c;
	}
	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.concurrent.memoizer.Computable#compute(java.lang.Object)
	 */
	@Override
	public V compute(A arg) {
		while ( true ) {
			Future<V> f = cache.get(arg);
			if (f == null) {
				if ( DEBUG ) {
					System.out.println("f was null");
				}
				Callable<V> eval = new Callable<V>() {
					public V call() throws InterruptedException {
						return c.compute(arg);
					}
				};
				FutureTask<V> ft = new FutureTask<V>(eval);
				f = cache.putIfAbsent(arg, ft);
				if (f == null) {
					f = ft;
					ft.run();
				}
			} else {
				if ( DEBUG ) {
					System.out.println("processing future " + f);
				}
			}
				try {
					return f.get();
				} catch (CancellationException | InterruptedException e){
					cache.remove(arg,f);
				} catch (ExecutionException e){
					throw Utility.launderThrowable(e.getCause());
				}
			}
		}
}
