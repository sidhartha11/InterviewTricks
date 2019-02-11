/**
 * 
 */
package com.georgecurington.imaging.art;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Owner
 *
 */
public class CanvasDescriptionTest {

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link com.georgecurington.imaging.art.CanvasDescription#CanvasDescription()}.
	 */
	@Ignore
	public void testCanvasDescription() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.imaging.art.CanvasDescription#getCanvas()}.
	 */
	@Ignore
	public void testGetCanvas() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.imaging.art.CanvasDescription#getRow(int)}.
	 */
	@Ignore
	public void testGetRow() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.imaging.art.CanvasDescription#getColumn(int, int)}.
	 */
	@Ignore
	public void testGetColumn() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.imaging.art.CanvasDescription#isNeighborCanyon(int, int)}.
	 */
	@Ignore
	public void testIsNeighborCanyon() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.georgecurington.imaging.art.CanvasDescription#createRow(int, int, int, int, int)}.
	 */
	@Test
	public void testCreateRow() {
		CanvasDescriptionInf cd =
				new CanvasDescription();
//		List<CellIntf> l = cd.createRow(0, 100, 100, 5, 10, 5, 10);
		IntStream.iterate(0, i -> i + 10).limit(100).forEach( p -> {
			List<CellIntf> l = cd.createRowEqual(p, 100, 100, 10);
			System.out.println(l);
			System.out.println("-----------------------------");
		});
		List<CellIntf> l = cd.createRowEqual(0, 100, 100, 10);
		
		Stream<Integer> numbersFromValues = Stream.of(1, 2, 3, 4);
		List<Integer> l2 = numbersFromValues.collect(Collectors.toList());
		int[] numbers = {1, 2, 3, 4};
		IntStream numbersFromArray = Arrays.stream(numbers);
		IntStream.iterate(0, i -> i + 100)
        .limit(1000 / 100)
        .forEach(i -> { /* the test */ });
		l.forEach( p -> {
			System.out.println(p);
		});
	}

}
