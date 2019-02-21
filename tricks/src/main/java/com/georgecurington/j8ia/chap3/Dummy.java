/**
 * 
 */
package com.georgecurington.j8ia.chap3;

/**
 * @author Owner
 *
 */
public class Dummy {

	/**
	 * 
	 */
	public Dummy(Runnable r) {
		System.out.println("running Runnable");
		r.run();
	}

}
