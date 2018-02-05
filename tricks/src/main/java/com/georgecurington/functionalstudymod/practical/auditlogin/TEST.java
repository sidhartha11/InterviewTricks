/**
 * 
 */
package com.georgecurington.functionalstudymod.practical.auditlogin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 4, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */

//for ( Future<Void> f : futs) {
//	try {
//		System.out.println(f.get());
//	} catch (InterruptedException | ExecutionException e) {
//		e.printStackTrace();
//	}
//}
//
//try {
//while ( !exec.awaitTermination(1000, TimeUnit.MILLISECONDS)){
//	System.out.println("waiting for shutdown");
//}
//} catch (InterruptedException e) {
//	e.printStackTrace();
//}
public class TEST {
	
	public static void writeAuditLog(String logMessage , String userId){
		futs.add(exec.submit(new Worker("str","str")));
	}

	private static final ExecutorService exec = Executors.newCachedThreadPool();
	private static final List<Future<Void>> futs = new ArrayList<>();
	
	private static class Worker implements Callable<Void> {

		private final String loginId;
		private final String msg;
		public Worker(String loginId, String msg){
			this.loginId = loginId;
			this.msg = msg;
		}
		
		public Void call() throws Exception {
			writeAuditLog(msg,loginId);
			return null;
		}
		
		public static void main(String...strings ){
			for ( Future<Void> f : futs) {
				try {
					System.out.println(f.get());
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				while ( !exec.awaitTermination(1000, TimeUnit.MILLISECONDS)){
					System.out.println("waiting for shutdown");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 
	 */
	public TEST() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
