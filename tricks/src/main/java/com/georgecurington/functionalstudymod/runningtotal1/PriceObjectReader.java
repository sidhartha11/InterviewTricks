/**
 * 
 */
package com.georgecurington.functionalstudymod.runningtotal1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.georgecurington.functionalstudymod.design.singleton.DemandHolderForPoisonPill;
 
/**
 * <pre>
 * This Callable supplies input the back end threads.
 * The input is generated in one of three possible ways
 * <ul>
 * <li> data is read in from a file 
 * <li> data is generated using a IntStream.generate sequence ( infinite with this approach )
 * <li> data is generated using a Spliterator
 * </ul>
 * </pre>
 * 
 * <pre>
 * The data is queued into a Deque. Both Callables are effectively immutable classes.
 * This means the following holds true:
 * One copy of the classes can be used via executor unless you want the classes to process
 * different input data.
 * </pre>
 * 
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 * @see com.georgecurington.functionalstudymod.runningtotal1.ControllableInfiniteSequence
 *
 */

public class PriceObjectReader implements Callable<Integer> {
	private static final boolean DEBUG=false;
	private static final int LIMIT=10_000;
	private final String filename;
	private final ConcurrentLinkedDeque<MiniInputIntf> deque;
	private final CountDownLatch countDownLatch;
	/**
	 * 
	 */
	public PriceObjectReader(String filename) {
		this.filename=filename;
		deque=null;
		this.countDownLatch = null;
	}
	
	public PriceObjectReader(ConcurrentLinkedDeque<MiniInputIntf> deque, CountDownLatch countDownLatch){
		this.deque=deque;
		filename=null;
		this.countDownLatch = countDownLatch;
	}

	private int init(String fileName) {
		new ArrayList<>();
		StupidLambdaCounter currenttime = new StupidLambdaCounter(0);

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.map(p -> p.split(",")).map(MiniInput::new).forEach(p -> {
				currenttime.getAndUpdate(1);
				boolean b = deque.offer(p);
				if ( DEBUG ) {
				if (b) {
					System.out.println(b + ": processed: " + p);
				} else {
					System.out.println(b + ": <<< processed: " + p);

				}
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("offering POISON PILL");
			deque.offer((MiniInputIntf) MiniInput.POISON);
		}
		return currenttime.getAndUpdate(0);
	}

	private int initGenerate() {
		new ArrayList<>();
		StupidLambdaCounter counter = new StupidLambdaCounter(0);
		Timecntr currenttime = new Timecntr(PriceObjectImplDataGenerator.currenttime);
		try {
			Stream.generate(() -> PriceObjectImplDataGenerator.generate(currenttime)).map(p -> p.split(","))
					.map(MiniInput::new).forEach(p -> {
						counter.getAndUpdate(1);
						boolean b = deque.offer(p);
						if ( DEBUG ){
						if (b) {
							System.out.println(b + ": processed: " + p);
						} else {
							System.out.println(b + ": <<< processed: " + p);

						}
						}
					});
		} finally {
			System.out.println("offering POISON PILL");
			deque.offer((MiniInputIntf) MiniInput.POISON);
		}
		return counter.getAndUpdate(0);
	}
	
	private int initGenerateSplit() {
		new ArrayList<>();
		StupidLambdaCounter counter = new StupidLambdaCounter(0);
		new Timecntr(PriceObjectImplDataGenerator.currenttime);
		try {
			Stream<String> stream = StreamSupport.stream(new ControllableInfiniteSequence(LIMIT), false);
			stream.map(p -> p.split(","))
					.map(MiniInput::new).forEach(p -> {
						counter.getAndUpdate(1);
						boolean b = deque.offer(p);
						if ( DEBUG ) {
						if (b) {
							System.out.println(b + ": processed: " + p);
						} else {
							System.out.println(b + ": <<< processed: " + p);

						}
						}
					});
		} finally {
			System.out.println("offering POISON PILL");
			deque.offer((MiniInputIntf) DemandHolderForPoisonPill.getPoison());
		}
		return counter.getAndUpdate(0);
	}

	@Override
	public Integer call() throws Exception {
		if ( countDownLatch != null ){
			System.out.println("waiting for latch");
			countDownLatch.await();
		}
		return initGenerateSplit();
	}

}
