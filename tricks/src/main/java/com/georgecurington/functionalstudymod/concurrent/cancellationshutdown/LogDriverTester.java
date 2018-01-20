/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.naming.OperationNotSupportedException;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.testdata.TestData;
import com.georgecurington.functionalstudymod.utilities.Pair;

/**
 * <pre>
 * <p><b>INTERVIEW TRICKS</b></p>
 * </pre>
 * <p>
 * ================================================
 * </p>
 * 
 * <pre>
 * This example illustrates a logging service, simple implementation.
 * Instantiation of the service first sends some implementation of a 
 * Writer to the constructor. Within the constructor, a BlockinQueue
 * is instantiated, LinkedBlockingQueue implementation passing an initial
 * compacity and the constructor argument.An internal thread that does the 
 * actual writing of the log is also instantiated but not started at that 
 * time. The public api contains a log method and a start method, i.e. for 
 * starting the internal logging thread. 
 * In this simple design, there is not much control over the service. It 
 * simply runs forever, logging messages that are passed in via the log 
 * method and put into the log queue.
 * For clarity, I have added a counter and a few additional output messages
 * to the Author's original implementation.
 * 
 * Interesting Interfaces Used In This Example:
 * BlockingQueue<T> 
 * and
 * LinkedBlockingQueue<T>
 * some detail regarding BlockingQueue<T> and LinkedBlockingQueue<T>
 * located in java.util.concurrent package. BlockingQueue<T> extends Queue<T>
 * BlockingQueue<T> Implementations are Thread Safe.
 * put and take block depending on queue state.
 * add and remove along with element may throw an exception depending on queue state
 * poll and offer along with peek return a special value depending on queue state.
 * Illegal to use null values in BlockingQueues.
 * also .. the head and tail of concurrent queues are guarded by two different 
 * explicit locks. Here is a brief look at the source set up:
 * </pre>
 * 
 * <pre>
 * {
 * 	&#64;code
 * 	private final ReentrantLock takeLock = new ReentrantLock();
 * 
 * 	private final Condition notEmpty = takeLock.newCondition();
 *
 * 	private final ReentrantLock putLock = new ReentrantLock();
 * 
 * 	private final Condition notFull = putLock.newCondition();
 * }
 * 
 * </pre>
 * 
 * <br
 * 
 * <pre>
 * &#64;author George Curington
 * &#64;version 1.0.0
 * &#64;since Jan 19, 2018
 * &#64;see https://github.com/sidhartha11/InterviewTricks
 * &#64;see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * </pre>
 */
public class LogDriverTester {

	public static class LogWriterWithoutStop implements Log {
		AtomicInteger ai = new AtomicInteger(0);
		private static final int CAPACITY = 10;
		private final BlockingQueue<String> queue;
		private final LoggerThread logger;

		/**
		 * 
		 */
		public LogWriterWithoutStop(Writer writer) {
			this.queue = new LinkedBlockingQueue<String>(CAPACITY);
			this.logger = new LoggerThread(writer);
		}

		@Override
		public void stop() {
			throw new IllegalAccessError("not supported");

		}

		@Override
		public void start() {
			logger.start();
		}

		@Override
		public void log(String msg) throws InterruptedException {
			queue.put(ai.incrementAndGet() + "put ->" + msg);
		}

		private class LoggerThread extends Thread {
			private final PrintWriter writer;

			public LoggerThread(Writer writer) {
				this.writer = (PrintWriter) writer;
			}

			public void run() {
				try {
					while (true) {
						writer.println(ai.get() + "get ->" + queue.take());
						writer.flush();
					}
				} catch (InterruptedException ignored) {

				} finally {
					writer.close();
				}
			}

		}
	}

	public static class LogWriter implements Log {
		AtomicInteger ai = new AtomicInteger(0);
		private static final int CAPACITY = 10;
		private final BlockingQueue<String> queue;
		private final LoggerThread logger;
		private boolean isShutdown;
		private int reservations;

		/**
		 * 
		 */
		public LogWriter(Writer writer) {
			this.queue = new LinkedBlockingQueue<String>(CAPACITY);
			this.logger = new LoggerThread(writer);
		}

		/**
		 * This flag will cause the logger to stop processing new requests
		 * 
		 * @see com.georgecurington.functionalstudymod.concurrent.cancellationshutdown.Log#stop()
		 */
		@Override
		public void stop() {
			synchronized (this) {
				isShutdown = true;
			}
			logger.interrupt();
		}

		@Override
		public void start() {
			logger.start();
		}

		@Override
		public void log(String msg) throws InterruptedException {
			synchronized (this) {
				if (isShutdown) {
					throw new IllegalStateException("logger is shutdown");
				}
				++reservations;
			}
			queue.put(reservations + ":" + ai.incrementAndGet() + "put ->" + msg);
		}

		private class LoggerThread extends Thread {
			private final PrintWriter writer;

			public LoggerThread(Writer writer) {
				this.writer = (PrintWriter) writer;
			}

			public void run() {
				boolean stop = false;
				try {
					while (!stop) {

						try {
							synchronized (LogWriter.this) {
								--reservations;
							}
							writer.println(ai.get() + "get ->" + queue.take());
							writer.flush();
						} catch (InterruptedException ignored) {
							stop=true;
						}
					}
				} finally {
					writer.close();
				}
			} /** end of run */

		}
		
		

		public static void main(String... strings)  {
			PrintWriter pw = new PrintWriter(System.out);
			pw.println("HELLO");
			pw.flush();

			/** create and start the logger **/
			// Log logger = new LogWriterWithoutStop(pw);
			Log logger = new LogWriter(pw);
			logger.start();

			/** create a worker to send log entries to the logger **/
			Worker worker = new Worker(logger);
			Stopper stopper = new Stopper(logger);
			Pair<Future<?>, ExecutorService> execPair = Utility.oneOff(worker);
			Pair<Future<?>, ExecutorService> execPairStopper = Utility.oneOff(stopper);
			ExecutorService exec1 = execPair.getRight();
			ExecutorService exec2 = execPairStopper.getRight();
			try {
				System.out.println("getting exec1");
				System.out.println(execPair.getLeft().get());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				System.out.println("getting exec2");
				System.out.println(execPairStopper.getLeft().get());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			exec1.shutdown();
			exec2.shutdown();
			try {
				while (!exec1.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
					System.out.println("exec1 waiting to terminate");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				while (!exec2.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
					System.out.println("exec2 waiting to terminate");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		static class Worker implements Callable<Void> {

			private static final long LOGSLEEP = 1000;
			private final Log logger;

			public Worker(Log logger) {
				this.logger = logger;
			}

			@Override
			public Void call() throws Exception {
				while (true) {
					String randomString = TestData.getUniqueString();
					logger.log(randomString);
					Thread.sleep(LOGSLEEP);
				}
			}

		}

		static class Stopper implements Callable<Void> {

			private static final long LOGSLEEP = 1000;
			private final Log logger;

			public Stopper(Log logger) {
				this.logger = logger;
			}

			@Override
			public Void call() throws Exception {
				Thread.sleep(LOGSLEEP * 2);
				logger.stop();
				return null;
			}

		}
	}
}
