/**
 * 
 */
package com.georgecurington.j8ia.chapter11;
import static com.georgecurington.j8ia.chapter11.Java8utils.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author Owner
 *
 */
public class Shop implements ShopperApiIntf {
    private final boolean SIMULATEFAILURE = false;
	final private String name;
	/**
	 * 
	 */

	public Shop(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.j8ia.chapter11.ShopperApiIntf#getPrice(java.lang.String)
	 */
	@Override
	public String getPrice(String product) {
		System.out.println("in getPrice:" + product);
		double price =  calculatePrice(product);
		
		Discount.Code code = Discount.Code.values()[RANDOM.nextInt(Discount.Code.values().length)];
		return String.format("%s:%.2f:%s", name,price,code.name());
	}
	
	@Override
	public double getPriceDouble(String product) {
		double price =  calculatePrice(product);
		
		return price;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 * @param product
	 * @return price of product
	 * NOTE:
	 * To simulate division by zero you cannot use a double value
	 * because:
	 * Java will not throw an exception if you divide by float zero. 
	 * It will detect a run-time error only if you divide by integer zero 
	 * not double zero. If you divide by 0.0, the result will be 
	 * INFINITY. If you divide double by 0, JVM will show Infinity
	 */
	private double calculatePrice(String product) {
//		peep(">>calculating price for " + product);
		delay();
		double price = getDValue(product);
		if ( SIMULATEFAILURE ) {
			int f = 0;
			int test = 100 / f;
		}

		return price;
	}


	/* <p>
	 * Creating a completableFuture with the supplyAsync factory method
	 * This is much more concise than doing it programatically.
	 * It accepts a jdk8-lambda expression: A Supplier that returns 
	 * a CompletableFuture.
	 * This Supplier will be run by one of the Executors in the ForkJoinPool, 
	 * but you can specify a different Executor by passing it 
	 * as a second argument to the overloaded version of this method. 
	 */
	@Override
	public Future<Double> getPriceAsync(String product) {
		CompletableFuture<Double> futurePrice = 
				CompletableFuture.supplyAsync( () -> calculatePrice(product));

		return futurePrice;
	}
	/* <p>
	 * #1 attempt at using the CompletableFuture Interface
	 * definition in java source:
	 * public class CompletableFuture<T> implements Future<T>, CompletionStage<T> {
	 */
	private Future<Double> getPriceAsync_1(String product) {
		CompletableFuture<Double> futurePrice = 
				new CompletableFuture<>();
	    new Thread
	    ( 
	    		() -> {
	    			/** refining to probagate errors **/
	    			
	    			try {
	    			double price = calculatePrice(product);
	    			futurePrice.complete(price);
	    			}
	    			catch (Exception ex) {
	    				futurePrice.completeExceptionally(ex);
	    			}
	    			
	    		}
	    ).start();
		return futurePrice;
	}

}
