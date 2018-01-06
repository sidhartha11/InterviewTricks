/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.threads;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author George Curington
 *
 */
public class UEHLogger implements Thread.UncaughtExceptionHandler {
	/**
	 * 
	 */
	public UEHLogger() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		Logger logger = Logger.getAnonymousLogger();
		logger.log(Level.SEVERE,
		"Thread terminated with exception: " + t.getName(),e );
		
	}

}
