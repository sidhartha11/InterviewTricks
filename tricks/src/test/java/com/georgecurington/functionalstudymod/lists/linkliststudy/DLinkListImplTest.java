/**
 * 
 */
package com.georgecurington.functionalstudymod.lists.linkliststudy;

import static org.junit.Assert.*;

import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.georgecurington.functionalstudymod.concurrent.threads.Utility;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 24, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class DLinkListImplTest {

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
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.linkliststudy.DLinkListImpl#DLinkListImpl()}.
	 */
	@Ignore
	public void testDLinkListImpl() {
		DLinkList dlinkList = new DLinkListImpl();
		assertNotNull("cannot create DLinkedList",dlinkList);
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.linkliststudy.DLinkListImpl#insertAtHead(java.lang.Comparable)}.
	 */
	@Ignore
	public void testInsertAtHead() {
		DLinkList<String> dlinkList = new DLinkListImpl<>();
		boolean b = false;
		b = dlinkList.insertAtHead("A");
		b = dlinkList.insertAtHead("B");
		b = dlinkList.insertAtHead("B");
		b = dlinkList.insertAtHead("A");
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.linkliststudy.DLinkListImpl#insertAtEnd(java.lang.Comparable)}.
	 */
	@Ignore
	public void testInsertAtEnd() {
		DLinkList<String> dlinkList = new DLinkListImpl<>();
		boolean b=false;
		b = dlinkList.insertAtEnd("A");
		b = dlinkList.insertAtEnd("B");
		b = dlinkList.insertAtEnd("B");
		b = dlinkList.insertAtEnd("A");
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
	}

	/**
	 * Test method for {@link com.georgecurington.functionalstudymod.lists.linkliststudy.DLinkListImpl#insertAtMiddle(java.lang.Comparable)}.
	 */
	@Ignore
	public void testInsertAtMiddle() {
		DLinkList<String> dlinkList = new DLinkListImpl<>();
		boolean b=false;
		b = dlinkList.insertAtMiddle("Z","C");
		b = dlinkList.insertAtMiddle("Z","D");
		b = dlinkList.insertAtMiddle("Z","E");
		b = dlinkList.insertAtMiddle("Z","F");
		b = dlinkList.insertAtMiddle("Z","G");
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
	}
	
	@Ignore
	public void testInsertBeforeMiddle() {
		DLinkList<String> dlinkList = new DLinkListImpl<>();
		boolean b=false;
		b = dlinkList.insertBeforeMiddle("Z","A");
		b = dlinkList.insertBeforeMiddle("A","D");
		b = dlinkList.insertBeforeMiddle("A","E");
		b = dlinkList.insertBeforeMiddle("A","F");
		b = dlinkList.insertBeforeMiddle("A","G");
		b = dlinkList.insertBeforeMiddle("A","E");
		b = dlinkList.insertBeforeMiddle("A","E");
		b = dlinkList.insertBeforeMiddle("A","F");
		b = dlinkList.insertBeforeMiddle("A","Z");
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
	}
	
	@Ignore 
	public void testInsertInOrder() {
		DLinkList<String> dlinkList = new DLinkListImpl<>();
		boolean b=false;
		b = dlinkList.insertInOrder("A");
		b = dlinkList.insertInOrder("X");
		b = dlinkList.insertInOrder("Y");
		b = dlinkList.insertInOrder("1");
		b = dlinkList.insertInOrder("B");
		b = dlinkList.insertInOrder("A");
		b = dlinkList.insertInOrder("X");
		b = dlinkList.insertInOrder("Y");
		b = dlinkList.insertInOrder("1");
		b = dlinkList.insertInOrder("B");
		
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
	}
	
	@Ignore
	public void testDeleteHeadNode() {
		DLinkList<String> dlinkList = new DLinkListImpl<>();
		boolean b=false;
		b = dlinkList.insertInOrder("A");
		b = dlinkList.insertInOrder("X");
		b = dlinkList.insertInOrder("Y");
		b = dlinkList.insertInOrder("1");
		b = dlinkList.insertInOrder("B");
		b = dlinkList.insertInOrder("A");
		b = dlinkList.insertInOrder("X");
		b = dlinkList.insertInOrder("Y");
		b = dlinkList.insertInOrder("1");
		b = dlinkList.insertInOrder("B");
		
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
		Utility.p("deleting head node");
		b = dlinkList.deleteHeadNode();
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
		
		Utility.p("deleting head node");
		b = dlinkList.deleteHeadNode();
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
	}
	
	@Ignore
	public void testDeleteAllHeadNode() {
		DLinkList<String> dlinkList = new DLinkListImpl<>();
		boolean b=false;
		b = dlinkList.insertInOrder("A");
		b = dlinkList.insertInOrder("X");
		b = dlinkList.insertInOrder("Y");
		b = dlinkList.insertInOrder("1");
		b = dlinkList.insertInOrder("B");
		b = dlinkList.insertInOrder("A");
		b = dlinkList.insertInOrder("X");
		b = dlinkList.insertInOrder("Y");
		b = dlinkList.insertInOrder("1");
		b = dlinkList.insertInOrder("B");
		
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
		IntStream.rangeClosed(1, dlinkList.size()).forEach(p -> {
			dlinkList.deleteHeadNode();
		});
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
	}
	
	@Test
	public void testDeleteANode() {
		DLinkList<String> dlinkList = new DLinkListImpl<>();
		boolean b=false;
		b = dlinkList.insertInOrder("A");
		b = dlinkList.insertInOrder("X");
		b = dlinkList.insertInOrder("Y");
		b = dlinkList.insertInOrder("1");
		b = dlinkList.insertInOrder("B");
		b = dlinkList.insertInOrder("A");
		b = dlinkList.insertInOrder("X");
		b = dlinkList.insertInOrder("Y");
		b = dlinkList.insertInOrder("1");
		b = dlinkList.insertInOrder("B");
		
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
		
		dlinkList.deleteANode("1");
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
		
		dlinkList.deleteANode("B");
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
		
		dlinkList.deleteANode("X");
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
		
		dlinkList.deleteANode("X");
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
		dlinkList.deleteANode("X");
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
		
		dlinkList.deleteANode("Y");
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
		dlinkList.deleteANode("Y");
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
		dlinkList.deleteANode("Y");
		Utility.p("size=" + dlinkList.size());
		for (String ele : dlinkList ) {
			System.out.println(ele);
		}
	}

}
