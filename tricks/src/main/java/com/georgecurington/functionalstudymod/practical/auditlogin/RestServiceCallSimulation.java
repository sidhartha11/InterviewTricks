/**
 * 
 */
package com.georgecurington.functionalstudymod.practical.auditlogin;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.georgecurington.functionalstudymod.testdata.TestData;

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
public class RestServiceCallSimulation {
	private static volatile boolean running=true;
	private static final ExecutorService exec = Executors.newCachedThreadPool();
	private static final BlockingQueue<AuditQueue> queue = new LinkedBlockingQueue<>(Integer.MAX_VALUE);
	private static final boolean DEBUG = false;
	/**
	 * 
	 */
	public RestServiceCallSimulation() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/** start up the QueueReader to simulate a backend process reading the queue **/
		QueueReader<AuditQueue> qr = new QueueReader<>(queue);
		Future<Void> fut = exec.submit(qr);
		
		while (running) {
			long sleep=ThreadLocalRandom.current().nextLong(0, 250);
			try {
				TimeUnit.MILLISECONDS.sleep(sleep);
				/** execute a login audit request **/
				/** generate random id for user **/
				String user=TestData.getUniqueString();
				
				/** call the audit method **/
				StatusLogAudit b=writeAuditLog("successful log in", user);
				
				/** check the status of the audit **/
				switch ( b ) { 
				case successQueueWrite:
					if (DEBUG) System.out.println("successful audit of login");
					break;
				case unsuccesQueueWrite:
					if (DEBUG) System.out.println("unsuccessful audit of login");
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/** just let the QueueReader run forever **/
		exec.shutdown();
		try {
			while ( !exec.awaitTermination(10000, TimeUnit.MILLISECONDS)) {
				System.out.println("waiting for QueueReader to stop");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static StatusLogAudit writeAuditLog (String logmessage,String user){
		AuditQueue aq = new LoginAudit(logmessage, user); 
		boolean res = queue.offer(aq);
		return res ? StatusLogAudit.successQueueWrite :StatusLogAudit.unsuccesQueueWrite; 	
	}
}
