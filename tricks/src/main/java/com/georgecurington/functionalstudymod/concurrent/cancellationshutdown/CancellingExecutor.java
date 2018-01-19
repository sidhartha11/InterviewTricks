/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author george
 *
 */
public class CancellingExecutor extends ThreadPoolExecutor {

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 */
	public CancellingExecutor(int arg0, int arg1, long arg2, TimeUnit arg3, BlockingQueue<Runnable> arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 */
	public CancellingExecutor(int arg0, int arg1, long arg2, TimeUnit arg3, BlockingQueue<Runnable> arg4,
			ThreadFactory arg5) {
		super(arg0, arg1, arg2, arg3, arg4, arg5);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 */
	public CancellingExecutor(int arg0, int arg1, long arg2, TimeUnit arg3, BlockingQueue<Runnable> arg4,
			RejectedExecutionHandler arg5) {
		super(arg0, arg1, arg2, arg3, arg4, arg5);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 * @param arg6
	 */
	public CancellingExecutor(int arg0, int arg1, long arg2, TimeUnit arg3, BlockingQueue<Runnable> arg4,
			ThreadFactory arg5, RejectedExecutionHandler arg6) {
		super(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected<T> RunnableFuture<T> newTaskFor(Callable<T> callable){
		if ( callable instanceof CancellableTask ){
			return ((CancellableTask<T>) callable).newTask();
		} else {
			return super.newTaskFor(callable);
		}
	}

}
