/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.explicitlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * This example is illustrating a use of Explicit locks in a 
 * timed/polled usage. If the lock is not acquired within 
 * a given amount of time it is released.
 * </pre>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 30, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class SendOnSharedLine implements Callable<Boolean> {

	private final String message;
	private final long timeout;
	private final TimeUnit unit;
	private final Lock lock;
	/**
	 * 
	 */
	public SendOnSharedLine(Lock lock,String message,long timeout, TimeUnit unit) {
		this.message = message;
		this.timeout = timeout;
		this.unit = unit;
		this.lock = lock;
	}

	@Override
	public Boolean call() throws Exception {
		Boolean status=null;
		try {
		status = Explicits.trySendOnShared(lock,message,timeout,unit);
		} finally {
		Utility.p("status returned was " + status);
		}
		return status;
	}

	public static void main(String...strings ){
		ExecutorService exec = Executors.newCachedThreadPool();
		Lock lock =new ReentrantLock();

		List<Future<Boolean>> futs = new ArrayList<>();
		IntStream.rangeClosed(1,100).forEach ( p -> {
			futs.add(exec.submit(new SendOnSharedLine(lock,"shared line",Explicits.TIMEOUTV,TimeUnit.NANOSECONDS)));
		});

		Boolean b=null;
		futs.forEach(p -> {
			try {
				Boolean a = p.get();
				Utility.p("got --- " + b );
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		Utility.p("got " + b);
		exec.shutdown();
		
		try {
			while (!exec.awaitTermination(1000	, TimeUnit.NANOSECONDS)){
				Utility.p("waiting to terminate");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
