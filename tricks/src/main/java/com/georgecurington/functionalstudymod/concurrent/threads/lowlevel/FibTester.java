package com.georgecurington.functionalstudymod.concurrent.threads.lowlevel;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.mathstuff.recursive.FibonacciSeries;

public class FibTester  implements Runnable {

	long n;
	String id;
	public FibTester(long n, String id){
		this.n = n;
		this.id = id;
	}

	private long fib(long n){
		if ( n == 0 )
			return 0L;
		if ( n == 1 )
			return 1L;
		return fib (n-1) + fib(n-2);
	}
	
	@Override
	public void run() {
	    Date d = new Date( );
        DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");
        long startTime = System.currentTimeMillis( );
        d.setTime(startTime);
        System.out.println("Starting task " + id + " at " + df.format(d));
        
        FibonacciSeries fibCache = 
        		new FibonacciSeries(); 
        BigInteger bigInt = new BigInteger(String.valueOf(n));
//       fibRecursiveCache
//        Utility.p("fibcache of " + n + " = " + fibCache.fibRecursiveBigCache(bigInt));
        Utility.p("fibcache of " + n + " = " + fibCache.fibRecursiveCache(n));
//       Utility.p("fib of " + n + " = " +fib(n));
        
        
        long endTime = System.currentTimeMillis( );
        d.setTime(endTime);
        System.out.println("Ending task " + id + " at " + 
                           df.format(d) + " after " + (endTime - startTime) +
                           " milliseconds");
	}

	public static void main(String...strings ){
		/** create a executor to monitor the fib stack **/
		ExecutorService exec = Executors.newCachedThreadPool();
		
		/** create a task to execute the recursive fib algorithm **/
		FibTester fibt = new FibTester(50L,"50L");
		
		/** pass the fib Runnable to a Thread instance **/
		Thread t=new Thread(fibt);
		
		/** submit the monitor callable with the thread handle **/
		Future<Void> fut = exec.submit(new CheckStack(t));
		
		/** start the thread executing the fib function **/
		t.start();
		
		try {
			t.join();
			Utility.p("joined");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		exec.shutdownNow();
		while (!exec.awaitTermination(1000, TimeUnit.MILLISECONDS)){
			Utility.p("waiting to terminate");
		}
		} catch (InterruptedException e){
			
		}
	}
	
	public static class CheckStack implements Callable<Void> {

		private final Thread t;
		public CheckStack(Thread t) {
			this.t = t;
		}
		@Override
		public Void call() throws Exception {
			while ( true ) {
				Thread.sleep(2000);
				StackTraceElement[] st = t.getStackTrace();
				Utility.p("stacktrace size=" + st.length);
				Arrays.stream(st).limit(10).forEach(System.out::println);
			}
			
		}
		
	}
}

