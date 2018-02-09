/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.latch.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 8, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class TestHarness {

	public long timeTasks ( int nThreads , final Runnable task)
	throws InterruptedException {
		
		/** here we use a gate to tell the Threads when to proceed with their work **/
		final CountDownLatch startGate = new CountDownLatch(1);
		
		/** here we use a gate to indicate to some other thread that these threads are finished **/
		final CountDownLatch endGate   = new CountDownLatch(nThreads);
        
		for ( int i = 0; i < nThreads; i++ ){
			
			Thread t = new Thread() {
				public void run() {
					try {
						
	/**
	 * Causes the current thread to wait until the latch has counted down to 
	 * zero, unless the thread is interrupted. If the current count is zero 
	 * then this method returns immediately. If the current count is 
	 * greater than zero then the current thread becomes disabled for thread scheduling 
	 * purposes and lies dormant until one of two things happen: 
     *     ◦The count reaches zero due to invocations of the countDown() method; or 
     *     ◦Some other thread interrupts the current thread. 
     *
     * If the current thread: 
     *     ◦has its interrupted status set on entry to this method; or 
     *     ◦is interrupted while waiting, 
     * then InterruptedException is thrown and the current thread's 
     * interrupted status is cleared.
     * Throws:InterruptedException - if the current thread is 
     * interrupted while waiting
     * 
	 */
						
						startGate.await();
						try { 
							task.run();
						} finally {
							
	/**
	 * public void countDown() 
	 * Decrements the count of the latch, releasing all waiting threads 
	 * if the count reaches zero. 
     * If the current count is greater than zero then it is decremented. 
     * If the new count is zero then all waiting threads are re-enabled for 
     * thread scheduling purposes. 
     * If the current count equals zero then nothing happens.
     * 					
	 */
							
							
							endGate.countDown();
						}
					} catch ( InterruptedException ignored ) {
					/** swallows this exception **/
					}
				}
			};
			
			/** start a thread and execute the Runnable */
			t.start();
		}
		
		long start = System.nanoTime();
		/** this will cause all the threads waiting on the gate to execute. **/
		startGate.countDown();
		/** now wait until they all do a countDown call indicating that they have
		 * finished.
		 */
		endGate.await();
		
		/** collect the amount of nano seconds that have gone by **/
		long end = System.nanoTime();
		
		/** return the elapsed time **/
		return end - start ;
	}
	/**
	 * 
	 */
	public TestHarness() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String...strings ){
		try {
			long t = new TestHarness().timeTasks(10, new Runnable(){
				public void run() {
					try {
						Utility.p("running");
						TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(100, 500));
						Utility.p("exiting");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			Utility.p("took " + t + " microseconds");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
