package com.georgecurington.functionalstudymod.generics.gac;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class Chapter2Test {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	public void test_1() {
		List<Number> nums = new ArrayList<>();
		nums.add(2);
		nums.add(3.14);
		assertEquals(nums.toString(),"[2, 3.14]");
	}
	
	@Ignore
	public void test_2() {
		List<Integer> ints = new ArrayList<>();
		ints.add(1);
		ints.add(2);
		/**
		 * The line below will not compile becasue then we could put a float
		 * into an array of Integers 
		 */
//		List<Number> nums = ints;
		List<Number> nums = new ArrayList<>();
		nums.add(3.14);
//		assertEquals(ints.toString(), "[1, 2, 3.14]");
		assertEquals(nums.toString(), "[3.14]");
	}
	
	@Ignore
	public void test_3() {
		List<Number> nums = new ArrayList<>();
		nums.add(2.78);
		nums.add(3.14);
		/**
		 * The line below will not compile 
		 */
//		List<Integer> ints = nums;
		List<Integer> ints = new ArrayList<>();
		ints.add(2);
//		assertEquals(ints.toString(),"[2.78, 3.14]" );
		assertEquals(ints.toString(),"[2]" );
	}
	
	@Ignore
	public void test_4() {
		
		List<Number> nums = new ArrayList<>();
		List<Integer> ints = Arrays.asList(1,2);
		List<Double> dbls = Arrays.asList(2.78,3.14);
		nums.addAll(ints);
		nums.addAll(dbls);
		assertEquals(nums.toString(), "[1, 2, 2.78, 3.14]");
		
	}
	
	@Ignore
	public void test_5() {
		List<Integer> ints = new ArrayList<>();
		ints.add(1);
		ints.add(2);
		List<? extends Number> nums = ints;
		/**
		 * Note the line below now creates a compile time error 
		 * because it would , once again allow you to add a double to 
		 * and Integer list which must not be allowed 
		 */
		// nums.add(3.14); 
		// assertEquals(ints.toString(), "[1, 2, 3.14]");
		assertEquals(ints.toString(), "[1, 2]");
	}
	
	@Test
	public void test_6() {
		List<Object> objs = Arrays.<Object>asList(2, 2.14 , "four");
		List<Integer> ints = Arrays.asList(5,6);
		Utils.copy(objs, ints);
		Utils.<Object>copy(objs,ints);
		Utils.<Number>copy(objs,ints);
		Utils.<Integer>copy(objs,ints);
		objs.stream().forEach(System.out::println);
	}

}
