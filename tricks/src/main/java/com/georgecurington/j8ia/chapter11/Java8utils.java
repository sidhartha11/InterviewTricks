/**
 * 
 */
package com.georgecurington.j8ia.chapter11;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;

/**
 * @author Owner
 * Notes:
 * 1. Interface static variables are by default public,static and final.
 *
 */
public interface Java8utils {
	public static final Random RANDOM = new Random(0);
	public static final DecimalFormat 
	formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
	
	public String TESTPRODUCT = "my favorite product";
    public Logger logger=Logger.getLogger(Java8utils.class);
	public static void waitx(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static double format(double number) {
		synchronized(formatter) {
			return new Double(formatter.format(number));
		}
	}
	public static void delay() {
		waitx(1000);
	}
	
	public static double getDValue(String product) {
		double d = 
				ThreadLocalRandom.current().nextDouble() *
				product.charAt(0) + product.charAt(1);
		return d;
	}
	public static void peep(Logger logger, Object msg) {
		logger.info(msg);
	}
	
	public static void peep(Object msg) {
		logger.info(msg);
	}
	public static void peep(List<String> msg) {
		System.out.println(msg);
	}
	
	public static void doSomethingElse() {
		peep(">>>>simulating doing other work");
		waitx(1000);
	}
}
