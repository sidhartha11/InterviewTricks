/**
 * 
 */
package com.georgecurington.functionalstudymod.design.singleton;

import com.georgecurington.functionalstudymod.runningtotal1.MiniInput;
import com.georgecurington.functionalstudymod.runningtotal1.MiniInputIntf;

/**
 * <pre>
 * <b>
 * Singleton Design Pattern
 * </b>
 * This is an example of using the Demand Holder Pattern. It is the most efficient
 * and scalable usage of singleton pattern in the JVM. This pattern is also 
 * thread safe. It also implements the "lazy loading" idiom.
 * </pre>
 * <pre>
 * <b>from wikipedia</b>
 * The implementation of the idiom relies on the initialization phase of execution 
 * within the Java Virtual Machine (JVM) as specified by the Java Language Specification (JLS).[3] 
 * When the class Something is loaded by the JVM, the class goes through initialization. 
 * Since the class does not have any static variables to initialize, the initialization 
 * completes trivially. The static class definition LazyHolder within it is not initialized 
 * until the JVM determines that LazyHolder must be executed. The static class LazyHolder is 
 * only executed when the static method getInstance is invoked on the class Something, and the 
 * first time this happens the JVM will load and initialize the LazyHolder class. 
 * The initialization of the LazyHolder class results in static variable INSTANCE being 
 * initialized by executing the (private) constructor for the outer class Something. 
 * Since the class initialization phase is guaranteed by the JLS to be sequential, i.e., 
 * non-concurrent, no further synchronization is required in the static getInstance method 
 * during loading and initialization. And since the initialization phase writes the static 
 * variable INSTANCE in a sequential operation, all subsequent concurrent invocations of the 
 * getInstance will return the same correctly initialized INSTANCE without incurring any additional 
 * synchronization overhead. While the implementation is an efficient thread-safe "singleton" 
 * cache without synchronization overhead, and better performing than uncontended synchronization,
 * [4] the idiom can only be used when the construction of Something can be guaranteed to not fail. 
 * In most JVM implementations, if construction of Something fails, subsequent attempts 
 * to initialize it from the same class-loader will result in a NoClassDefFoundError failure.
 * </pre>
 * 
 *  
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 * @see https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom 
 *
 */
public class DemandHolderForPoisonPill {

	/**
	 * 
	 */
	private DemandHolderForPoisonPill() {
		// TODO Auto-generated constructor stub
	}
	private static class DemandHolder { 
		
		public static final String[] POISONSTRING = 
			{"-1","___", "380", "206.63","1514852274268"};
		public static final MiniInputIntf POISON = new MiniInput(POISONSTRING);
	}
	
	public static MiniInputIntf getPoison(){
		return DemandHolder.POISON;
	}
}
