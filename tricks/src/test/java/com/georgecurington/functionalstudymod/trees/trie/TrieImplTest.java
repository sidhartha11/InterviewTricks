package com.georgecurington.functionalstudymod.trees.trie;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

public class TrieImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	public void testTrieImpl() {
		Trie<Character> trie  = new TrieImpl<>();
		assertNotNull("cannot construct trie", trie);
	}

	@Ignore
	public void testInsert() {
		Trie<Character> trie  = new TrieImpl<>();
		assertNotNull("cannot construct trie", trie);
		List <Character> list = Arrays.asList('a','b','c');
		trie.insert(list);
		Utility.p("trie=\n" + trie);
		
		list = Arrays.asList('a','b','g','l');
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		list = Arrays.asList('c','d','f');
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		list = Arrays.asList('a','b','c','d');
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		list = Arrays.asList('l','m','n');
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
	}

	@Test
	public void testSearchPrefix() {
		Trie<Character> trie  = new TrieImpl<>();
		assertNotNull("cannot construct trie", trie);
		List <Character> list = Arrays.asList('a','b','c');
		trie.insert(list);
		Utility.p("trie=\n" + trie);
		
		list = Arrays.asList('a','b','g','l');
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		list = Arrays.asList('c','d','f');
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		list = Arrays.asList('a','b','c','d');
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		list = Arrays.asList('l','m','n');
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		boolean found = false;
		list = Arrays.asList('l','m');
		System.out.println("prefix search for " + list );
		found = trie.searchPrefix(list);
		System.out.println(found);
		
		found = false;
		list = Arrays.asList('c','d');
		System.out.println("prefix search for " + list );
		found = trie.searchPrefix(list);
		System.out.println(found);
		
		found = false;
		list = Arrays.asList('b','c');
		System.out.println("prefix search for " + list );
		found = trie.searchPrefix(list);
		System.out.println(found);
		
		found = false;
		list = Arrays.asList('a','b','c','d');
		System.out.println("prefix search for " + list );
		found = trie.searchPrefix(list);
		System.out.println(found);
		
	}

	@Test
	public void testSearchWord() {
		Trie<Character> trie  = new TrieImpl<>();
		assertNotNull("cannot construct trie", trie);
		List <Character> list = Arrays.asList('a','b','c');
		trie.insert(list);
		Utility.p("trie=\n" + trie);
		
		list = Arrays.asList('a','b','g','l');
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		list = Arrays.asList('c','d','f');
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		list = Arrays.asList('a','b','c','d');
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		list = Arrays.asList('l','m','n');
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		boolean found = false;
		list = Arrays.asList('l','m');
		System.out.println("word search for " + list );
		found = trie.searchWord(list);
		System.out.println(found);
		
		found = false;
		list = Arrays.asList('c','d');
		System.out.println("word search for " + list );
		found = trie.searchWord(list);
		System.out.println(found);
		
		found = false;
		list = Arrays.asList('b','c');
		System.out.println("word search for " + list );
		found = trie.searchWord(list);
		System.out.println(found);
		
		found = false;
		list = Arrays.asList('a','b','c','d','e');
		System.out.println("word search for " + list );
		found = trie.searchWord(list);
		System.out.println(found);
	}

	@Ignore
	public void testGetRoot() {
		fail("Not yet implemented");
	}
	
	@Ignore
	public void testCreateCharacterList(){
		List <Character> clist = Arrays.asList('a','b','c');
		clist.forEach(System.out::println);
	}
	
	@Test
	public void testTraversalTechniques(){
		Trie<Character> trie  = new TrieImpl<>();
		assertNotNull("cannot construct trie", trie);
		List <Character> list = Arrays.asList('a','b','c');
		System.out.println(list);
		trie.insert(list);		
		list = Arrays.asList('a','b','g','l');
		System.out.println(list);
		trie.insert(list);		
		list = Arrays.asList('c','d','f');
		System.out.println(list);
		trie.insert(list);
		list = Arrays.asList('a','b','c','d');
		System.out.println(list);
		trie.insert(list);	
		list = Arrays.asList('l','m','n');
		System.out.println(list);
		trie.insert(list);
//		System.out.println(trie);
		Queue<TrieNode<Character>> q = new LinkedBlockingQueue<>();
		AtomicInteger ai = new AtomicInteger(0);
		q.offer(trie.getRoot());
		Utility.p("root=:\n" + trie.getRoot().toString());
		while( q.peek() != null ){
			TrieNode<Character> r = q.poll();
			r.getChildren().values().forEach(p -> {
				Utility.p((ai.incrementAndGet()) + "  :  \n" + p.toString());
				q.offer(p);
				
			});
			
		}
	}

}
