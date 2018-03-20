/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.java8inaction;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

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
public class Shop implements ShopIntf, Utils {
	private final String shop;

	/**
	 * 
	 */
	public Shop(String shop) {
		this.shop = shop;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.jdk8lambda.java8inaction.ShopIntf#getPrice()
	 */
	@Override
	public Future<Double> getP(String product) {
		Utility.p("calling getPriceAsync for " + product);
		Future<Double> r =  getPriceAsync(product);
		return r;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

				
	}

	/**
	 * @throws RuntimeException
	 */
	private static void runOneShopAsynchronously() throws RuntimeException {
		Shop shop = new Shop("BestShop");
		long start = System.nanoTime();
		Future<Double> futurePrice = shop.getP("My favorite product");
		
		long invocationTime = ((System.nanoTime() - start )/1_000_000);
		Utility.p("Invocation returned after " + invocationTime + " msecs");

		/** doSomethingElse() while waiting for task to complete **/
		doSomethingElse();
		
		try {
			double price = futurePrice.get();
			Utility.p(String.format("Price is %.2f%n", price));
		} catch ( Exception e ){
			throw new RuntimeException(e);
		}
		
		long retrieveTime = ((System.nanoTime() - start )/1_000_000);
		Utility.p("Price returned after " + retrieveTime + " msecs");
	}

	private static void doSomethingElse() {
		Utils.delay(" querying other shops");
		
	}


	
@Override
public String getName() {
	// TODO Auto-generated method stub
	return this.shop;
}

@Override
public double getPD(String product) {
	// TODO Auto-generated method stub
	return getPrice(product);
}

}
