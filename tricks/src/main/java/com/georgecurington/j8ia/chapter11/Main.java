/**
 * 
 */
package com.georgecurington.j8ia.chapter11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static com.georgecurington.j8ia.chapter11.Java8utils.*;
import com.georgecurington.j8ia.chapter11.ExchangeService;
import com.georgecurington.j8ia.chapter11.Money;

/**
 * @author Owner
 *
 */
public class Main {
	List<ShopperApiIntf> shops = Arrays.asList(new Shop("BestPrice"), new Shop("LetsSaveBig"),
			new Shop("MyFavoriteShop"), new Shop("BuyItAll"), new Shop("AMC")
//		        new Shop("BestPrice2"), 
//		        new Shop("LetsSaveBig2"), 
//		        new Shop("MyFavoriteShop2"),
//		        new Shop("BuyItAll2"),
//		        new Shop("AMC2"),
//		        new Shop("BestPrice"), 
//		        new Shop("LetsSaveBig"), 
//		        new Shop("MyFavoriteShop"),
//		        new Shop("BuyItAll"),
//		        new Shop("AMC"),
//		        new Shop("BestPrice2"), 
//		        new Shop("LetsSaveBig2"), 
//		        new Shop("MyFavoriteShop2"),
//		        new Shop("BuyItAll2"),
//		        new Shop("AMC2"),
//		        new Shop("BestPrice"), 
//		        new Shop("LetsSaveBig"), 
//		        new Shop("MyFavoriteShop"),
//		        new Shop("BuyItAll"),
//		        new Shop("AMC"),
//		        new Shop("BestPrice2"), 
//		        new Shop("LetsSaveBig2"), 
//		        new Shop("MyFavoriteShop2"),
//		        new Shop("BuyItAll2"),
//		        new Shop("AMC2"),
//		        new Shop("BestPrice"), 
//		        new Shop("LetsSaveBig"), 
//		        new Shop("MyFavoriteShop"),
//		        new Shop("BuyItAll"),
//		        new Shop("AMC"),
//		        new Shop("BestPrice2"), 
//		        new Shop("LetsSaveBig2"), 
//		        new Shop("MyFavoriteShop2"),
//		        new Shop("BuyItAll2"),
//		        new Shop("AMC2")
	);

	public List<String> findPrices(String product) {
		Executor executor = getExecutor(8, 100, shops.size());
		List<CompletableFuture<String>> priceFutures = shops.stream()
				/**
				 * This section retrieves asynchronously the non-discounted price from the list
				 * of shops
				 */
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
				/**
				 * When the price comes back parse it and create a Quote object from the the
				 * parts of it gotton from the getPrice method. String.format("%s:%.2f:%s",
				 * name,price,code.name());
				 * 
				 * Note: the thenApply is done synchronously. Supposedly, this operation does
				 * not take that much time so it just creates a Quote object from the processed
				 * Completable Future that is passed to it.
				 */
				.map(future -> future.thenApply(Quote::parse))
				/**
				 * Then compose the resulting Future with another asynchronous task, applying
				 * the discount code. returning a resultant list of CompletableFutures Note that
				 * the thenCompose is another asynchronously executed function, i.e. the exector
				 * is used
				 */
				.map(future -> future.thenCompose(
						quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
				.collect(toList());
		System.out.println("priceFugures = " + priceFutures.toString());
		/**
		 * Finally join all the asynchronous elements of the the previous collected list
		 * of Completable Futures.
		 */
		List<String> ret = priceFutures.stream().map(CompletableFuture::join).collect(toList());
		System.out.println("final ret = " + ret.toString());
		return ret;
	}

	public List<String> findPricesNoComment(String product) {
		Executor executor = getExecutor(8, 100, shops.size());
		List<CompletableFuture<String>> priceFutures = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
				.map(future -> future.thenApply(Quote::parse))
				.map(future -> future.thenCompose(
						quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
				.collect(toList());
		List<String> ret = priceFutures.stream().map(CompletableFuture::join).collect(toList());
		System.out.println("final ret = " + ret.toString());
		return ret;
	}
	
	public Stream<CompletableFuture<String>> findPricesStream(String product) {
		Executor executor = getExecutor(8,100,shops.size());

		return shops.stream()
	             .map(shop -> CompletableFuture.supplyAsync(
	                                   () -> shop.getPrice(product), executor))
	             .map(future -> future.thenApply(Quote::parse))
	             .map(future -> future.thenCompose(quote ->
	                      CompletableFuture.supplyAsync(
	                          () -> Discount.applyDiscount(quote), executor)));
	}
	public Stream<CompletableFuture<String>> findPricesPipeLine(String product) {
		try {
		Executor executor = getExecutor(8,100,shops.size());
		Stream<CompletableFuture<String>> stream = 
				shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
				.map(future -> future.thenApply(Quote::parse))
				.map(future -> future.thenCompose(
						quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor))).peek(System.out::println);
		return stream;
		} catch(Throwable t) {
			System.out.println("caugh exception:" + t.getMessage());
		}
		return null;		
	}

	public List<String> findPricesSynchronous(String product) {

		return shops.stream().map(shop -> shop.getPrice(product)).map(Quote::parse).map(Discount::applyDiscount)
				.collect(toList());
	}

	public List<String> findPricesOldWithExec(String product) {
		// return
		Executor exec = getExecutor(8, 100, shops.size());
		List<CompletableFuture<String>> futures = shops.stream().map(shop -> CompletableFuture.supplyAsync(
				() -> String.format("%s price is %.2f", shop.getName(), shop.getPriceDouble(product))/** ,exec **/
		)).collect(toList());
		return futures.stream().map(CompletableFuture::join).collect(toList());
	}

	public List<CompletableFuture<String>> findPricesCompletableWithOutJoin(String product) {
		return shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(
						() -> String.format("%s price is %.2f", shop.getName(), shop.getPriceDouble(product))))
				.collect(toList());
	}

	public List<String> findPricesParallelStream(String product) {
		return shops.parallelStream()
				.map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPriceDouble(product)))
				.collect(toList());
	}

	public List<String> findPricesNonParralel(String product) {
		return shops.stream()
				.map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPriceDouble(product)))
				.collect(toList());
	}

	private void test_2() {
		ShopperApiIntf shop = new Shop("BestShop");
		long start = System.nanoTime();
		Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		peep(String.format("Invocation returned after %d msecs", invocationTime));
		// Do some more tasks, like querying other shops
		doSomethingElse();
		try {
			double price = futurePrice.get(4000, TimeUnit.MILLISECONDS);
			peep(String.format("Price is %.2f", price));
		} catch (ExecutionException ee) {
			peep(String.format("got exception %s", ee.getMessage()));
		} catch (TimeoutException to) {
			peep(String.format("got timeout %d", 4000));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
		peep(String.format("Price returned after %d msecs", retrievalTime));

	}

	private void test_3() {
		long start = System.nanoTime();
		peep(findPricesParallelStream("myPhone27s"));

		long duration = ((System.nanoTime() - start) / 1_000_000);
		peep(String.format("Done in %d msecs", duration));
	}

	private Executor getExecutor(int nmbrCpu, int waitCompRatio, int nmbrTasks) {
		Executor exec = Executors.newFixedThreadPool(Math.min(nmbrTasks, 100), new ThreadFactory() {
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setDaemon(true);
				return t;
			}
		});
		return exec;
	}

	private void test_1() {
		long start = System.nanoTime();
		peep(findPricesNoComment("myPhone27s"));

		long duration = ((System.nanoTime() - start) / 1_000_000);
		peep(String.format("Done in %d msecs", duration));

	}
	
	private CompletableFuture<Double> futurePriceInUSD(String product,ShopperApiIntf shop) {
		
		CompletableFuture<Double> futurePriceInUSD = 
				CompletableFuture.supplyAsync(() -> shop.getPriceDouble(product))
				.thenCombine(
						CompletableFuture.supplyAsync(
								() -> ExchangeService.getRate(Money.EUR,Money.USD)),
						(price,rate) -> price * rate 
						);
		return futurePriceInUSD ; 

	}
	
	private void test_4()  {
		long start = System.nanoTime();
		
		List<CompletableFuture<Double>> comps = 
				shops.stream()
				.map(shop -> futurePriceInUSD("prod1", shop)).collect(toList());
		List<Double> ret = comps.stream().map(CompletableFuture::join).collect(toList());
		
		peep(ret);

		long duration = ((System.nanoTime() - start) / 1_000_000);
		peep(String.format("Done in %d msecs", duration));
	}
	
	private void test_5() {
		long start = System.nanoTime();
		CompletableFuture[] futures = findPricesStream("myPhone27S")
		        .map(f -> f.thenAccept(
		             s -> System.out.println(s + " (done in " +
		                  ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
		        .toArray(size -> new CompletableFuture[size]);
		CompletableFuture.allOf(futures).join();
		System.out.println("All shops have now responded in "
		                   + ((System.nanoTime() - start) / 1_000_000) + " msecs");
	
	}

	public Main() {
		System.out.println("running test");
		test_5();
		System.out.println("procs=" + Runtime.getRuntime().availableProcessors());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
