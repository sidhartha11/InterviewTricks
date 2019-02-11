/**
 * 
 */
package com.georgecurington.j8ia.chapter11;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static com.georgecurington.j8ia.chapter11.Java8utils.*;


/**
 * @author Owner
 *
 */
public class TestShop {

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

	@Test
	public void testShip() {
		ShopperApiIntf shop = 
				new Shop("BestShop");
		String d = shop.getPrice("product");
		peep("d = " + d);
		
	}

}
