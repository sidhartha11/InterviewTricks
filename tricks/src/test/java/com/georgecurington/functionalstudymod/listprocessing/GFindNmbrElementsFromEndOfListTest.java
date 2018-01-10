/**
 * 
 */
package com.georgecurington.functionalstudymod.listprocessing;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author george
 *
 */
public class GFindNmbrElementsFromEndOfListTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.listprocessing.GFindNmbrElementsFromEndOfList#GFindNmbrElementsFromEndOfList(int)}.
	 */
	@Ignore
	public void testGFindNmbrElementsFromEndOfList() {
		GFindNmbrElementsFromEndOfListIntf<String> list = 
				new GFindNmbrElementsFromEndOfList<>(3);
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("1");
		list.add("2");
		list.add("4");
		list.add("5");
		list.add("6");
		List<String> l=list.getNelementsFromEndOfList();
		System.out.println(l);
				
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.listprocessing.GFindNmbrElementsFromEndOfList#add(java.lang.Comparable)}.
	 */
	@Test
	public void testAdd() {
		List<String> l = new LinkedList<String>();
		IntStream.rangeClosed(0,10).forEach(p -> {
			l.add(String.valueOf(p));
		});
		System.out.println(l);
		// printout the last n items in the list 
		l.stream().skip(l.size()-(4+1)).limit(4).forEach(System.out::println);
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.listprocessing.GFindNmbrElementsFromEndOfList#size()}.
	 */
	@Ignore
	public void testSize() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.listprocessing.GFindNmbrElementsFromEndOfList#getNelementsFromEndOfList()}.
	 */
	@Test
	public void testGetNelementsFromEndOfList() {
		fail("Not yet implemented");
	}

}
