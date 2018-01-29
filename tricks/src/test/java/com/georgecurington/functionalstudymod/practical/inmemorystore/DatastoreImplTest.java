package com.georgecurington.functionalstudymod.practical.inmemorystore;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

public class DatastoreImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDatastoreImpl() {
		Datastore ds = new DatastoreImpl();
		assertNotNull("cannot create datastore", ds);
	}

	@Test
	public void testCommand() {
		Datastore ds = new DatastoreImpl();
		assertNotNull("cannot create datastore", ds);
		boolean b = ds.command(Operation.add, "1", "george");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "2", "eorg");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "3", "mike");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "4", "mary");
		assertTrue("could not insert contact", b);
	}

	@Test
	public void testFind() {
		Datastore ds = new DatastoreImpl();
		assertNotNull("cannot create datastore", ds);
		boolean b = ds.command(Operation.add, "1", "george");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "2", "eorg");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "3", "mike");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "4", "mary");
		assertTrue("could not insert contact", b);
		List<Contacts> l = ds.find("mary");
		Utility.p("find results for mary:");
		l.forEach(System.out::println);
		
		
		l = ds.find("m");
		Utility.p("find results for m:");
		l.forEach(System.out::println);
		
	}

	@Test
	public void testAll() {
		Datastore ds = new DatastoreImpl();
		assertNotNull("cannot create datastore", ds);
		boolean b = ds.command(Operation.add, "1", "george");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "2", "eorg");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "3", "mike");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "4", "mary");
		assertTrue("could not insert contact", b);
		List<Contacts> l = ds.all();
		Utility.p("find results for all():");
		l.forEach(System.out::println);
	}
	
	@Test
	public void testCommandUpdate() {
		Datastore ds = new DatastoreImpl();
		assertNotNull("cannot create datastore", ds);
		boolean b = ds.command(Operation.add, "1", "george");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "2", "eorg");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "3", "mike");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "4", "mary");
		assertTrue("could not insert contact", b);
		List<Contacts> l = ds.all();
		Utility.p("find results for all():");
		l.forEach(System.out::println);
		
		b = ds.command(Operation.update, "4", "mary poppins");
		l = ds.all();
		Utility.p("find results for all():");
		l.forEach(System.out::println);
	}
	
	@Test
	public void testCommandDelete() {
		Datastore ds = new DatastoreImpl();
		assertNotNull("cannot create datastore", ds);
		boolean b = ds.command(Operation.add, "1", "george");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "2", "eorg");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "3", "mike");
		assertTrue("could not insert contact", b);
		b = ds.command(Operation.add, "4", "mary");
		assertTrue("could not insert contact", b);
		List<Contacts> l = ds.all();
		Utility.p("find results for all():");
		l.forEach(System.out::println);
		
		b = ds.command(Operation.delete, "4", "mary poppins");
		l = ds.all();
		Utility.p("find results for all():");
		l.forEach(System.out::println);
	}


}
