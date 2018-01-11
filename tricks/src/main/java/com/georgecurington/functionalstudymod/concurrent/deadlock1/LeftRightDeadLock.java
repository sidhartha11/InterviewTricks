/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.deadlock1;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**<pre>
 * From chapter 10 of Concurrency in practice. The book illustrates
 * an example of how to cause a dead lock via incorrect lock aquisition.
 * This is known as lock ordering:
 * If multiple threads try to acquire two locks in reverse
 * order dead lock could happen.
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class LeftRightDeadLock {
	private final Object left = new Object();
	private final Object right = new Object();

	/**
	 * 
	 */
	public LeftRightDeadLock() {
		// TODO Auto-generated constructor stub
	}
	
	public void leftRight(){
		Utility.p("grabbing left");
		synchronized(left) { 
			Utility.p("grabbing right");
			synchronized(right){
				Utility.p("leftRight calling doSomething");
				doSomething("leftright");
			}
		}
	}
	
	public void rightLeft(){
		Utility.p("grabbing right");
		synchronized(right){
			Utility.p("grabbing left");
			synchronized(left){
				Utility.p("rightLeft calling doSomething");
				doSomething("rightleft");
			}
		}
	}

	private void doSomething(String string) {
		Utility.p("in doSomething:" + string);
		
	}

}
