/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre>
 * <p><b>INTERVIEW TRICKS</b></p>
 * </pre>
 * <p>
 * ================================================
 * </p>
 * <br>
 * 
 * @author George Curington
 * @version 1.0.0
 * @since Feb 12, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class CompletableExamples {
	ExecutorService executor = Executors.newFixedThreadPool(10);
	static List<String> webPageLinks;
	static {
		webPageLinks = new ArrayList<>(100);
		IntStream.range(0, 100).mapToObj(p -> "page_" + p + ".html").forEach(webPageLinks::add);
		Utility.p("created " + webPageLinks.size() + " web pages");
	}

	/**
	 * 
	 */
	public CompletableExamples() {
		erroranalysis2();
	}
	private void erroranalysis2() {
		CompletableFuture<String> maturityFuture = 
				CompletableFuture.supplyAsync( () -> {
					if ( age == 0 ) {
						throw new IllegalArgumentException("Age can not be negaive");
					}
					if ( (age + 1) == 0 ){
						int x = 2 / ( age + 1);
						return "Alien";
					}
					else if ( age > 18 ) {
						int x = 2 / ( age + 1);
						return "Adult";
					} else {
						return "Child";
					}
				}).handle((res , ex) -> {
					if ( ex != null ){
						Utility.p("Opps! We have an exception - " + ex.getMessage());
					    return "Unknown!";
					}
					
					return res;
				});
			try {
				Utility.p("returned " + maturityFuture.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	Integer age = -1 ;
	private void erroranalysis1() {
		CompletableFuture<String> maturityFuture = 
				CompletableFuture.supplyAsync(() -> {
					if ( age < 0 ) {
						throw new IllegalArgumentException("Age can not be negative");
					}
					
					if( age > 18 ) {
						return "Adult";
					} else {
						return "Child";
					}
				}).exceptionally(ex -> {
					Utility.p("Oops! We have an exception - " + ex.getMessage());
					return "Unknown!";
				});
		
		try {
			Utility.p("Maturity : " + maturityFuture.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void complexExample7() {
		/**
		 * call the supplyAsync method of CompletableFuture:
		 * which takes a Supplier<T>  a method that simply supplies a value
		 * used to complete the associated future of the CompletablFuture
		 */
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch ( InterruptedException e ) {
				throw new IllegalStateException (e);
			}
			return "Result of Future 1";
		});
		
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch ( InterruptedException e) {
				throw new IllegalStateException ( e );
			}
			
			return "Result of Future 2";
		});
	
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch ( InterruptedException e) {
				throw new IllegalStateException (e);
			}
			return "Result of Future 3";
		});
		
		/** now get the first one that completes **/
		CompletableFuture<Object> anyOfFuture = 
				CompletableFuture.anyOf(future1,future2,future3);
		try {
			Utility.p(anyOfFuture.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	CompletableFuture<String> downloadWebPage(String pageLink) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Throwable e) {
				throw new IllegalStateException(e);
			}
			/** generate a couple random strings **/
			int r = ThreadLocalRandom.current().nextInt(0, 3);
			String s = "bobcat";
			switch (r) {
			case 0:
				s = "ThreadLocal";
				break;
			case 1:
				s = "CompletableFuture";
				break;
			case 2:
				s = "TimeUnit.SECONDS.sleep";
			}
			return s + "_webpage_" + ThreadLocalRandom.current().nextInt();
		});
	}

	private void complexExample6() {
    /** download the contents of all webpages asynchronously **/
		List<CompletableFuture<String>> pageContentFutures = webPageLinks.stream()
				.map((String webPagelink) -> downloadWebPage(webPagelink)).collect(Collectors.toList());
		/** Create a combined Future using allOf() **/
		CompletableFuture<Void> allFutures = CompletableFuture
				.allOf(pageContentFutures.toArray(new CompletableFuture[pageContentFutures.size()]));
		// when all the futures are completed, call future.join()
		// to get their results and collect the results in a list
		CompletableFuture<List<String>> allPageContentsFuture = 
				allFutures
				.thenApply(v -> {
			    return pageContentFutures
			    		.stream()
			    		.map(pageContentFuture -> pageContentFuture.join())
					    .collect(Collectors.toList());
		});
		
		/** count the number of pages that contain the keyword **/
		CompletableFuture<Long> countFuture = 
				allPageContentsFuture
				.thenApply( pageContents -> {
			return pageContents
					.stream()
					.filter(pageContent -> pageContent.contains("CompletableFuture"))
					.count();
		});
		try {
			Utility.p("Number of pages containing keywork=" + countFuture.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void complexExample5() {
		Utility.p("retrieving weight");
		CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Throwable e) {
				throw new IllegalStateException(e);
			}
			return 65.0;
		});
		Utility.p("retrieving height");
		CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Throwable e) {
				throw new IllegalStateException(e);
			}
			return 177.8;
		});

		Utility.p("Calculating BMI");
		CompletableFuture<Double> combinedFuture = weightInKgFuture.thenCombine(heightInCmFuture,
				(weightInKg, heightInCm) -> {
					Double heightInMeter = heightInCm / 100;
					return weightInKg / (heightInMeter * heightInMeter);
				});

		try {
			Utility.p("Your BMI is - " + combinedFuture.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void complexExample4() {

		CompletableFuture<CompletableFuture<Double>> result = getUsersDetail("blazay user")
				.thenApply(user -> getCreditRating(user));
		try {
			Utility.p("result is " + result.get().get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CompletableFuture<Double> result2 = getUsersDetail("good user").thenCompose(user -> getCreditRating(user));

		try {
			Utility.p("second nonnested = " + result2.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	CompletableFuture<User> getUsersDetail(String userId) {
		return CompletableFuture.supplyAsync((Supplier<User>) () -> {
			return UserService.getUserDetails(userId);
		});
	}

	CompletableFuture<Double> getCreditRating(User user) {
		return CompletableFuture.supplyAsync((Supplier<Double>) () -> {
			return UserService.getCreditRating(user);
		});
	}

	private void complexExample3() {
		CompletableFuture<String> c = CompletableFuture.supplyAsync(() -> {
			return "some result";
		}).thenApplyAsync(result -> {
			return "Processed Result";
		}, executor);
		try {
			Utility.p("got " + c.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.shutdownNow();
	}

	private void complexExample2() {
		Utility.p("running");
		final int productId = 10;

		CompletableFuture.supplyAsync(() -> {
			Utility.p("inside first async");
			try {
				Utility.p("in try");
				// TimeUnit.SECONDS.sleep(1);
				Utility.p("after sleep");
			} catch (Throwable e) {
				Utility.p("in catch:" + e.toString());
				e.printStackTrace();
			}
			Utility.p("returning ...");
			return ProductService.getProductDetail(productId);
		}).thenApplyAsync((Product result) -> {
			try {
				Utility.p("sleeping");
				// Thread.sleep(2000);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Utility.p("Got product detail from remote service ");
			return result;
		}, executor);

	}

	private void complexExample1() {
		Utility.p("running");
		final int productId = 10;

		CompletableFuture.supplyAsync(() -> {
			Utility.p("inside first async");
			return ProductService.getProductDetail(productId);
		}).thenRunAsync(() -> {
			Utility.p("Got product detail from remote service ");
		});

	}

	private void trivialExample8() {
		final int productId = 10;

		CompletableFuture.supplyAsync(() -> {
			return ProductService.getProductDetail(productId);
		}).thenAccept(product -> {
			Utility.p("Got product detail from remote service " + product.getName());
		});

	}

	private void trivialExample7() {
		CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			Utility.p("one");
			return "Rejeev";
		}).thenApply(name -> {
			Utility.p("two");
			return "Hello " + name;
		}).thenApply(greeting -> {
			Utility.p("three");
			return greeting + " , Welcome to the calli";
		});

		try {
			Utility.p(welcomeText.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void trivialExample6() {
		// create a completablefuture
		CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "returning from async call";
		});

		// Attach a call back to the Future using theApply()
		CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> "Hello " + name);

		// Block and get the result of the future.
		try {
			System.out.println(greetingFuture.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void trivialExample5() {
		Executor executor = Executors.newFixedThreadPool(10);
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);

			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "returning from asynccall";
		}, executor);

		try {
			Utility.p(future.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void trivialExample4() {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}

			return "Result of computation";
		});
		try {
			Utility.p(future.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void trivialExample3() {
		// Run a task specified by a Supplier object asynchronously
		CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					throw new IllegalStateException(e);
				}

				return "Result of the call";
			}
		});
		try {
			Utility.p(future.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void trivialExample2() {
		/**
		 * Example passing a lambda expression to the runAsync method of
		 * CompletableFuture
		 */

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			// simulate long running job
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}

			Utility.p("I'll run in a separate thread");
		});
		try {
			Utility.p(future.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void trivialExample() {
		/**
		 * Note how the call .runAsync actually starts the thread , no need for
		 * the usual start command with normal threads.
		 */
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {

			@Override
			public void run() {
				// Simulate a long-running job
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					throw new IllegalStateException(e);
				}

				Utility.p("I will run in a separate thread");
			}

		});

		// block and wait for the future to complete
		Utility.p("waiting for future to complete");
		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CompletableExamples c = new CompletableExamples();

	}

}
