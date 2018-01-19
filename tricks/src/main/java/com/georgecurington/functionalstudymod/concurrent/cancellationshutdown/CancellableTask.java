/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * <pre>
 * When a Callable is submitted to an ExecutorService, submit
 * returns a Future that can be used to cancel the task.
 * ThreadPoolExecutor (since java 6) has a hook called
 * newTaskFor.
 * The newTaskFor hook is a factory method that creates the 
 * Future representing the task. It returns a RunnableFuture, an
 * interface that extends both Future and Runnable and is implemented
 * by FutureTask. 
 * Cusomizing the task Future allows you to override Future.cancel.
 * Custom cancellation code can perform logging or gather statistics
 * on cancellation and can also be used to cancel activities that 
 * are not responsive to interruption.
 * ReaderThread encapsulates cancellation of 
 * socket-using threads by overriding interrupt; the same can be done
 * for tasks by overriding Future.cancel.
 * 
 * CancellableTask in Listing 7.12 deines a CancellableTask interface
 * that extends Callable and adds a cancel method and a newTask
 * factory method for constructing a RunnableFuture.
 * 
 * </pre>
 * 
 * @author george
 *
 */
public interface CancellableTask<V> extends Callable<V> {
	void cancel();
	RunnableFuture<V> newTask();
}
