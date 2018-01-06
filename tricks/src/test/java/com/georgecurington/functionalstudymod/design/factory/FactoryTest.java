/**
 * 
 */
package com.georgecurington.functionalstudymod.design.factory;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author george
 *
 */
public class FactoryTest {

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
	public void test() {
		Computer pc = ComputerFactory.getComputer("pc", "2 GB", "500 GB", "2.4 GHz");
		Computer server = ComputerFactory.getComputer("server", "16 GB", "1 TB", "2.9 GHz");
		System.out.println("Factory PC Config::" + pc);
		System.out.println("Factory Server Config::" + server);
	}

}
