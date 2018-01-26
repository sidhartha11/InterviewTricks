/**
 * 
 */
package com.georgecurington.functionalstudymod.practical.auditlogin;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 26, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class QueueReader<T extends AuditQueue> implements Callable<Void> {
	private volatile boolean running=true;
	private final BlockingQueue<T> queue;
	/**
	 * 
	 */
	public QueueReader(BlockingQueue<T> queue) {
		this.queue = queue;
	}

	@Override
	public Void call() throws Exception {
		while ( running ) {
			T data = queue.take();
			auditDb(data);
		}
		return null;
	}

	private void auditDb(T data) {
		long sleep=ThreadLocalRandom.current().nextLong(0, 500);
		try {
			TimeUnit.MILLISECONDS.sleep(sleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("simulating database action");
		System.out.println("logging:");
		System.out.println(data.getLogmessage());
		System.out.println(data.getUserId());
		System.out.println(data.getLogtime());
		
	}

}
