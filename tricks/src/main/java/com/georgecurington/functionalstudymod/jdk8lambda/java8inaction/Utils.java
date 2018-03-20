/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.java8inaction;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.jdk8lambda.Discount;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 10, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public interface Utils {

	public static void delay(String msg){
		try {
			Utility.p("doing some work "+ msg);
			TimeUnit.MILLISECONDS.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void delay(){
		try {
			Utility.p("remote call delay ");
			TimeUnit.MILLISECONDS.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** default utility methods **/
	default Future<Double> getPriceAsync(String product) {
	
		/** using CompletableFuture and supplying the task to be executed **/
		/**
		 * Note that the supplyAsync methods accepts a Supplier lambda expression.
		 * that in turn returns. Also this form of CompletableFuture handles 
		 * possible exceptions that are thrown by the Supplier.
		 * 
		 * Supplier<T> Declaration:
		 *
		 * <p>There is no requirement that a new or distinct result be returned each
		 * time the supplier is invoked.
		 *
		 * <p>This is a <a href="package-summary.html">functional interface</a>
		 * whose functional method is {@link #get()}.
		 *
		 * @param <T> the type of results supplied by this supplier
		 *
		 * @since 1.8
		 * @FunctionalInterface
         * public interface Supplier<T> {
		 */
		Future<Double> d = CompletableFuture.supplyAsync(() -> calculatePrice(product));
		Utility.p("returning a future price:" + d.toString());
		return d;
	}
	
	/** default utility methods **/
	default Future<Double> getPriceAsync2(String product) {
		
		/** create a CompletableFuture **/
		/** notice that this requires extra work to catch the exception **/
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		
		/** Use a thread to execute a task **/
		new Thread( () -> {
			
			try {
			double price = calculatePrice(product);
			
			/** set the price in the CompletableFuture **/
			futurePrice.complete(price);
			} catch ( Exception ex ){
				futurePrice.completeExceptionally(ex);
			}
		}).start();
		
		Utility.p("returning a future price");
		return futurePrice;
	}
	
	default double getPrice(String product) {
		return calculatePrice(product);
		}
	
	default String getPriceNew(String product) {
		/** get the price **/
		double d = calculatePrice(product);
		
		/** get a random discount code **/
		Discount.Code code = Discount.Code.values()[
		          ThreadLocalRandom.current().nextInt(Discount.Code.values().length) ]; 
		
		/** return a string formated with colons,product:price:code **/
		return String.format("%s:%.2f:%s", product,d,code);
		}
	
	default double calculatePrice(String product) {
//		Utility.p("processing product");
		delay("calculating price");
		/** 
		 * we want to make this method occassionally blow up
		 * to test exception handlion.
		 */
		int blowup = 2;
//				ThreadLocalRandom.current().nextInt(1, 3);
//		Utility.p(">>>>>>blowup=" + blowup);
		switch( blowup ) {
		case 20:
			throw new RuntimeException("calculatePrice error");
		case 2:
			break;
		}
		char firstPart = product.charAt(0);
		char secondPart= product.charAt(1);
		double r = ThreadLocalRandom.current().nextDouble() * firstPart + secondPart;
//		Utility.p("returning a synthetic price of " + r);
		return r;
	}
	
	/**
	 * <pre>
	 * This is formula from Java Concurrency in Practice 
	 * @param waittime the ratio of waittime to compute time
	 * @param computetime the ratio of waitime to compoute time
	 * @param cpu target CPU utilization ( 0 to 1 )
	 * @return
	 */
	static int poolSizeGoetz(double waittime, double computetime,double cpu){
		double threads = 
				Runtime.getRuntime().availableProcessors()
				* cpu 
				* ( 1 + (waittime/computetime));
		return (int)threads;
	}
	
	static int numberThreads() {
		int n = poolSizeGoetz(99.0, 1.0, .99);
    return n;
	}
	
	public static void main (String...strings ){
		Utility.p("testing numberThreads function:" + numberThreads());
	}
}
