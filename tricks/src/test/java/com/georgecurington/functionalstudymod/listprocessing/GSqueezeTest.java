/**
 * 
 */
package com.georgecurington.functionalstudymod.listprocessing;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * <pre>
 * <p><b>INTERVIEW TRICKS</b></p>
 * </pre>
 * <p>
 * ================================================
 * </p>
 * <br>
 * 
 * <pre>
 * &#64;author George Curington
 * &#64;version 1.0.0
 * &#64;since Jan 20, 2018
 * &#64;see https://github.com/sidhartha11/InterviewTricks
 * &#64;see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * </pre>
 */
public class GSqueezeTest {

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
	 * Test method for
	 * {@link com.georgecurington.functionalstudymod.listprocessing.GSqueeze#GSqueeze(java.util.List)}.
	 */
	@Ignore
	public void testGSqueeze() {
		List<String> list = Arrays.asList("a", "a", "b", "3", "4", "4", "4");
		Collections.sort(list);
		System.out.println(list);
		assertEquals("list must be sorted", "[3, 4, 4, 4, a, a, b]", list.toString());
		GSqueezeIntf<String> gsqueeze = new GSqueeze<>(list);
		assertNotNull("ctr error", gsqueeze);
		List<String> sqz = gsqueeze.squeeze();
		assertEquals("not sqozen", "[3, 4, a, b]", sqz.toString());
		System.out.println(sqz.toString());
	}

	@Test
	public void testGSqueezeIter() {
		List<String> list = Arrays.asList("a", "a", "b", "3", "4", "4", "4");
		Collections.sort(list);
		System.out.println(list);
		assertEquals("list must be sorted", "[3, 4, 4, 4, a, a, b]", list.toString());
		GSqueezeIntf<String> gsqueeze = new GSqueeze<>(list);
		assertNotNull("ctr error", gsqueeze);
		List<String> sqz = gsqueeze.squeezeIterTemp();
		assertEquals("not sqozen", "[3, 4, a, b]", sqz.toString());
		System.out.println(sqz.toString());
	}

	/**
	 * Test method for
	 * {@link com.georgecurington.functionalstudymod.listprocessing.GSqueeze#squeeze()}.
	 */
	@Ignore
	public void testSqueeze() {
		fail("Not yet implemented");
	}

}
