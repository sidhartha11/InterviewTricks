/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.RunnableFuture;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * <pre>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 19, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * </pre>
 */
public class ClientCallableTask extends SocketUsingTask<Void> {
	private final int port;
	private Socket serverSocket;
	private volatile boolean running=true;
	/**
	 * 
	 */
	public ClientCallableTask(int port) {
		this.port = port;
	}

	@Override
	public Void call() throws Exception {
		try
		(       Socket serverSocket =new Socket("localhost", port);
				PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		)
		{
		this.serverSocket = serverSocket;
		/** set socket in parent **/
		setSocket(serverSocket);
		while (running) {
			System.out.println("client reading");
			String s = in.readLine();
			if ( s == null ) {
				running=false;
				System.out.println("null string, exiting");
			} else if ( s.length() > 0 ){
				processBuffer(s,out);
			} else {
				running=false;
				System.out.println("blank string, exiting");
			}
		}
		}
		catch (UnknownHostException e) {
			System.out.println("bad host");
		} catch (IOException e) {
			System.out.println("IOexception");
		}
		return null;
	}
	
	private void processBuffer(String s, PrintWriter out) {
		System.out.println("read " + s);
		out.println(s);
	}

	/** the methods below are implemented in SocketUsingTask **/
//	@Override
//	public void cancel() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public RunnableFuture<Void> newTask() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
