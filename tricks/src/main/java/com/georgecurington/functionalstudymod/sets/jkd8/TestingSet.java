/**
 * 
 */
package com.georgecurington.functionalstudymod.sets.jkd8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author george
 *
 */
public class TestingSet {
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
	public TestingSet() {
		init();
		overloadingIssues();
	}

	private void overloadingIssues() {
		// Construct and populate a NavigableSet whose iterator returns its
		// elements in the reverse of the natural order
		NavigableSet<String> base = new TreeSet<String>(Collections.reverseOrder());
		Collections.addAll(base, "b","a","c");
		
		// call the two different constructors for TreeSet, supplying the
		// set just constructed, but with different static types:
		
		NavigableSet<String> sortedSet1 = new TreeSet<String>((Set<String>)base);
		NavigableSet<String> sortedSet2 = new TreeSet<String>(base);
		
		// and the two sets havee different iteration orders:
		List<String> forward = new ArrayList<>();
		forward.addAll(sortedSet1);
		
		List<String> backward = new ArrayList<>();
		backward.addAll(sortedSet2);
		
		p("foward=" + forward);
		p("backward=" + backward);
		
		Collections.reverse(forward);
		p("Collections.reverse(forward)=" + forward);
		
	}

	private void usingNavigableSet_2() {
		NavigableSet<String> stringSet = new TreeSet<String>();
		Collections.addAll(stringSet, "abc", "cde" , "x-ray", "zed");
		String last = stringSet.floor("x-ray");
		p("stringSet:"+stringSet);
		p("stringSet.floor(\"x-ray\")=" + stringSet.floor("x-ray"));
		
		String secondToLast = 
				last == null ? null : stringSet.lower(last);
		
		String thirdToLast = 
				secondToLast == null ? null : stringSet.lower(secondToLast);
		p("thirdToLast=" + thirdToLast);
		assert thirdToLast.equals("abc");
		
		NavigableSet<String> headSet = stringSet.headSet(last, true);
		NavigableSet<String> reverseHeadSet = headSet.descendingSet();
		p("stringSet.headSet(last, true)=" + stringSet.headSet(last, true));
		p("headSet.descendingSet()=" + reverseHeadSet);
		
	}

	private void usingNavigableSet() {
		p("priorityTasks=" + priorityTasks);
		PriorityTask nextTask = priorityTasks.pollFirst();
		p("priorityTasks.pollFirst()=" + nextTask);
		p("priorityTasks=" + priorityTasks);
		
		PriorityTask firstMediumPriorityTask = 
				new PriorityTask(new EmptyTask(), Priority.MEDIUM);
		
		p("firstMediumPriorityTask=" + firstMediumPriorityTask);
		PriorityTask mikePhoneMedium = new 
				PriorityTask(mikePhone,Priority.MEDIUM);
		
		p("mikePhoneMedium=" + mikePhoneMedium);
		NavigableSet closedInterval = priorityTasks.subSet(
				firstMediumPriorityTask, true, mikePhoneMedium,true);
		p("closedInterval=" + closedInterval);
		assert(closedInterval.toString()).equals(
				"[code db: MEDIUM, phone Mike: MEDIUM]");		
		
	}

	private void usingPriorityTask() {

		
		PriorityTask firstLowPriorityTask = 
				new PriorityTask(new EmptyTask(), Priority.LOW);
		SortedSet<PriorityTask> highAndMediumPriorityTasks = 
				priorityTasks.headSet(firstLowPriorityTask);
		
		p("priorityTasks:" + priorityTasks.toString());

		p("highAndMediumPriorityTasks:" + highAndMediumPriorityTasks.toString());
		assert highAndMediumPriorityTasks.toString().equals
		(  "[phone Paul: HIGH, code db: MEDIUM, phone Mike: MEDIUM]");
		
		PriorityTask firstMediumPriorityTask = 
				new PriorityTask(new EmptyTask(), Priority.MEDIUM);
		SortedSet<PriorityTask> mediumPriorityTasks = 
				priorityTasks.subSet
				(firstMediumPriorityTask, firstLowPriorityTask);
		p("mediumPriorityTasks.toString():"+mediumPriorityTasks.toString());
		assert mediumPriorityTasks.toString().equals(
				"[code db: MEDIUM, phone Mike: MEDIUM]");
		
		
		
		
		
		PriorityTask logicCodeMedium = 
				new PriorityTask(logicCode, Priority.MEDIUM);
		priorityTasks.add(logicCodeMedium);
		p("priorityTasks:" + priorityTasks.toString());
		
		assert mediumPriorityTasks.toString().equals(
				"[code db: MEDIUM, code logic: MEDIUM, phone Mike: MEDIUM]");
		
		p("mediumPriorityTasks:" + mediumPriorityTasks.toString());

		p("now removing logicCodeMedium");
		
		mediumPriorityTasks.remove(logicCodeMedium);
		p("priorityTasks.toString():" + priorityTasks.toString());
		assert priorityTasks.toString().equals(
		"[phone Paul: HIGH, code db: MEDIUM, phone Mike: MEDIUM, code gui: LOW]");
				
		
	}

	private void usingNaviagableset() {
		NavigableSet<PriorityTask> priorityTasks = 
				new TreeSet<PriorityTask>();
		
		priorityTasks.add(new PriorityTask(mikePhone,Priority.MEDIUM));
		priorityTasks.add(new PriorityTask(paulPhone, Priority.HIGH));
		priorityTasks.add(new PriorityTask(databaseCode, Priority.MEDIUM));
		priorityTasks.add(new PriorityTask(interfaceCode, Priority.LOW));
		
		priorityTasks.forEach(System.out::print);
		System.out.println();
		p(priorityTasks.toString());
	}

	private void usingTreeSet() {
		p("mondayTasks=" + mondayTasks);
		p("tuesdayTasks=" + tuesdayTasks);
		Set<Task> naturallyOrderedTasks = new TreeSet<Task>(mondayTasks);
		p("new TreeSet<Task>(mondayTasks)=" + naturallyOrderedTasks);
		naturallyOrderedTasks.addAll(tuesdayTasks);
		p("naturallyOrderedTasks.addAll(tuesdayTasks)=" + naturallyOrderedTasks);	
	}

	private void usingNulls() {
		Set<Integer> s = new HashSet<>();
		s.add(null);
		p(s.toString());
		s = new LinkedHashSet<>();
		s.add(null);
		p(s.toString());
		Set<Color> c = EnumSet.of(Color.RED,Color.BLUE,Color.GREEN);
		try {
		c.add(null);
		p(c.toString());
		} catch ( Exception e){ p("cannot add nulls to EnumSet");}
		
		Set<Integer> tree = new TreeSet<>();
		try {
			tree.add(null);
			p(tree.toString());
		} catch ( Exception e ) { p("cannot add nulls to TreeSet");}
		Set<Integer> cowas = new CopyOnWriteArraySet<>();
		cowas.add(null);
		
		Set<Integer> csls = new ConcurrentSkipListSet<>();
		try {
		csls.add(null);
		p(csls.toString());
		} catch ( Exception e ) { p("cannot add nulls to ConcurrentSkipListSet");}
		
	}

	private void enumset_() {
		EnumSet<Color> yellow = EnumSet.of(Color.RED,Color.GREEN);
		drawLine(yellow);
		
		EnumSet<Color> white = EnumSet.of(Color.RED,Color.GREEN,Color.BLUE);
		drawLine(white);
		
		EnumSet<Color> pink = EnumSet.of(Color.RED,Color.BLUE);
		drawLine(pink);
	}

	private void drawLine(Set<Color> colors) {
		p("Requested Colors to draw lines : " + colors);
		colors.forEach(p -> {
			p("drawing ne in color : " + p);
		});
		
	}

	/**
	 * <pre>
	 * This is a thread safe set implementation that is backed
	 * by an readonly array. When ever it is modified, a copy is 
	 * made. Iterators are snapshot iterators and are only capable 
	 * of read operations. Use case is Subject Observer.
	 * </pre>
	 * 
	 * @see com.georgecurington.functionalstudymod.design.subjectobserver.Simulator
	 * 
	 */
	private void copyonwritearrayset_1() {
		Set<Integer> s = new CopyOnWriteArraySet<>();

	}

	private void linkedhashset_1() {
		/** set the initial compacity to 8 **/

		Set<Character> s2 = new LinkedHashSet<>(8);
		Set<Character> s3 = new HashSet<>(Arrays.asList('a', 'b', 'j', 'q', 'z', '1'));
		s3.stream().forEach(System.out::println);
		Collections.addAll(s2, 'a', 'b', 'j', 'q', 'z', '1');
		s2.stream().forEach(System.out::println);

	}

	private void section_1() {
		Set<Task> dups = new TreeSet<>();
		dups.addAll(mondayTasks);
		dups.addAll(mondayTasks);

		Set<Task> phoneAndMondayTasks = new TreeSet<Task>(mondayTasks);
		phoneAndMondayTasks.addAll(phoneTasks);
		p("mondayTasks=" + mondayTasks.toString());
		p("phoneTasks=" + phoneTasks.toString());
		p("phoneAndMondayTasks=" + phoneAndMondayTasks.toString());
		p("dups=" + dups);
		dups.add(null);

	}

	private void init() {
		Collections.addAll(phoneTasks, mikePhone, paulPhone);
		Collections.addAll(codingTasks, databaseCode, interfaceCode, logicCode);
		Collections.addAll(mondayTasks, logicCode, mikePhone);
		Collections.addAll(tuesdayTasks, databaseCode, interfaceCode, paulPhone);
		

		
		priorityTasks.add(new PriorityTask(mikePhone,Priority.MEDIUM));
		priorityTasks.add(new PriorityTask(paulPhone, Priority.HIGH));
		priorityTasks.add(new PriorityTask(databaseCode, Priority.MEDIUM));
		priorityTasks.add(new PriorityTask(interfaceCode, Priority.LOW));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestingSet();

	}

	private static void p(String s) {
		System.out.println(s);
	}

}
