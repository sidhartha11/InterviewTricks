package com.georgecurington.functionalstudymod.lists.heap;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

public class HeapImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	public void testGetLeftIndex() {

		Heap<String> heap = new HeapImpl<>();
		int i = heap.getLeftIndex(0);
		assertEquals("left index of 0 should be 1", 1 , i);
		
		i = heap.getLeftIndex(1);
		assertEquals("left index of 1 should be 3", 3 , i);
		
		i = heap.getLeftIndex(3);
		assertEquals("left index of 3 should be 7", 7 , i);
		
		i = heap.getLeftIndex(4);
		assertEquals("left index of 4 should be 9", 9 , i);
		
		i = heap.getLeftIndex(5);
		assertEquals("left index of 5 should be 11", 11 , i);
		
		i = heap.getLeftIndex(6);
		assertEquals("left index of 4 should be 13", 13 , i);
		
	}

	@Ignore
	public void testGetRightIndex() {
		Heap<String> heap = new HeapImpl<>();
		int i = heap.getRightIndex(0);
		assertEquals("right index of 0 should be 2" , 2 , i);
		i = heap.getRightIndex(2);
		assertEquals("right index of 2 should be 6" , 6 , i);
		
		i = heap.getRightIndex(3);
		assertEquals("right index of 3 should be 8" , 8 , i);
		
		i = heap.getRightIndex(4);
		assertEquals("right index of 4 should be 10" , 10 , i);
		
		i = heap.getRightIndex(5);
		assertEquals("right index of 5 should be 12" , 12 , i);
		
		i = heap.getRightIndex(6);
		assertEquals("right index of 6 should be 14" , 14 , i);
	}

	@Ignore
	public void testGetParent() {
		Heap<String> heap = new HeapImpl<>(); 
		int i = 0;
		try {
		i = heap.getParent(0);
		} catch (Throwable t){
			System.out.println("cannot get roots parent");
		}
		i = heap.getParent(1);
		assertEquals("parent of 1 should be 0", 0, i);
		
		i = heap.getParent(2);
		assertEquals("parent of 2 should be 0", 0, i);
		
		i = heap.getParent(3);
		assertEquals("parent of 3 should be 1", 1, i);
		
		i = heap.getParent(4);
		assertEquals("parent of 4 should be 1", 1, i);
		
		i = heap.getParent(5);
		assertEquals("parent of 5 should be 2", 2, i);
		
		i = heap.getParent(6);
		assertEquals("parent of 6 should be 2", 2, i);
	}
	
	@Ignore
	public void testNumberOfNodesOfCompleteBinaryTree() {
		Heap<String> heap = new HeapImpl<>();
		int nmbrNodes = 0 , height = 0;
		
		try {
		nmbrNodes = heap.getNmbrNodesBasedOnHeight(height);
		} catch (Throwable t){
			System.out.println("cannot get nmbrNodes of 0 height tree");
		}
		
		nmbrNodes = heap.getNmbrNodesBasedOnHeight(1);
		assertEquals("nmbr nodes of height 1 should be 3", 3 , nmbrNodes);
		
		nmbrNodes = heap.getNmbrNodesBasedOnHeight(1);
		assertEquals("nmbr nodes of height 1 should be 3", 3 , nmbrNodes);
		
		nmbrNodes = heap.getNmbrNodesBasedOnHeight(2);
		assertEquals("nmbr nodes of height 2 should be 7", 7 , nmbrNodes);
		
		nmbrNodes = heap.getNmbrNodesBasedOnHeight(3);
		assertEquals("nmbr nodes of height 3 should be 15",15 , nmbrNodes);
	
		nmbrNodes = heap.getNmbrNodesBasedOnHeight(4);
		assertEquals("nmbr nodes of height 4 should be 15",31 , nmbrNodes);
	}
	
	@Ignore
	public void testNumberOfNodesOfCompleteTernaryTree() {
		Heap<String> heap = new HeapImpl<>();
		int nmbrNodes = 0 , height = 0;
		
		IntStream.rangeClosed(1,4).forEach(p -> {
			Utility.p("height =" + p + ",#nodes=" +
		    heap.getNmbrNodesBasedOnHeightTernary(p));
		});

	}
	
	@Ignore
	public void testHeightOfCompleteBinaryTree() {
		Heap<String> heap = new HeapImpl<>();
		int nmbrNodes = 0 , height = 0;
		// Max nodes   3     7     15    31 
		IntStream.of(1,3,7,15,31).forEach(p -> {
			Utility.p("#nodes =" + p + ",height=" +
		    heap.getHeightOfCompleteBinaryTree(p));
		});

	}
	
	@Ignore
	public void testLeafStart() {
		Heap<String> heap = new HeapImpl<>();
		int nmbrNodes = 0 , height = 0;
		// Max nodes   3     7     15    31 
		IntStream.of(1,3,7,15,31).forEach(p -> {
			Utility.p("#nodes =" + p + ",leafStart=" +
		    heap.getLeafStart(p));
		});

	}
	
	@Ignore
	public void testMaxHeapify(){
		Heap<Integer> heap = new HeapImpl<>();
		List<Integer> list = 
				IntStream.of(1,14,10,8,7,9,3,2,4,6)
				.boxed().collect(Collectors.toList());
		
		heap.setSize(list.size());
		System.out.println(list);
		heap.maxHeapify(list,0);
		System.out.println(list);
		
	}
	
	@Ignore
	public void testBuildHeap(){
		Heap<Integer> heap = new HeapImpl<>();
		List<Integer> list = 
				IntStream.of(9,6,5,0,8,2,1,3)
				.boxed().collect(Collectors.toList());
		
		heap.setSize(list.size());
		int largestNonLeaf = Math.floorDiv(heap.getSize(), 2) - 1;
		for (int  i = largestNonLeaf; i > 0; i-- ){
			heap.maxHeapify(list, i);
		}
		
		
	}
	
	@Ignore
	public void testExtractMax(){
		Heap<Integer> heap = new HeapImpl<>();
		List<Integer> list = 
				IntStream.of(9,6,5,0,8,2,1,3)
				.boxed().collect(Collectors.toList());
		
		heap.setSize(list.size());
		int largestNonLeaf = Math.floorDiv(heap.getSize(), 2) - 1;
		for (int  i = largestNonLeaf; i > 0; i-- ){
			heap.maxHeapify(list, i);
		}
		int sz = heap.getSize();
		for ( int i = 0 ; i < sz ; i++ ) {
			int max = heap.extractMax(list);
			System.out.println("max = " + max);
		}
	}
	
	@Ignore
	public void testBuildHeapMethod(){
		Heap<Integer> heap = new HeapImpl<>();
		List<Integer> list = 
				IntStream.of(9,6,5,0,8,2,1,3)
				.boxed().collect(Collectors.toList());
		heap.buildHeap(list,true);
		
		int sz = heap.getSize();
		System.out.println("heap size is " + sz);
		int mod =0;
		for ( int i = 0 ; i < sz ; i++ ) {
			int max = heap.extractMax(list);
			System.out.println("max=" + max);
		}
	}
	
	@Ignore
	public void testExtractMaxHugeList(){
		Heap<Integer> heap = new HeapImpl<>();
		List<Integer> list = new ArrayList<>(100_000_000);
		IntStream.rangeClosed(1, 100_000_000).forEach( p -> {
			list.add(ThreadLocalRandom.current().nextInt());
		});
		System.out.println("list size is " + list.size());
		heap.setSize(list.size());
		int largestNonLeaf = Math.floorDiv(heap.getSize(), 2) - 1;
		
		System.out.println("largestNonLeaf = " + largestNonLeaf );
		for (int  i = largestNonLeaf; i > 0; i-- ){
			heap.maxHeapify(list, i);
		}
		int sz = heap.getSize();
		System.out.println("heap size is " + sz);
		int mod =0;
		for ( int i = 0 ; i < sz ; i++ ) {
			int max = heap.extractMax(list);
			if ( (mod++)  % 1_000_000 == 0 ) {
				System.out.println("current max = " + max );
			}
			
//			System.out.println("max = " + max);
		}
	}
	
	@Test
	public void testBuildHeapMethod2(){
		for (int i = 0; i < 10; i++) {
			List<Integer> list  = new ArrayList<>();
			int b = i;
			IntStream.rangeClosed(1, 10).forEach(p -> {
				list.add(ThreadLocalRandom.current().nextInt(0,10_000));
			});
			Utility.p("--------------------------------");
			list.stream().limit(10).forEach(System.out::println);
			Utility.p("--------------------------------");
			// Utility.p(list[i].toString());
			
			Heap<Integer> heap = new HeapImpl<>();
			Utility.p("calling buildHeap");
			heap.buildHeap(list, true);
			Integer r = heap.extractMax(list);
			Utility.p("..max=" + r);
			for (int x = 0; x < list.size()-1;x++){
				Integer r2 = heap.extractMax(list);
				Utility.p("..max=" + r2);
			}
		
		}
	}

}
