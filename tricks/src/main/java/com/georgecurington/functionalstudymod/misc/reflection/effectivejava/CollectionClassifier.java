/**
 * 
 */
package com.georgecurington.functionalstudymod.misc.reflection.effectivejava;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * Item 41: Use overloading judiciously
 * This interesting exampe from Effective Java, 2nd Edition
 * illustrates one issue that can cause confusion when it comes to 
 * overloading functions at compile time. THe decision as to which
 * class to use is compiled into bytecode during the compilation
 * phase , not at runtime, as it is with polymorphic semantics.
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 * 
 *
 */
public class CollectionClassifier {
	
	public static String classify(Set<?> s) {

		return "Set";
	}
	
	public static String classify(List<?> lst) {
		return "List";
	}
	
	public static String classify(Collection<?> c){
		System.out.println("+" + c.getClass());

		return "Unknown Collection";
	}

	/**
	 * 
	 */
	public CollectionClassifier() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Collection<?>[] collections = {
				
				new HashSet<String>(),
				new ArrayList<BigInteger>(),
				new HashMap<String,String>().values()
		};
		
		for (Collection<?> c : collections) {
			System.out.println("class=" + c.getClass());
			System.out.println(":" + classify(c));
		}

	}

}
