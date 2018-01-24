/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.threads.threadlocal;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 22, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class ThreadLocalExample {

	/**
	 * 
	 */
	public ThreadLocalExample() {
		// TODO Auto-generated constructor stub
	}
	
	public static class MyRunnable implements Runnable {
		private ThreadLocal<Integer> threadLocal = 
				new ThreadLocal<Integer>();

		@Override
		public void run() {
			threadLocal.set((int)(Math.random()  * 1800));
			try {
				Thread.sleep(2000);
			} catch ( InterruptedException e) {
				
			}
			
			System.out.println(threadLocal.get());
		}
	}
	
	public static void main(String...strings ) throws InterruptedException{
		MyRunnable sharedRunnableInstance = new MyRunnable();
		
		Thread thread1 = new Thread(sharedRunnableInstance);
		Thread thread2 = new Thread(sharedRunnableInstance);
		
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
	}

}
