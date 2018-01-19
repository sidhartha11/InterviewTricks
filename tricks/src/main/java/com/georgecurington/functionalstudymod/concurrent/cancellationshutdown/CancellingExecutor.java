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
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * This class is used to allow a customized cancel method to be called.
 * The cancel method is implemented by a task that implements CancellableTask.
 * By overriding the newTaskFor hook, we have an opportunity to call
 * a CancellableTask if the task being implements Cancellable as determined
 * by the instanceof test below:
 * If it is a normal task, the default newTaskFor is simply called.
 * </pre>
 * <pre>
 * 	{@code
 *	protected<T> RunnableFuture<T> newTaskFor(Callable<T> callable){
 *		if ( callable instanceof CancellableTask ){
 *			return ((CancellableTask<T>) callable).newTask();
 *		} else {
 *			return super.newTaskFor(callable);
 *		}
 *	}
 *  }
 *  
 *  Briefly, the various declarations found in JDK source go as follows:
 *  
 * public interface Executor {
 * 
 * @param command the runnable task
 * @throws RejectedExecutionException if this task cannot be
 * accepted for execution
 * @throws NullPointerException if command is null
 * 
 *   void execute(Runnable command);
 * }
 *  
 *  public interface ExecutorService extends Executor {
 *  
 *  public class ThreadPoolExecutor extends AbstractExecutorService {
 *  
 *  
 *  public abstract class AbstractExecutorService implements ExecutorService {
 *  
 *       
 *  protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
 *        return new FutureTask<T>(runnable, value);
 *  }
 *  
 *  protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
 *        return new FutureTask<T>(callable);
 *  }
 *  
 *</pre>
 * <br>
 * <pre>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 19, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * @see com.georgecurington.functionalstudymod.concurrent.cancellationshutdown.CancellableTask
 * @see java.util.concurrent.AbstractExecutorService
 * </pre>
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
