/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.synchronizers;

/**
 * @author george
 *
 */
public class ThreadGate {
	
	// CONDITION-PREDICATE: opened-since(n) (isOpen || generation>n)

	private boolean isOpen;
	private int generation;
	/**
	 * 
	 */
	public ThreadGate() {
		// TODO Auto-generated constructor stub
	}
	
	public synchronized void close() {
		isOpen = false;
	}
	
	public synchronized void open() {
		++generation;
		isOpen = true;
		notifyAll();
	}
	
	// BLOCKS-UNTIL: opened-since(generation on entry)
	public synchronized void await() throws InterruptedException {
		int arrivalGeneration = generation;
		while (!isOpen && arrivalGeneration == generation){
			wait();
		}
	}

}
