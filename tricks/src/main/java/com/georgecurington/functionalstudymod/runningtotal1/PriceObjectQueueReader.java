/**
 * 
 */
package com.georgecurington.functionalstudymod.runningtotal1;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import com.georgecurington.functionalstudymod.design.singleton.DemandHolderForPoisonPill;

/**
 * <pre>
 * This Callable is part of set of objects that simulate processing 
 * some financial information. This object represents the terminal destination
 * of the input , MiniInputIntf , objects. The source of the data comes from either
 * the file system or it is dynamically generated using a SplitIterator.
 * This is code serves only an educational purpose to illustrate the use of various
 * JDK8 lambda functional things and JDK Concurrency issues. 
 * This code will be further enchanced to process the items it retrieves from the queue
 * in various ways ... yet to be imagined.
 * </pre>
 * 
 * <pre>
 * Note the use of ConcurrentLinkedDeque here. The idea here is to have multiple
 * threads processing both ends of the queue to increase thru-put and scalability.
 * Also, note the termination sequence. Since we are reading from both ends of the queue,
 * we can no longer terminate once the poison pill is discovered. We have to check for both
 * poison pill being discovered and the queue returning a null entry. 
 * 
 * The Callables work as follows using a Deque as the communication channel between threads:
 * 
 * PriceObjectReader  ===> ConcurrentLinkedDeque ===> PriceObjectQueueReader ====> further downstream processing.
 * and note that:
 * PriceObjectQueueReader reads from both ends of the queue. 
 * Also note that multiple copies of the producer and consumer can be executed. 
 * 
 * 
 * 
 * </pre>
 * 
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class PriceObjectQueueReader implements Callable<Integer> {
	private static final boolean DEBUG = false;
//	private volatile boolean running = true;
//	private volatile boolean finished = false;

	private final ConcurrentLinkedDeque<MiniInputIntf> deque;	
	private final Calculator<MiniInputIntf> calculator;

	private final CountDownLatch countDownLatch;

	/**
	 * @param countDownLatch 
	 * 
	 */
	public PriceObjectQueueReader(ConcurrentLinkedDeque<MiniInputIntf> deque, Calculator<MiniInputIntf> calculator, CountDownLatch countDownLatch) {
		this.deque = deque;
		this.calculator = calculator;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public Integer call() throws Exception {
		int i = 0;
		boolean running = true;
		boolean finished = false;
		MiniInputIntf input = null;
		PollDir pollDirection = null;
		if ( countDownLatch != null ){
			System.out.println("waiting for latch");
			countDownLatch.await();
		}
		while (!finished) {

			/** randomly pick which end of the queue to retrieve from **/
			pollDirection = PollDir.values()[ThreadLocalRandom.current().nextInt(0, PollDir.values().length)];
			if (DEBUG) {
				System.out.println("poll direction:" + pollDirection);
			}
			switch (pollDirection) {
			case first:
				input = deque.pollFirst();
				break;
			case last:
				input = deque.pollLast();
				break;
			}
			/** if nothing in the queue, just sleep a bit and try again **/
			if (input == null && running == false) {
				finished = true;
				if (DEBUG) {
					System.out.println("FINISHED!!");
				}
			} else if (input == null) {
				if (DEBUG) {
					System.out.println("return null");
				}
				Thread.sleep(ThreadLocalRandom.current().nextLong(0, 250));

				/** if the input represents a poison pill, time to stop **/
			} else if (input.equals((MiniInputIntf) DemandHolderForPoisonPill.getPoison())) {
				running = false;
			} else {
				i++;
				if (DEBUG) {
					System.out.println(i + " processing " + input);
				}
				calculator.process(input);
			}
		}
		return i;
	}

}
