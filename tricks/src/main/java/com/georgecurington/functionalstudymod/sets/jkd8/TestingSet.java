/**
 * 
 */
package com.georgecurington.functionalstudymod.sets.jkd8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author george
 *
 */
public class TestingSet {
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
		section_1();
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
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestingSet();

	}
	private static void p(String s){ System.out.println(s);}

}
