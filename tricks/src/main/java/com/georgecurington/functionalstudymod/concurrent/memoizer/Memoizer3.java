package com.georgecurington.functionalstudymod.concurrent.memoizer;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre>
 * This attempt at memoization uses a Future:
 * A Future represents the result of an asynchronous computation. 
 * Methods are provided to check if the computation is complete, 
 * to wait for its completion, and to retrieve the result of the computation. 
 * The result can only be retrieved using method get when the computation has completed, 
 * blocking if necessary until it is ready. Cancellation is performed by the cancel method. 
 * Additional methods are provided to determine if the task completed normally or was cancelled. 
 * Once a computation has completed, the computation cannot be cancelled. 
 * If you would like to use a Future for the sake of cancellability but not 
 * provide a usable result, you can declare types of the form Future<?> and return null as a result 
 * of the underlying task.
 * 
 * This works by instead of putting the actual computed value in the ConcurrentHashMap,
 * a Future<V> is placed there instead. The key is still the key; nothing changes there.
 * The major change is this: Now if the key is not in the cache, ie. returns null, a Future<V>
 * is created and quickly put into the cache. This covers most of the timing hole
 * that was in Memoizer2. Abeilt, there is still an extremely miniscule timing hole that probably
 * has zero probablity of ever being hit. If another thread comes along and finds a future 
 * in the cache, it will do a f.get() and simply wait until the computation is completed.
 * Note: The small timing hole can be further refined using a putIfAbsent in concurrenthashmap.
 * The final implementation will employ this feature.
 * 
 * </pre>
 * 
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 * @param <A>
 *            generic parameter representing a value to an expensive function
 * @param <V>
 *            generic parameter representing the result of applying an expensive
 *            function to A
 */
public class Memoizer3<A, V> implements Computable<A, V> {

	private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
	private final Computable<A, V> c;

	/**
	 * @param c
	 */
	public Memoizer3(Computable<A, V> c) {
		super();
		this.c = c;
	}

	@Override
	public V compute(A arg) {
		Future<V> f = cache.get(arg);
		if (f == null) {
			Callable<V> eval = new Callable<V>() {

				@Override
				public V call() throws Exception {
					// TODO Auto-generated method stub
					return c.compute(arg);
				}
			};
			/** FutureTask implements Runnable **/
			FutureTask<V> ft = new FutureTask<>(eval);
			f = ft;
			cache.put(arg, ft);
			ft.run();
		}

		try {

			return f.get();
		} catch (ExecutionException | InterruptedException e) {
			throw Utility.launderThrowable(e.getCause());
		}
	}

}
