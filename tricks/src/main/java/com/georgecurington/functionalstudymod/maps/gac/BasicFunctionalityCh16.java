/**
 * 
 */
package com.georgecurington.functionalstudymod.maps.gac;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.TreeSet;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;
import com.georgecurington.functionalstudymod.sets.jkd8.CodingTask;
import com.georgecurington.functionalstudymod.sets.jkd8.PhoneTask;
import com.georgecurington.functionalstudymod.sets.jkd8.Priority;
import com.georgecurington.functionalstudymod.sets.jkd8.PriorityTask;
import com.georgecurington.functionalstudymod.sets.jkd8.Task;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 30, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class BasicFunctionalityCh16 {
	Map <Integer,Integer> m = new HashMap<>();
	Map <Integer,Integer> n = new HashMap<>();
	/** example initializing a Map in line ( pre Java.9) **/
	Map<Integer, Integer> exampleHashMap = new HashMap<Integer,Integer>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{	
		put(1,1);
		put(2,2);
		put(3,3);
		}
	};
	
	private NavigableSet<PriorityTask> priorityTasks = 
			new TreeSet<PriorityTask>();
	private enum Color {
		RED(255, 0, 0), GREEN(0, 255, 0), BLUE(0, 0, 255);
		private int r;
		private int g;
		private int b;

		private Color(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}

		public int getR() {
			return r;
		}

		public int getG() {
			return g;
		}

		public int getB() {
			return b;
		}
	}

	// Read more:http:// javarevisited.blogspot.com/2014/03/how-to-use-enumset-in-java-with-example.html#ixzz53vg0zjy2

	PhoneTask mikePhone = new PhoneTask("Mike", "987 6543");
	PhoneTask paulPhone = new PhoneTask("Paul", "123 4567");
	CodingTask databaseCode = new CodingTask("db");
	CodingTask interfaceCode = new CodingTask("gui");
	CodingTask logicCode = new CodingTask("logic");

	Collection<PhoneTask> phoneTasks = new ArrayList<PhoneTask>();
	Collection<CodingTask> codingTasks = new ArrayList<CodingTask>();
	Collection<Task> mondayTasks = new ArrayList<Task>();
	Collection<Task> tuesdayTasks = new ArrayList<Task>();

	
	/**
	 * 
	 */
	public BasicFunctionalityCh16() {
		init();
//		addingAssociations();
//		removingAssociations();
//		queryingContentsOfAMap();
//		viewsOfMaps();
		usingMethodsOfMap();
		
	}

	private void usingMethodsOfMap() {
		/**
		 * EnumMap
         * public EnumMap(Class<K> keyType)
         * Creates an empty enum map with the specified key type.
         * Parameters:keyType - the class object of the key type for this enum map
         * Throws:NullPointerException - if keyType is null
		 */
		Map<Priority, ArrayDeque<Task>> taskMap = 
				new EnumMap<Priority,ArrayDeque<Task>>	(Priority.class);
		for ( Priority p : Priority.values()){
			taskMap.put(p,new ArrayDeque<Task>());
		}
		
		// populate the lists , for example 
		taskMap.get(Priority.MEDIUM).add(mikePhone);
		taskMap.get(Priority.HIGH).add(databaseCode);
		p("taskMap=" + taskMap);
		
		// now lets get to one of the task queues -- say , the one with
		// the highest priority
		Queue<Task> highPriorityTaskList = taskMap.get(Priority.HIGH);
	
		Client acme = new Client("Acme Corp");
		Map<Task,Client> billingMap = new HashMap<>();
		billingMap.put(interfaceCode, acme);
		
		Client client = billingMap.get(interfaceCode);	
		if ( client != null ){
			client.bill(interfaceCode);
		}
		
		/** now clean up after all work is done **/
		Collection<Client> clients = billingMap.values();
		for (Iterator<Client> iter = clients.iterator();iter.hasNext();){
			if ( iter.next().equals(acme)){
				p("removing " + acme.nm);
				iter.remove();
			}
		}
	
	}

	class Client {
		private final String nm;
		public Client(String nm){
			this.nm = nm;
		}
		public void bill(CodingTask interfaceCode) {
			p("sending invoice to " + interfaceCode.toString());
			
		}
	}
	private void viewsOfMaps() {
		/**
		 * Note: the documentation does not say that a concurrent
		 * modification is thrown if the underlying Map is modified
		 * while interating thru the view. And note that it does not
		 * support add and addAll.
		 * 
		 * Further: Note that entrySet() returns a cached value of the
		 * Set view representing the Map.Entry<K,V> values.
		 * See:
		 * 
		 *
         * public Set<Map.Entry<K,V>> entrySet() {
         *     Set<Map.Entry<K,V>> es;
         *     return (es = entrySet) == null ? (entrySet = new EntrySet()) : es;
         * }
		 * And EntrySet is a final class located in HashMap.java
		 * final class EntrySet extends AbstractSet<Map.Entry<K,V>> {
		 * 
		 * Map.Entry is an interface inside Map interface.
		 * Set<Map.Entry<K, V>> entrySet()  
		 * return a Set view of the associations
		 * * Returns a {@link Set} view of the mappings contained in this map.
         * The set is backed by the map, so changes to the map are
         * reflected in the set, and vice-versa.  If the map is modified
         * while an iteration over the set is in progress (except through
         * the iterator's own <tt>remove</tt> operation, or through the
         * <tt>setValue</tt> operation on a map entry returned by the
         * iterator) the results of the iteration are undefined.  The set
         * supports element removal, which removes the corresponding
         * mapping from the map, via the <tt>Iterator.remove</tt>,
         * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
         * <tt>clear</tt> operations.  It does not support the
         * <tt>add</tt> or <tt>addAll</tt> operations.
		 * 
		 * Set<K> keySet()                  
		 * return a Set view of the keys
		 * 
		 * Collection<V> values()           
		 * return a Collection view of the values
		 * 
		 * keySet and values(), located in HashSet follows the same rules as 
		 * entrySet. 
		 */ 
		p("m.entrySet()=" + m.entrySet());
		p("m.keySet()=" + m.keySet());
		p("m.values()=" + m.values());
		@SuppressWarnings("unchecked")
		Map.Entry<Integer,Integer> me = new Map.Entry(){

			Integer key=1;
			Integer value=2;
			@Override
			public Object getKey() {
				// TODO Auto-generated method stub
				return this.key;
			}

			@Override
			public Object getValue() {
				// TODO Auto-generated method stub
				return this.value;
			}

			@Override
			public Object setValue(Object value) {
				Object temp = value;
				this.value = (Integer) value;
				return temp;
			}
			
			@Override
			public String toString(){
				return "anonomous [" + key + ", " + value + "]";
			}
			
		};
		p("new Map.Entry<Integer,Integer>()=" + me);
	}

	private void queryingContentsOfAMap() {
		/**
		 * NOTE the HashMap implementation may contain 1 null key
		 * and many null values
		 * V get(Object k) returns the value corresponding to  k , 
		 * or null if ke is not present as a key.
		 * 
		 * boolean containsKey(Object k) returns true is k is present
		 * as a key or false otherwise
		 * 
		 * boolean containsValue(Object v) returns true is v is present
		 * as a value in the map ( O(n) time in most implementations.
		 * 
		 * int size() returns the number of associations
		 * 
		 * boolean isEmpty() returns true if there are no associations.
		 * 
		 */
		p("m.get(20)=" + m.get(20));
		p("m.containsKey(20)=" + m.containsKey(20));
		p("m.containsKey(null)=" + m.containsKey(null));
		p(String.format("m.siz()=%d, n.size()=%d", m.size(),n.size()));
		p(String.format("m.isEmpty=%b", m.isEmpty()));
		
	}

	private void init() {
		m = new HashMap<>();
		n = new HashMap<>();
		m.put(10,20);
		m.put(20, 30);
		m.put(null, 50);
		m.put(30, null);
		n.put(30,30);
		n.put(40,40);
		p("m=" + m);
		p("n=" + n);
		Collections.addAll(phoneTasks, mikePhone, paulPhone);
		Collections.addAll(codingTasks, databaseCode, interfaceCode, logicCode);
		Collections.addAll(mondayTasks, logicCode, mikePhone);
		Collections.addAll(tuesdayTasks, databaseCode, interfaceCode, paulPhone);
		

		
		priorityTasks.add(new PriorityTask(mikePhone,Priority.MEDIUM));
		priorityTasks.add(new PriorityTask(paulPhone, Priority.HIGH));
		priorityTasks.add(new PriorityTask(databaseCode, Priority.MEDIUM));
		priorityTasks.add(new PriorityTask(interfaceCode, Priority.LOW));
		
	}

	private void removingAssociations() {
		/** The operations in this group are optional; calling 
		 * them on an  unmodifiable map will result in an 
		 * UnsupportedOperationException.
		 */
		m.clear();
		p("m.clear()=" + m);
		m.put(10,20);
		m.put(20, 30);
		p("m=" + m);
		p("m.remove(20)=" + m.remove(20) + ":" + m);
	}
	private void addingAssociations() {
		/**
		 * V put(K key, V value)
		 * Add or replace a key-value association
		 * return the old value ( may be null )
		 * if the key was present
		 */
		p("m.put(50,null)=" + m.put(50, null) + ":" + m);
		p("m.put(50,null)=" + m.put(50, null) + ":" + m);
		p("m.put(null,null)=" + m.put(null, null) + ":" + m);
		p("m.put(null,50)=" + m.put(null, 50) + ":" + m);
		
		p("exampleHashMap=" + exampleHashMap);
		p("exampleHashMap.put(1,4)=" + exampleHashMap.put(1,4));
		
		/** 
		 * void putAll(Map<? extends K, ? extends V> m)
		 * add each of the key-value associations in the 
		 * supplied map to the receiver
		 */
		Map <Integer,Integer> m = new HashMap<>();
		Map <Integer,Integer> n = new HashMap<>();
		m.put(10,20);
		m.put(20, 30);
		n.put(30,30);
		n.put(40,40);
		p("m=" + m);
		p("n=" + n);
		m.putAll(n);
		p("m.putAll(n)=" + m);
		
		
		
		
		
		
	}

	private void doGenericMethods() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BasicFunctionalityCh16 basic = new BasicFunctionalityCh16();
	}

	static void p(String s){
		Utility.p(s);
	}
}
