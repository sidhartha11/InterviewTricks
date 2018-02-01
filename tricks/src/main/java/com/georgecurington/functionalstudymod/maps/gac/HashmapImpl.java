/**
 * 
 */
package com.georgecurington.functionalstudymod.maps.gac;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * <pre>
 * 
 * (from java source)
 * This class goes over some of the fundamentals of the Map Implementation
 * of HashMap:
 * Hash table based implementation of the <tt>Map</tt> interface.  This
 * implementation provides all of the optional map operations, and permits
 * <tt>null</tt> values and the <tt>null</tt> key.  (The <tt>HashMap</tt>
 * class is roughly equivalent to <tt>Hashtable</tt>, except that it is
 * unsynchronized and permits nulls.)  This class makes no guarantees as to
 * the order of the map; in particular, it does not guarantee that the order
 * will remain constant over time.
 *
 * <p>This implementation provides constant-time performance for the basic
 * operations (<tt>get</tt> and <tt>put</tt>), assuming the hash function
 * disperses the elements properly among the buckets.  Iteration over
 * collection views requires time proportional to the "capacity" of the
 * <tt>HashMap</tt> instance (the number of buckets) plus its size (the number
 * of key-value mappings).  Thus, it's very important not to set the initial
 * capacity too high (or the load factor too low) if iteration performance is
 * important.
 * 
*
 * <p>As a general rule, the default load factor (.75) offers a good
 * tradeoff between time and space costs.  Higher values decrease the
 * space overhead but increase the lookup cost (reflected in most of
 * the operations of the <tt>HashMap</tt> class, including
 * <tt>get</tt> and <tt>put</tt>).  The expected number of entries in
 * the map and its load factor should be taken into account when
 * setting its initial capacity, so as to minimize the number of
 * rehash operations.  If the initial capacity is greater than the
 * maximum number of entries divided by the load factor, no rehash
 * operations will ever occur.
 * 
 * <p>If many mappings are to be stored in a <tt>HashMap</tt>
 * instance, creating it with a sufficiently large capacity will allow
 * the mappings to be stored more efficiently than letting it perform
 * automatic rehashing as needed to grow the table.  Note that using
 * many keys with the same {@code hashCode()} is a sure way to slow
 * down performance of any hash table. To ameliorate impact, when keys
 * are {@link Comparable}, this class may use comparison order among
 * keys to help break ties.
 * 
 * As Per JavaSource:
 * public class HashMap<K,V> extends AbstractMap<K,V>
 *    implements Map<K,V>, Cloneable, Serializable {
 *    
 *    
 * Notes:
 * You can implement your own version of a map my extending the AbstractMap class.
 * This will create a non-mondifiable version of a map. To make it modifiable you will
 * need to implement the put method and also the remove method of the iterator thatis 
 * returned. 
 * 
 * 
 * The default initial capacity - MUST be a power of two.
 * static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
 * The maximum capacity, used if a higher value is implicitly specified
 * by either of the constructors with arguments.
 * MUST be a power of two <= 1<<30.
 * static final int MAXIMUM_CAPACITY = 1 << 30;
 * static final float DEFAULT_LOAD_FACTOR = 0.75f;
 * 
 * </pre>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 31, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class HashmapImpl {
	private final Map<Integer,Integer> testmap;
	private final Map<Integer,Integer> testmap2;
	private final Map<Integer,Integer> testlinkedmap;
	{
		testmap = new HashMap<>();
		testmap2 = new HashMap<>();
		testlinkedmap = new LinkedHashMap<>();
		IntStream.rangeClosed(1,10).forEach(p -> {
			testmap.put(p, p);
		});
		
		testlinkedmap.put(9, 5);
		testlinkedmap.put(4, 5);
		testlinkedmap.put(3, 5);
		testlinkedmap.put(1, 5);
		
		testmap2.put(9, 5);
		testmap2.put(4, 5);
		testmap2.put(3, 5);
		testmap2.put(1, 5);
		
		
	}
	/**
	 * 
	 */
	public HashmapImpl() {
		testLinkedHashMapConstruction();
	}

	/**
	 * <pre>
	 * An important note. There is a special type of ordering available for 
	 * the LinkedHashMap, access order.
     * Constructs an empty <tt>LinkedHashMap</tt> instance with the
     * specified initial capacity, load factor and ordering mode.
     *
     * @param  initialCapacity the initial capacity
     * @param  loadFactor      the load factor
     * @param  accessOrder     the ordering mode - <tt>true</tt> for
     *         access-order, <tt>false</tt> for insertion-order
     * @throws IllegalArgumentException if the initial capacity is negative
     *         or the load factor is nonpositive
     *    public LinkedHashMap(int initialCapacity,
     *                         float loadFactor,
     *                         boolean accessOrder) {
     *        super(initialCapacity, loadFactor);
     *        this.accessOrder = accessOrder;
     *    }
     *    
     *    And notice this method that is called by the implementation:
     *    
     *    void afterNodeInsertion(boolean evict) { // possibly remove eldest
     *        LinkedHashMap.Entry<K,V> first;
     *        if (evict && (first = head) != null && removeEldestEntry(first)) {
     *           K key = first.key;
     *            removeNode(hash(key), key, null, false, true);
     *        }
     *    }
     *    
     *    you can extend the class and override the removeEldestEntry method
     *    and try to implement some type of caching mechanism.
	 * 
	 */
	private void testLinkedHashMapConstruction() {
		Utility.p("testmap2=" + testmap2);
		Utility.p("testlinkedmap=" + testlinkedmap);
		Map<Integer,Integer> m2 = new LinkedHashMap<>(testlinkedmap);
		Utility.p("m2=" + m2);
		
	}

	private void testConstruction() {
		/** with empty constructor **/
		Map<Integer,Integer> m0 = new HashMap<>();
		/** with initial capacity **/
		Map<Integer,Integer> m1 = new HashMap<>(1_000_000);
		/** with initial capacity and load factor **/
		Map<Integer,Integer> m2= new HashMap<>(1_000_000, .65f);
		/** with map input **/
		Map<Integer,Integer> m3= new HashMap<>(testmap);
		Utility.p("m3=" + m3);	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new HashmapImpl();

	}

}
