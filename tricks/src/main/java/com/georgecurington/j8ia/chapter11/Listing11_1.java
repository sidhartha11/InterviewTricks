/**
 * 
 */
package com.georgecurington.j8ia.chapter11;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import static com.georgecurington.j8ia.chapter11.Java8utils.*;


/**
 * @author Owner
 *
 */
public class Listing11_1 {
private static Logger logger=Logger.getLogger(Listing11_1.class);


	/**
	 * 
	 */
	public Listing11_1() {
		callExample1();
	}

	private Double callExample1() {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Double> future = executor.submit( new Callable<Double>() {
			public Double call() {
				return doSomeLongComputation();
			}

			private Double doSomeLongComputation() {
				// TODO Auto-generated method stub
				waitx(1000);
				return 10.0;
			}
		});
		
		try {
			Double result = future.get(1,TimeUnit.SECONDS);
			return result;
		} catch (InterruptedException ie) {
			peep(ie.getMessage());
		} catch (ExecutionException ee) {
			peep(ee.getMessage());
		} catch (TimeoutException te) {
			peep(te.getMessage());
		} finally {
			executor.shutdownNow();
		}
		
		return -1.1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		peep(logger,new Listing11_1().callExample1());

	}

}
