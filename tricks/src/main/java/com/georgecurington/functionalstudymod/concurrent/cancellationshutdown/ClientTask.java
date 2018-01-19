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

/**
 * <pre>
 * Attempting to do the 7.11 exercise regarding
 * how to cancel a task that is reading from
 * a socket using plain old Threads
 * </pre>
 * @author george
 *
 */
public class ClientTask extends Thread {

	private final int port;
	private Socket serverSocket;
	private volatile boolean running=true;
	/**
	 * 
	 */
	public ClientTask(int port) {
		this.port = port;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try
		(       Socket serverSocket =new Socket("localhost", port);
				PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		)
		{
		this.serverSocket = serverSocket;
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

	}
	
	private void processBuffer(String s, PrintWriter out) {
		System.out.println("read " + s);
		out.println(s);
	}

	@Override
	public void interrupt(){
		System.out.println("interrupting client");
		try {
			serverSocket.close();
		}
		catch ( IOException ignored ){
			
		} finally {
			super.interrupt();
		}
	}

}
