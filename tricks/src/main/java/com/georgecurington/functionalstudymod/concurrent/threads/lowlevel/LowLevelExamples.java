/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.threads.lowlevel;

import java.sql.Date;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <pre>
 * This class contains a few illustrations of low level
 * Thead constructs that form the basis of the java
 * Threading api.
 * </pre>
 * 
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 31, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class LowLevelExamples {

	Runnable test = () -> System.out.println("default Runnable Test");
	
	Runnable test2 = () -> {
		synchronized(this) {
			Utility.p("calling wait");
			try {
				wait(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Utility.p("finished calling wait");
			
		}
	};
	
	Runnable test3 = () -> {
		synchronized(this) {
			Utility.p("inspecting context class loader");
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			try {
				Class<?> dt=cl.loadClass("java.util.Date");
				System.out.println(dt.newInstance());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Utility.p("finished calling wait");
			
		}
	};
	
	Runnable test4 = () -> {
		synchronized(this) {
			Utility.p("thread calling stop");
			Thread.currentThread().stop();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Utility.p("finished calling wait");
		}
	};
	
	Runnable test5 = () -> {
		synchronized(this) {
			Utility.p("thread throwing ThreadDeath");
			throw new MyThreadDeath("I can't take it anymore!!!!");
			
		}
	};
	/**
	 * 
	 */
	public LowLevelExamples() {
//		examp_RunnableExceptions();
//		examp_RunnableExceptionsUncaughtHandler();
		examp_suicide();
	}

	/**
	 * <pre>
	 * A thread can stop running in the following ways, some being deprecated:
	 * 1. return from run
	 * 2. all stop
	 * 3. throw a ThreadDeath exception
	 * </pre>
	 */
	private void examp_suicide() {
		new Thread(new OverrideThreadGroup( ),test5).start();
		
	}

	private void examp_RunnableExceptionsUncaughtHandler() {
		new Thread(new OverrideThreadGroup(), new RunnableThatThrowsExceptions()).start();
		
	}

	/**
	 * <pre>
	 * Note that is ok for a Runnable to emit an exception; as long
	 * as it is not a checked Exception.
	 * </pre>
	 * 
	 */
	private void examp_RunnableExceptions() {
		new Thread(new RunnableThatThrowsExceptions()).start();
		
	}
	/**
	 * <pre>
	 * Example illustrating the contextClassLoader associated with a Thread
	 * </pre>
	 */
	private void examp_ContextClassLoader() {
		Utility.p("running");
		Thread d = new Thread ( test3 );
		/** start the Thread **/
		d.start();
		
	}
	/**
	 * <pre>
	 * Note that this daemon thread will exit almost
	 * immediately and not honor the wait. In fact,
	 * what happens here is the JVM simply exits since
	 * there are no more User Threads running
	 * </pre>
	 * 
	 */
	private void examp_daemonThreadWithWait() {
		/** create a thread and pass it a Runnable **/
		Utility.p("running");
		Thread d = new Thread ( test2 );
		d.setDaemon(true);
		/** start the Thread **/
		d.start();
	}
	/**
	 * <pre>
	 * Note the JVM will continue to run until the 
	 * user thread finishes waiting
	 * </pre>
	 */
	private void examp_userThreadWithWait() {
		/** create a thread and pass it a Runnable **/
		Thread d = new Thread ( test2 );
		
		/** start the Thread **/
		d.start();
	}

	/**
	 * <pre>
	 * Note that in this example you will definitely
	 * see the output from the task before the JVM
	 * exits. This is a NON-DAEMON thread
	 * </pre>
	 * 
	 */
	private void examp_userThread() {
		/** create a thread and pass it a Runnable **/
		Thread d = new Thread ( test );
		
		/** start the Thread **/
		d.start();
		
	}

	/**
	 * <pre>
	 * In this example, the Thread creates a simple task that
	 * executes a Runnable. Note that the setDaemon function is 
	 * called with a parameter of true.
	 * It is possible that you will never see the output from
	 * task before the JVM exits. This is because The JVM is allowed 
	 * to exist once all NON-DAEMON threads exit.
	 * </pre>
	 */
	private void examp_daemonThread() {
		/** create a thread and pass it a Runnable **/
		Thread d = new Thread ( test );
		
		/** let this Thread process its Runnable as a Daemon **/
		d.setDaemon(true);
		
		/** start the Thread **/
		d.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LowLevelExamples lle=new LowLevelExamples();
//		System.out.println("exiting");
	}
	
	public static class RunnableThatThrowsExceptions implements Runnable {

		@Override
		public void run() {
			Utility.p("oh oh, throwing an exception");
			throw new RuntimeException("I threw a runtime exception");
			
		}
		
	}
	
    /**
     * <pre>
     * The class ThreadGroup contains an overridable method that will handle
     * Exceptions thrown by a Runnable
     * </pre>
     */
    static class OverrideThreadGroup extends ThreadGroup {
        public OverrideThreadGroup( ) {
            super("Administrator Alert Group");
        }
        public void uncaughtException(Thread t, Throwable e) {
            Utility.p("uncaughtException being handled:" + e);
        }
    }
    
    static class MyThreadDeath extends ThreadDeath {
    	public static final String suicideNote="Cannot Stand It Anymore!!";
    	static {
    		Utility.p(suicideNote);
    	}
          public MyThreadDeath(String suicideNote){
        	  Utility.p(suicideNote);
          }
    }


}
