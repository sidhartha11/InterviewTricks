/**
 * 
 */
package com.georgecurington.functionalstudymod.jdk8lambda.java8inaction;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.jdk8lambda.Discount;
import com.georgecurington.functionalstudymod.jdk8lambda.Quote;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 11, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class Client {
	/** note : cannot reference field before it is defined **/
	private final List<Shop> shops = Arrays.asList(
			new Shop("BestPrice"),
			new Shop("LetsSaveBig"),
			new Shop("MyFavoriteShip"),
			new Shop("ShopEasy"),
			new Shop("BuyItAll")
			
//			new Shop("REI Sports"),
//			new Shop("EastMountainSports"),
//			new Shop("RamseyOutdoors"),
//			new Shop("TheNinthStop")
			
//			
//			new Shop("REI Sports-2"),
//			new Shop("EastMountainSports-2"),
//			new Shop("RamseyOutdoors-2"),
//			new Shop("TheNinthStop-2")
			);
	
	/** custom executor **/
	private final Executor executor = 
			Executors.newFixedThreadPool
			(
			Math.min(shops.size(), 100),
			new ThreadFactory() {
				public Thread newThread(Runnable r){
					Thread t = new Thread(r);
					t.setDaemon(true);
					return t;
				}
			}
			);
			

			
	/**
	 * 
	 */
	public Client() {
		// TODO Auto-generated constructor stub
	}
	public List<String> findPricesParallel(String product) {
		List<String> s = shops.parallelStream().map(shop ->

		String.format("%s price is %.2f", shop.getName(), shop.getPD(product))

		).collect(Collectors.toList());
		return s;
	}
	public List<String> findPricesCompletable(String product) {
		
		List<CompletableFuture<String>> priceFutures = 
				shops.stream()
				.map(shop -> 
				CompletableFuture.supplyAsync(
					() -> shop.getName() + " price is " + 
				shop.getPrice(product)))
				.collect(Collectors.toList());
		
		List<String> s = priceFutures.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
		return s;
	}
	
	public List<String> findPricesCompletableExecutor(String product) {
		
		List<CompletableFuture<String>> priceFutures = 
				shops.stream()
				.map(shop -> 
				CompletableFuture.supplyAsync(
					() -> shop.getName() + " price is " + 
				shop.getPrice(product),executor))
				.collect(Collectors.toList());
		
		List<String> s = priceFutures.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
		return s;
	}
//	public List<String> findPrices(String product) {
//	    List<CompletableFuture<String>> priceFutures =
//	        shops.stream()
//	             .map((Shop shop) -> CompletableFuture.supplyAsync(	
//	                              () -> shop.getPrice(product), executor))
//	             .map((String future) -> future.thenApply(Quote::parse))	
//	             .map(future -> future.thenCompose(quote ->	
//	                         CompletableFuture.supplyAsync(
//	                           () -> Discount.applyDiscount(quote), executor)))
//	                .collect(Collectors.toList());
//
//	    return priceFutures.stream()
//	            .map(CompletableFuture::join)	
//	            .collect(Collectors.toList());
//	}

//	public List<String> findPricesWithDiscoun1(String product) {
//		List<CompletableFuture<String>> priceFutures = 
//				shops.stream()
//				.map(shop -> 
//				CompletableFuture.supplyAsync(() -> 
//				() -> shop.getPriceNew(shop.getName()), executor ))
//				
//				.map(future -> future.thenApply(Quote::parse))
//
//				.map(future -> future.thenCompose
//				(
//				quote -> 
//			    CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)
//			    )
//				)
//				
////				.map(Discount::applyDiscount)
//				
//				
//				
//				.collect(Collectors.toList());
//			
//				
////				shop.getPriceNew(shop.getName()))
//				
////		   	CompletableFuture.supplyAsync(
////						() -> shop.getPriceNew(shop.getName()), executor ))
//				
//				
//				/** map the String to a quote object **/
//				.map(Quote::parse)
//				
//				/** return a string with the discount applied **/
//				.map(Discount::applyDiscount)
//				
//				/** collect into a List **/
//				.collect(Collectors.toList());
//		return  null;
//	}
	
	public List<String> findPricesSequential(String product) {
		List<String> s = shops.stream().map(shop ->

		String.format("%s price is %.2f", shop.getName(), shop.getPD(product))

		).collect(Collectors.toList());
		return s;
	}
	public List<String> findPricesWithDiscountCustom(String product) {
		
		ForkJoinPool customThreadPool = new ForkJoinPool(8);
		
		List<String> list =  null ;
		try {
			list = 
			customThreadPool.submit(() -> 
					shops.parallelStream()
					.map(shop -> shop.getPriceNew(shop.getName()))
					
					/** map the String to a quote object **/
					.map(Quote::parse)
					
					/** return a string with the discount applied **/
					.map(Discount::applyDiscount)
					
					/** collect into a List **/
					.collect(Collectors.toList())).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<String> findPricesWithDiscount(String product) {
		return shops.parallelStream()
				.map(shop -> shop.getPriceNew(shop.getName()))
				
				/** map the String to a quote object **/
				
				.map(Quote::parse)
				
				/** return a string with the discount applied **/
				.map(Discount::applyDiscount)
				
				/** collect into a List **/
				.collect(Collectors.toList());
	}
/** come back too **/
//	public void findPricesWithDiscount1(String product) {
//		return shops.stream()
//				.map(
//					shop -> CompletableFuture<String>.supplyAsync(() -> 
//						shop.getPriceNew(shop.getName()), executor )
//				)
//				
//				/** map the String to a quote object **/
//				
//				.map(( CompletableFuture<String> future) -> future.thenApply(
//						q -> Quote.parse(q))).;
//			
//				
				
//				.map(Quote::parse)
				
//				.map(future -> future.thenCompose
//				(
//				quote -> 
//			    CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)
//			    )
//				).
				
				
				/** return a string with the discount applied **/
//				.map(Discount::applyDiscount)
				
				/** collect into a List **/
//				.collect(Collectors.toList());
//	}
	
	/**
	 * Here we use completable futures to do the process 
	 * asynchronously
	 * @param product
	 * @return
	 */
//	public List<String> findPricesWithDiscountCompletable(String product) {
//		
//		
//		List<CompletableFuture<String>> priceFutures = 
//		shops.stream()
//		.map(shop -> 
//   	CompletableFuture.supplyAsync(
//				() -> shop.getPriceNew(shop.getName()), executor ))
//		.map(future -> future.thenApply(Quote::parse)))
//		.map(future -> future.thenCompose(quote -> 
//		CompletableFuture.supplyAsync(
//				() -> Discount.applyDiscount(quote), executor)))
//		.collect(Collectors.toList()));
//		return priceFutures.stream()
//				.map(CompletableFuture::join)
//				.collect(Collectors.toList());
//	}
	
//	public List<String> findPrices(String product) {
//	    List<CompletableFuture<String>> priceFutures =
//	        shops.stream()
//	             .map(shop -> CompletableFuture.supplyAsync(	
//	                              () -> shop.getPrice(product), executor))
//	             .map(future -> future.thenApply(Quote::parse))	
//	             .map(future -> future.thenCompose(quote ->	
//	                         CompletableFuture.supplyAsync(
//	                           () -> Discount.applyDiscount(quote), executor)))
//	                .collect(toList());
//
//	    return priceFutures.stream()
//	            .map(CompletableFuture::join)	
//	            .collect(toList());
//	}

//    public Stream<CompletableFuture<String>> findPricesStream(String product) {
//        return shops.stream()
//                .map((Shop shop) -> CompletableFuture.supplyAsync(() -> shop.getPriceNew(shop.getName()), executor))
//                .map(future -> future.thenApply(Quote::parse))
//                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
//    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = new Client();
		long start = System.nanoTime();
//		Utility.p(client.findPricesSequential("myPhone27S"));
//		Utility.p(client.findPricesCompletableExecutor("myPhone27S"));
//		Utility.p(client.findPricesCompletable("myPhone27S"));
//		Utility.p(client.findPricesWithDiscount("myPhone27S"));
		Utility.p(client.findPricesWithDiscountCustom("myPhone27S"));

		long duration = (System.nanoTime() - start)/ 1_000_000;
		Utility.p("Done in " + duration + "msecs");
	}

}
