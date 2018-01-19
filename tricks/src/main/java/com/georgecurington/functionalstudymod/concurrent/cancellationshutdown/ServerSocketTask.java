/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * This code is only meant to test one of the issues
 * presented in Chapter 7 of Concurrency in Practice.
 * The client half of this uses an interrupt signal to 
 * cancel the client code
 * </pre>
 *
 */
public class ServerSocketTask implements Callable<Void> {
	private volatile boolean running = true;
	private static final int ACTIVETHREADS = (Runtime.getRuntime().availableProcessors() * 2);
	private final int serverport;
	private ServerSocket socket;

	/**
	 * 
	 */
	public ServerSocketTask(int serverport) {
		this.serverport = serverport;
	}

	@Override
	public Void call() throws Exception {
		System.out.println("serversockettask call executed");
		ExecutorService exec = Executors.newFixedThreadPool(ACTIVETHREADS);
		try {
			socket = new ServerSocket(serverport);
		} catch (IOException e) {
			System.out.println("exception e:" + e);
		}
		while (running) {
			System.out.println("accepting connections");
			try { 
			Socket clientsocket = socket.accept();
			/**
			 * 
			 * Note:
			 * In this implementation, it is IMPOSSIBLE
			 * to stop the call below. In order to do so, one
			 * might be able to send the Futures to a queue
			 * and have another process execut a get on them
			 * and eventually shutdown the executor. But 
			 * that is not what is example is supposed to do.
			 * 
			 */
			exec.submit(new ServerTask(clientsocket));
			} catch ( Exception e){
				System.out.println("2 exception e:" + e);
			}
		}
		
		return null;
	}
	
	public void interrupt(){
		System.out.println("shutting down server");
		running = false;
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		socket=null;
		
	}

	private static class ServerTask implements Callable<Void> {
		private final Socket socket;

		public ServerTask(Socket socket) {
			this.socket = socket;
		}

		@Override
		public Void call() throws Exception {
			System.out.println("call executed");
			try (InputStream in = socket.getInputStream();
					OutputStream out = socket.getOutputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(in));

			) {
				String request;
				String s = "hello\n";
				out.write(s.getBytes());
				while ((request = br.readLine()) != null) {
					System.out.println("Message received:" + request);
					request += '\n';
					out.write(request.getBytes());
				}
			} finally {
				System.out.println("ServerTask finally block");
			}

			return null;
		}
	}
}
