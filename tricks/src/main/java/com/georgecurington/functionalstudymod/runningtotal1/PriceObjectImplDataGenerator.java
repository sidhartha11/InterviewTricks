/**
 * 
 */
package com.georgecurington.functionalstudymod.runningtotal1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import com.georgecurington.functionalstudymod.testdata.TestData;


/**
 * @author george
 *
 */
public interface PriceObjectImplDataGenerator {
	static final long MAXID=4000;
	static final long MAXSHARESPERDAY=1000;
	static final long MAXSHAREPRICE=400;
	static final long SLEEPTIME = 250;
	static final long CURRENTOFFSET = 250;
	long currenttime = System.currentTimeMillis();
	static final boolean DEBUG=false;
	
	/**
	 * <pre>
	 * From JDK DOCS
	 * @see https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentLinkedDeque.html
	 * An unbounded concurrent deque based on linked nodes. Concurrent insertion, removal, and access 
	 * operations execute safely across multiple threads. 
	 * A ConcurrentLinkedDeque is an appropriate choice when many threads will share 
	 * access to a common collection. Like most other concurrent collection implementations, 
	 * this class does not permit the use of null elements.
	 * 
	 * Iterators and spliterators are weakly consistent.
	 * 
	 * Beware that, unlike in most collections, the size method is NOT a constant-time operation. 
	 * Because of the asynchronous nature of these deques, determining the current number of elements 
	 * requires a traversal of the elements, and so may report inaccurate results if this 
	 * collection is modified during traversal. 
	 * Additionally, the bulk operations addAll, removeAll, retainAll, containsAll, 
	 * equals, and toArray are not guaranteed to be performed atomically. 
	 * For example, an iterator operating concurrently with an addAll operation might view 
	 * only some of the added elements.
	 * </pre>
	 * 
	 */
	static final Queue<MiniInputIntf> miniInputDeque = 
			new ConcurrentLinkedDeque<>();
	
	public static long getRandomId(long s, long e){
		return ThreadLocalRandom.current().nextLong(s, e+1);
	}
	
	public static double getRandomFloatId(double s, double e){
		return ThreadLocalRandom.current().nextDouble(s, e+1);
	}

	/**
	 * <pre>
	 * load a file with test data.
	 * </pre>
	 * @param file
	 * @param records
	 */
	public static void generate(String file, int records) {

		Charset charset = Charset.forName("US-ASCII");
		Path p1 = Paths.get(file);
		Timecntr currenttime = new Timecntr(PriceObjectImplDataGenerator.currenttime);
		try (BufferedWriter writer = Files.newBufferedWriter(p1, charset)) {
			IntStream.rangeClosed(1,records).forEach( p -> {
				
			
			/** generate an id **/
			long id = getRandomId(1,MAXID);
			
			/** generate 3 character acronym **/
			String acronym = TestData.getSaltString(3,-1);
			
			/** generate number of shares sold at this time **/
			long currentSharesSold = getRandomId(1,MAXSHARESPERDAY);
			
			/** generate current share price at this time **/
			double currentSharePrice = getRandomFloatId(1,MAXSHAREPRICE);
			
			/** generate current time **/
			/**
			 * cannot use LocalDateTime to generate the time element because it is too slow
			 * to generate simulation data using LocalDateTime. Instead I will use milliseconds based
			 * on the and increment by some offset 
			 */
			// LocalDateTime localDateTime = LocalDateTime.now();
			long time=currenttime.getAndUpdate(CURRENTOFFSET);
			
			String s = 
					String.format("%4d,%3s,%4d,%7.2f,%d%n",
							id,
							acronym,
							currentSharesSold,
							currentSharePrice,
							time
							);
			System.out.println(s);

			try {
				writer.write(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
			
			});
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * create one record of test data. This is meant to be called
	 * repeatedly by some other code. Currently I call it from either
	 * an IntStream.generate or a Spliterator.
	 * @param currenttime
	 * @return
	 */
	public static String generate(Timecntr currenttime) {

		long time=currenttime.getAndUpdate(CURRENTOFFSET);
		
			/** generate an id **/
			long id = getRandomId(1,MAXID);
			
			/** generate 3 character acronym **/
			String acronym = TestData.getSaltString(3,-1);
			
			/** generate number of shares sold at this time **/
			long currentSharesSold = getRandomId(1,MAXSHARESPERDAY);
			
			/** generate current share price at this time **/
			double currentSharePrice = getRandomFloatId(1,MAXSHAREPRICE);
			
			String s = 
					String.format("%4d,%3s,%4d,%7.2f,%d%n",
							id,
							acronym,
							currentSharesSold,
							currentSharePrice,
							time
							);
			if (DEBUG) {
			System.out.println("generating .... " + s );
			}
			
			return s;
	}
	
	public static void main(String[] args){
		System.out.println("running an interface");
		generate("C:\\temp\\pricedata.txt",200_000);
	}

}
