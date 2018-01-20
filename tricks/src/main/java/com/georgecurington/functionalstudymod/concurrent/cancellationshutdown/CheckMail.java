/**
 * 
 */
package com.georgecurington.functionalstudymod.concurrent.cancellationshutdown;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * <pre>
 * Example 7.20 Checking mail with the lifetime of the executor
 * bounded by method bounday
 * </pre>
 * <pre>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 19, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * </pre>
 */
public class CheckMail {

	/**
	 * 
	 */
	public CheckMail() {
		// TODO Auto-generated constructor stub
	}

	boolean checkMail (Set<String> hosts,long timeout , TimeUnit unit )
	throws InterruptedException 
	{
		/** local executor used and is bounded by the lifetime of this method **/
		ExecutorService exec = Executors.newCachedThreadPool();
		
		/** AtomicBoolean used because we need to manipulate it within the 
		 * anonomous class
		 */
		final AtomicBoolean hasNewMail = new AtomicBoolean(false);
		try {
		hosts.stream().forEach(p -> {
			exec.execute(new Runnable(){
				public void run() {
					if (checkMail(p)){
						hasNewMail.set(true);
					}
				}
			});
		});
		} finally {
			exec.shutdown();
			exec.awaitTermination(timeout, unit);
		}
	    return hasNewMail.get();
	}
	protected boolean checkMail(String p) {
		/** check mail for host p **/
		System.out.println("checking mail for " + p);
		return true;
	}

	/**
	 * <pre>
	 * For the last time, please remember that you can easily insert a null
	 * element into a Java HashSet.
	 * And in a LinkedHashSet also.
	 * BUT NOT IN A TREESET!!!!
	 * AND NOT IN CONCURRENTSKIPLISTSET
	 * AND NOT IN AN ENUMSET
	 * CopyOnWriteArraySet will allow a null
	 * @param args
	 */
	enum hostEnums {
		ahost,
		bhost,
		chost,
		rhost
	}
	public static void main(String[] args) {
//		Set<String> hosts = new HashSet<>(Arrays.asList("a","b","b",null,"r"));
//		Set<String> hosts = new LinkedHashSet<>(Arrays.asList("a","b","b",null,"r"));
//		Set<String> hosts = new TreeSet<>(Arrays.asList("a","b","b",null,"r"));
//		Set<String> hosts = new CopyOnWriteArraySet<>(Arrays.asList("a","b","b",null,"r"));
//		Set<String> hosts = new ConcurrentSkipListSet<>(Arrays.asList("a","b","b",null,"r"));
		Set<hostEnums> hosts = EnumSet.of(hostEnums.ahost, null,hostEnums.rhost);

		CheckMail cm = new CheckMail();
		try {
			boolean hasmail = cm.checkMail(new HashSet<>(Arrays.asList(hosts.toArray(new String[0]))),1000,TimeUnit.MILLISECONDS);
		    System.out.println("hasmail:"+hasmail);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

}
