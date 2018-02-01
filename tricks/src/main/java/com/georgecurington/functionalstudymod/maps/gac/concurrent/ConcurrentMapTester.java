/**
 * 
 */
package com.georgecurington.functionalstudymod.maps.gac.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.IntStream;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 31, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class ConcurrentMapTester {
	private final ConcurrentMap<Integer,Integer> map ;
	
	
	{
		/**
		 * creating a map with an initial capacity of a meg
		 */
		map = new ConcurrentHashMap<>(1_000_000);
		IntStream.rangeClosed(1,10).forEach(p -> {
			map.put(p, p*p);
		});
	}
	/**
	 * 
	 */
	public ConcurrentMapTester() {
		Utility.p("map=" + map);
		testInterfaceDefaultMethods();
	}

	private void testInterfaceDefaultMethods() {
		/** testing getOrDefault **/
		p("map.getOrDefault(354,99)=" + map.getOrDefault(354, 99));
		p("map.forEach((x,y) -> System.out.println(x,y)");
		map.forEach((x,y)-> System.out.println(x + "," + y));
		map.replaceAll((x,y) -> x + y);
		Utility.p("map=" + map);
	}

	private void nullTest() {
		Utility.p("map=" + map);
		/** attempting to put null in key **/
		try {
			map.put(null,10);
		} catch ( Throwable t) {
			Utility.p("cannot put null in key");
		}
		
		/** attempting to put null in value **/
		try {
			map.put(10,null);
		} catch ( Throwable t) {
			Utility.p("cannot put null in value");
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ConcurrentMapTester();

	}	
	
	public static void p(String msg){
		Utility.p(msg);
	}
}
