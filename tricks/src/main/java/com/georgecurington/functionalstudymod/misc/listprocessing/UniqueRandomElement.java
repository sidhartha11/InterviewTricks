/**
 * 
 */
package com.georgecurington.functionalstudymod.misc.listprocessing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;

/**
 * @author george
 * <p>
 * <pre>
 * This is a simple way to return random names from a collection
 * of names, never using the same name twice until all names in
 * the collection have been exhausted. Once that happens, the program
 * starts over with the orignal collection of names. This is done as follows:
 * <ul>
 * <li>convert the input collection to a set to start with. This will remove duplicates
 * <li>generate a random number for each request for an element and used that to index into the collection
 * <li>remove an element from the collection once it is processed.
 * </ul>
 * </pre>
 * <p>
 *
 */
public class UniqueRandomElement {
	private static boolean DEBUG=false;
	private static final Logger logger=Logger.getLogger(UniqueRandomElement.class);
	private final String[] namesUniverse;
	private List<String> arraylist;
	private Set<String> listAsSet;
	/**
	 * 
	 */
	public UniqueRandomElement(String[]namesUniverse ) {
		Objects.requireNonNull(namesUniverse);
		this.namesUniverse = namesUniverse;
		init();
	}

	private void init() {
		/** first make sure there are no duplicates in the original list **/
		listAsSet = new HashSet<>(Arrays.asList(namesUniverse));
		arraylist = new ArrayList<>(listAsSet);
	}
	private void reinit() {
		/** first make sure there are no duplicates in the original list **/
		arraylist = new ArrayList<>(listAsSet);
	}

	/**
	 * @return a random name from our universe of names
	 * CAVEAT:
	 * Imposing the following requirement:
	 * The name cannot be used twice , only once until all names have been 
	 * used. THen the whole thing starts anew. 
	 */
	public String getRandomNames() {
		int randomNumber=0;
		String randomName=null;
		if (arraylist.size() == 1) {
			randomName = arraylist.get(0);
		} else {
		randomNumber = ThreadLocalRandom.current().nextInt(0,arraylist.size()-1);
		randomName = arraylist.get(randomNumber);	
		}
		arraylist.remove(randomNumber);
		if (DEBUG) {
			logger.info("SIZE OF LIST IS:" + arraylist.size());
		}
		if ( arraylist.isEmpty()){
			if (DEBUG) {
				logger.info("REINITIALIZING LIST:" + arraylist.size());
			}
			reinit();
		}
		return randomName;
	}
	
	public Set<String> getRandomNames(int i) {
		if ( i > arraylist.size()) {
			return null;
		}
		Set<String> set = new HashSet<>();
		IntStream.range(0, i).forEach(p -> {
			set.add(getRandomNames());
		});
		return set;
	}

}