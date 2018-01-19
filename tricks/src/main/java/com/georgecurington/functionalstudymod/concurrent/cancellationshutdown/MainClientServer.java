/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author george
 *
 */
public class MainClientServer {

	/**
	 * 
	 */
	public MainClientServer() {
		
		/** submit the server task via ExecutorService **/
		ServerSocketTask ss = new ServerSocketTask(ServerSocketClientUtil.SERVERPORT);
		ExecutorService exec = Executors.newCachedThreadPool();
		Future<?> f = exec.submit(ss);
		
		/** let the client run as an old fashion Thread **/
		ClientTask clientTask = new ClientTask(ServerSocketClientUtil.SERVERPORT);
		clientTask.start();
		try {
			/** wait a few seconds and interrupt the client **/
			Thread.sleep(2000);
			clientTask.interrupt();
			/** now try interrupt the server  **/
			ss.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exec.shutdown();
		try {
			while (!exec.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
				System.out.println("shutdown waiting");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String... strings) {

		new MainClientServer();
	}
}
