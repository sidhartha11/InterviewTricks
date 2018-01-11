/**
 * 
 */
package com.georgecurington.functionalstudymod.sets.jkd8;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author george
 *
 */
public class GenericsAndCollectionsChap12 {

	/**
	 * 
	 */
	public GenericsAndCollectionsChap12() {
		copyingContents();
	}

	private void copyingContents() {
		Collection<Integer> col  = new HashSet<>();
		Collection<Integer> col2 = new HashSet<>();
		col.add(1);
		col.add(2);
		col.add(3);
		col2.add(4);
		col2.add(5);
		col2.add(6);
		Iterator<Integer> itr = col.iterator();
		while ( itr.hasNext()) {
			System.out.println(itr.next());			
		}
		Object[] o = col.toArray();
		p("col.toArray=" + Arrays.toString(o));
		Integer[] ia = new Integer[0];
		p("size of new Integer[0]:" + ia.length);
		Integer[] ia2 = col.toArray(ia);
		p("ia2=" + Arrays.toString(ia2));
		
	}

	private void queryingContents() {
		Collection<Integer> col  = new HashSet<>();
		Collection<Integer> col2 = new HashSet<>();
		col.add(1);
		col.add(2);
		col.add(3);
		col2.add(4);
		col2.add(5);
		col2.add(6);
		p("col=" + col);
		p("col2=" + col2);
		p("col.contains(4):" + col.contains(4));
		p("col.containsAll(col)=" + col.containsAll(col));
		p("col.isEmpty()=" + col.isEmpty());
		p("col.size()=" + col.size());
		
	}

	private void removingOjbects() {
		Collection<Integer> col  = new HashSet<>();
		Collection<Integer> col2 = new HashSet<>();
		col.add(1);
		col.add(2);
		col.add(3);
		col2.add(4);
		col2.add(5);
		col2.add(6);
		p("col=" + col.toString());
		p("col2=" + col2.toString());
		p("col.remove(1)=" + col.remove(1));
		p("col=" + col);
		col.clear();
		p("col.clear()=" + col.toString());
		col.add(1);
		col.add(2);
		col.add(3);
		p("col.removeAll(col2)=" + col.removeAll(col2));
		p("col=" + col);
		p("col.removeAll(col)=" + col.removeAll(col));
		p("col=" + col);
		col.add(1);
		col.add(2);
		col.add(3);
		col.add(1);
		col.add(2);
		col.add(3);
		p("col=" + col);
		p("col2=" + col2);
		p("col.retainAll(col2)=" + col.retainAll(col2));
		p("col=" + col);
		
		
	}

	private void generalCollections() {
		Collection<String> col = new HashSet<>();
		boolean b = col.add("george");
		p("added george:" + b);
		b = col.add("george");
		p("added george twice:" + b);
		try {
		b = col.addAll(null);
		} catch ( Exception e){
			p("adding a null throws an exception in a set");
		}
		p(col.toString());
		col.add("bob");
		Collection<String> col2 = new HashSet<>();
		col2.addAll(col);
		p("addAll of col to :" + col2.toString());
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new GenericsAndCollectionsChap12();

	}
	
	private static void p(String s){ System.out.println(s);}
}
