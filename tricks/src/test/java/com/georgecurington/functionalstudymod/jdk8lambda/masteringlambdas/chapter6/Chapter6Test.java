package com.georgecurington.functionalstudymod.jdk8lambda.masteringlambdas.chapter6;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

public class Chapter6Test {
	List<String> stringList = Arrays.asList("a","b","c","d");
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	public void testSection6_3() {
		Stream<Integer> unorderedIntList = Stream.of(8,3,5,6,7,4) // ORDERED, SIZED
		.filter(i -> i % 2 == 0) // ORDERED
		.sorted() // ORDERED, SORTED
		.distinct() // DISTINCT, ORDERED, SORTED
		.map(i -> i + 1) // ORDERED
		.unordered(); // none 
		
		unorderedIntList.forEach(System.out::println);
	}
	
	@Ignore
	public void testSection6_4(){
		String joined =
				stringList.parallelStream()
				.map(String::toUpperCase)
				.collect(Collectors.joining());
		Utility.p(String.format("list=%s,joined=%s", stringList,joined));
	}
	
	/**
	 * using unsorted and distinct in the example below can
	 * improve performance on large datasets by more than 50%
	 * 
	 */
	@Ignore
	public void testSection6_4_2(){
		long distinctCount = stringList.parallelStream()
				.unordered()
				.distinct()
				.count();
	}
	
	@Test
	public void testSection6_4_3(){
		AtomicInteger counter = new AtomicInteger(1);
		IntStream.rangeClosed(1,5)
//		.map( i -> i + ":" + counter.getAndAdd(1) + " ")
		/**
		 * bogus ... java makes you convert the i to an Object ?
		 * inorder to map it to a string .. ehhhh
		 */
		.mapToObj( i -> i + ":" + counter.getAndAdd(1) + " ")

		.forEachOrdered(System.out::println);
	}
	
	@Test
	public void testSection6_4_4(){
		AtomicInteger counter = new AtomicInteger(1);
		IntStream.rangeClosed(1,100).parallel()
		.mapToObj(i -> i + ":" + counter.getAndAdd(1) + " ")
		.forEachOrdered(System.out::println);
	}

}
