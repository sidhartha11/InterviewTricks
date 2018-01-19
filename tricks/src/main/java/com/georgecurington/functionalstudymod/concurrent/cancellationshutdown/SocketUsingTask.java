/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import com.georgecurington.functionalstudymod.concurrent.threads.MyThreadFactory;


/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * From chapter 7. This is a task that implements a custom Callable defined
 * in CancellableTask. The sole purpose here is to allow the Callable to 
 * cancel an otherwise uninterruptible class that is processing a network 
 * related call. Basicaly, in this case the class will simply close the socket
 * when the cancel method is called on the Future that is return from an
 * executor submit call.
 * </pre>
 * <br>
 * <pre>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 19, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * @param <V>
 * </pre>
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
		/** submit the server task via ExecutorService **/
		ServerSocketTask ss = new ServerSocketTask(ServerSocketClientUtil.SERVERPORT);
		ClientCallableTask ct = new ClientCallableTask(ServerSocketClientUtil.SERVERPORT);
		
	
		ExecutorService exec = new CancellingExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>(), new MyThreadFactory("dummy"));
	
	
		Future<?> serverFuture = exec.submit(ss);
		Future<?> clientFuture = exec.submit(ct);
		List<Future<?>> allfuts =new ArrayList<>();
		allfuts.add(clientFuture);
		allfuts.add(serverFuture);
		Canceler canceler = new Canceler(allfuts);
		exec.submit(canceler);
		exec.shutdown();
		try {
			while (!exec.awaitTermination(1000, TimeUnit.MILLISECONDS)){
				System.out.println("waiting to terminate");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	static class Canceler implements Callable<Void> {

		private static final long ABIT = 2000;
		private final List<Future<?>> allfuts;
//		public Canceler(Future<?> fut){
//			this.fut = fut;
//		}
		public Canceler(List<Future<?>> allfuts) {
			this.allfuts = allfuts;
		}
		@Override
		public Void call() throws Exception {
			System.out.println("will wait a bit, then cancel the client");
			Thread.sleep(ABIT);
			allfuts.forEach(f -> {
				f.cancel(true);
			});
//			fut.cancel(true);
			return null;
		}
		
	}
}
