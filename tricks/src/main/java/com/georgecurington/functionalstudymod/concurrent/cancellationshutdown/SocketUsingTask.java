/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import com.georgecurington.functionalstudymod.concurrent.threads.MyThreadFactory;

/**
 * @author george
 *
 */
public abstract class SocketUsingTask<V> implements CancellableTask<V> {
	private Socket socket;

	/**
	 * 
	 */
	protected synchronized void setSocket(Socket s) {
		socket = s;
	}

	public SocketUsingTask() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.georgecurington.functionalstudymod.concurrent.cancellationshutdown.
	 * CancellableTask#cancel()
	 */
	@Override
	public synchronized void cancel() {
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (IOException ignored) {

		}

	}

	public RunnableFuture<V> newTask() {
		return new FutureTask<V>(this) {
			@SuppressWarnings("finally")
			public boolean cancel(boolean mayInterruptIfRunning) {
				try {
					SocketUsingTask.this.cancel();
				} finally {
					return super.cancel(mayInterruptIfRunning);
				}
			}
		};
	}

	public static void main(String... strings) {
		ExecutorService exec = new CancellingExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>(), new MyThreadFactory("dummy"));
	}
	
	/**
	 * <pre>
	 * Need to create and set a Socket for this task to use
	 * Then you can experiment and test the cancel method that 
	 * can be executed on the Future that is returned via the 
	 * exec.submit call.
	 * </pre>
	 * @author george
	 *
	 */
	public static class Worker extends SocketUsingTask<Void> {

		@Override
		public Void call() throws Exception {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
