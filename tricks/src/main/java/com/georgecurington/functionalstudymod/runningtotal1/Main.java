/**
 * 
 */
package com.georgecurington.functionalstudymod.runningtotal1;

import static com.georgecurington.functionalstudymod.runningtotal1.PriceObjectImplDataGenerator.miniInputDeque;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/** 
 * <pre>
 * This main program is a driver for the simulated pricing
 * MiniInput program.
 * </pre>
 * @author george
 *
 */
public class Main {

	/**
	 * 
	 */
	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String ...args){
		
		/** create a CountDownLatch to make everyone start at the same time **/
		CountDownLatch countDownLatch = new CountDownLatch(1);
		/** create an executor service **/
//		ExecutorService exec = Executors.newCachedThreadPool();
		ExecutorService exec = Utility.myCachedThreadPool("queuer");

		
		/** create a Calculator Object **/
		Calculator<MiniInputIntf> calculator = new CalculatorImpl<>();
		
		/** create a producer and consumer Callable(s) **/
		Callable<Integer> reader = new PriceObjectReader((ConcurrentLinkedDeque<MiniInputIntf>) miniInputDeque,countDownLatch);
		Callable<Integer> readerq = new PriceObjectQueueReader((ConcurrentLinkedDeque<MiniInputIntf>) miniInputDeque,
				calculator,countDownLatch);
		
		
		List<Future<Integer>> f = new ArrayList<>();
		
		f.add(exec.submit(readerq));
		f.add(exec.submit(reader));
		
		try {
			Thread.sleep(250);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/** countdown on the latch **/
		countDownLatch.countDown();
		
		for ( Future<Integer> ele : f ){
		try {
			int nmbRead = ele.get();
			System.out.println("read " + nmbRead + " items");
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		exec.shutdown();
		/**
		 * Blocks until all tasks have completed execution after a shutdown 
		 * request, or the timeout occurs, or the current thread is interrupted, 
		 * whichever happens first.
		 * 
		 */
		try {
			while ( !exec.awaitTermination(5000, TimeUnit.MILLISECONDS)){
				System.out.println("waiting to shutdown");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<Long,PriceObject> map = calculator.getMap();
		List<MiniInputIntf> nmap = calculator.getPriceObjectList();
		
		 Comparator<MiniInputIntf> byAcronym = (e1, e2) -> e1.getAcronymn().compareTo(e2.getAcronymn());
		 Comparator<MiniInputIntf> byId = (e1, e2) -> Long.compare(e1.getId(),e2.getId());

		 
		 nmap
		.stream()
		.sorted(byId)
		.forEach(System.out::println);

//		 Map<Long, List<MiniInputIntf>> listsPerId = 
				 nmap.stream()
				  .collect(Collectors.groupingBy(MiniInputIntf::getId))
				  .forEach((k,v) -> 
				  {
				  System.out.println("key=" + k );
				  v.stream().forEach(System.out::println);
				  });
		
		/**
		 * 
		 */
		
//		calculator.dumpPriceObjects();
		
	}

}
