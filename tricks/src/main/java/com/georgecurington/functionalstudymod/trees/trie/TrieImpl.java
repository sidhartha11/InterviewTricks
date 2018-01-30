/**
 * 
 */
package com.georgecurington.functionalstudymod.trees.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

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
 * This class contains various methods for inserting and searching 
 * A Trie. The data structure used is a Map implementation that contains
 * a K key and a Trie Node as the value. Also an indicator that determines if 
 * the node is a terminatingnode, i.e. the end element of the key that was inserted
 * into the tree.
 * 
 * Note that this is a generic class that processes a user-defined object as a key.
 * This means that the keys can not only be the usual characters that you find in most
 * Trie implementations but can also be complex objects. For example, the objects could 
 * be sentences,shapes , etc .
 * 
 * 
 * time complexity for insert and delete is:
 * O(l x n) where 
 * n = number of words in the trie
 * l = average length of a string in the trie
 * 
 * time complexity for prefix and word search is:
 * O ( l ) where
 * l = length of string being searched for.
 * </pre>
 * 
 * @author George Curington
 * @version 1.0.0
 * @since Jan 28, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 * @see com.georgecurington.functionalstudymod.trees.trie.TrieNode
 * @see com.georgecurington.functionalstudymod.trees.trie.TrieNodeImpl
 */
public class TrieImpl<K extends Comparable<? super K>> implements Trie<K> {

	/**
	 * Initially the Trie has an empty root
	 */
	private TrieNode<K> root = TrieNode.newNode();

	/**
	 * 
	 */
	public TrieImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * <pre>
	 * This method inserts a list of some K into the Trie.
	 * </pre>
	 * @see com.georgecurington.functionalstudymod.trees.trie.Trie#insert(java.util.List)
	 */
	@Override
	public boolean insert(List<K> key) {
		TrieNode<K> temp = root;
		int sizeOfkey = key.size();
		for (int i = 0; i < sizeOfkey; i++) {
			K ele = key.get(i);
			TrieNode<K> node = temp.getChildren().get(ele);
			if (node == null) {
				/**
				 * so we initially end up with: root ---> map( a , newnode )
				 */
				TrieNode<K> newnode = TrieNode.newNode();

				/** this new node must be put in temp **/
				temp.getChildren().put(ele, newnode);
				/** now we set Temp to the newly created trienode **/
				temp = newnode;
				if (i == sizeOfkey - 1) {
					newnode.setEndOfWord(true);
				}
			} else {
				/** we just set temp to the node that is occupied **/
				temp = node;
			}
		}
		return false;
	}

	/**
	 * <pre>
	 * This method will search for a valid prefix of some word that had been previously
	 * inserted into the tree
	 * </pre>
	 * @see com.georgecurington.functionalstudymod.trees.trie.Trie#searchPrefix(java.util.List)
	 */
	@Override
	public boolean searchPrefix(List<K> key) {
		Objects.requireNonNull(key);
		TrieNode<K> temp = root;
		int sizeOfkey = key.size();
		for (int i = 0; i < sizeOfkey; i++) {
			K ele = key.get(i);
			/**
			 * just search for items in the input. if any item is not found, we
			 * return false
			 */
			TrieNode<K> node = temp.getChildren().get(ele);
			if (node == null) {
				/** no match found for this item **/
				return false;
			} else {
				/** we just set temp to the node that is occupied **/
				temp = node;
			}
		}
		return true;
	}

	/**
	 * <pre>
	 * This is a word search. It searches the tree for a valid word that has been
	 * inserted into the tree.
	 * </pre>
	 */
	@Override
	public boolean searchWord(List<K> key) {
		Objects.requireNonNull(key);
		TrieNode<K> temp = root;
		int sizeOfkey = key.size();
		for (int i = 0; i < sizeOfkey; i++) {
			K ele = key.get(i);
			/**
			 * just search for items in the input. if any item is not found, we
			 * return false
			 */
			TrieNode<K> node = temp.getChildren().get(ele);
			if (node == null) {
				/** no match found for this item **/
				return false;
			} else {
				/** we just set temp to the node that is occupied **/
				temp = node;
			}
		}
		if (temp.getEndOfWord()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * <pre>
	 * This method will return all the prefixes that lead up to a word being searched
	 * for. Basically the path to word is returned as separate entries in a 
	 * List Of Lists
	 * For example:
	 * if the word being searched for is:
	 * [a, b, c, q, l, m, n, r, s, t, 1, 2]
	 * and if the following words have been inserted into the tree:
	 * 
     * [a, b, c]
	 * [a, b, q]
	 * [a, b, q, r]
	 * [a, b, g, l]
	 * [c, d, f]
	 * [a, b, c, d]
	 * [a, b, c, q]
	 * [l, m, n]
	 * [1, 2]
	 * [a, b, c, q, l, m, n, r, s, t, 1, 2]
	 * 
	 * then you will get all the words that make up prefixes returned:
	 * 
	 * l=[[a, b, c], [a, b, c, q], [a, b, c, q, l, m, n, r, s, t, 1, 2]]
	 * </pre>
	 * @see com.georgecurington.functionalstudymod.trees.trie.Trie#searchWordPrefixes(java.util.List)
	 */
	@Override
	public List<List<K>> searchWordPrefixes(List<K> key) {
		Objects.requireNonNull(key);
		TrieNode<K> temp = root;
		List<List<K>> listOlist = new ArrayList<List<K>>();
		List<List<K>> rlistOlist = new ArrayList<List<K>>();
		List<K> list = new ArrayList<>();
		int sizeOfkey = key.size();
		for (int i = 0; i < sizeOfkey; i++) {
			K ele = key.get(i);
			/**
			 * just search for items in the input. if any item is not found, we
			 * return false
			 */
			TrieNode<K> node = temp.getChildren().get(ele);
			if (node == null) {
				/** no match found for this item **/
				return Collections.emptyList();
			} else {
				/** we just set temp to the node that is occupied **/
				list.add(ele);

				
				listOlist.add(new ArrayList<K>(list));
				
				if ( node.getEndOfWord()) { /** this condition will only add an actaual word **/
					   rlistOlist.add(listOlist.get(listOlist.size()-1));
					}
				temp = node;
			}
		}
		if (temp.getEndOfWord()) {
			return rlistOlist;
		} else {
			return Collections.emptyList();
		}
	}
	
	/**
	 * <pre>
	 * Accidental Discovery:
	 * This routine can be used to generate a list of prefixes from the input list:
	 * Example:
	 * If the list input is:
	 * [a, b, c, q, l, m, n, r, s, t, 1, 2]
	 * The output will be this:
	 * l=[
	 * [a], 
	 * [a, b], 
	 * [a, b, c], 
	 * [a, b, c, q], 
	 * [a, b, c, q, l], 
	 * [a, b, c, q, l, m], 
	 * [a, b, c, q, l, m, n], 
	 * [a, b, c, q, l, m, n, r], 
	 * [a, b, c, q, l, m, n, r, s], 
	 * [a, b, c, q, l, m, n, r, s, t], 
	 * [a, b, c, q, l, m, n, r, s, t, 1], 
	 * [a, b, c, q, l, m, n, r, s, t, 1, 2]
	 * ]
	 * 
	 * This can come in handy when experimenting with various 
	 * types of Trie searches based on converting the string
	 * to a list of prefixes up front and storing them into
	 * the tree.
	 * 
	 * </pre>
	 * 
	 * 
	 * 
	 * @see com.georgecurington.functionalstudymod.trees.trie.Trie#createPrefixes(java.util.List)
	 */
	@Override
	public List<List<K>> createPrefixes(List<K> key) {
		Objects.requireNonNull(key);
		TrieNode<K> temp = root;
		List<List<K>> listOlist = new ArrayList<List<K>>();
		List<K> list = new ArrayList<>();
		int sizeOfkey = key.size();
		for (int i = 0; i < sizeOfkey; i++) {
			K ele = key.get(i);
			/**
			 * just search for items in the input. if any item is not found, we
			 * return false
			 */
			TrieNode<K> node = temp.getChildren().get(ele);
			if (node == null) {
				/** no match found for this item **/
				return Collections.emptyList();
			} else {
				/** we just set temp to the node that is occupied **/
				list.add(ele);
				listOlist.add(new ArrayList<K>(list));
				temp = node;
			}
		}
			return listOlist;
	}
	
	/**
	 * <pre>
	 * The method below will generate a list of suffixes from the input list of K
	 * This can be used as input to a Suffix Tree. Basically, the Trie can be used to only
	 * store suffixes of the input, logically creating a Suffix trie.
	 * </pre>
	 * @see com.georgecurington.functionalstudymod.trees.trie.Trie#createSuffixes(java.util.List)
	 */
	@Override
	public List<List<K>> createSuffixes(List<K> key) {
		Objects.requireNonNull(key);
		List<List<K>> listOlist = new ArrayList<List<K>>();
		int sizeOfkey = key.size();
		listOlist.add(key);
		for (int i = 1; i < sizeOfkey; i++) {
			List<K> sub = key.subList(i, sizeOfkey);
			listOlist.add(new ArrayList<K>(sub));
		}
		return listOlist;
	}


	@Override
	public TrieNode<K> getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[root=" + root + "]";
	}

	public static void main(String... strings) {

		Trie<String> trie = new TrieImpl<>();

		List<String> list = Arrays.asList("a", "b", "c");
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		list = Arrays.asList("a", "b", "q");
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		list = Arrays.asList("a", "b", "q","r");
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);

		list = Arrays.asList("a", "b", "g", "l");
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);

		list = Arrays.asList("c", "d", "f");
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);

		list = Arrays.asList("a", "b", "c", "d");
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		list = Arrays.asList("a", "b", "c", "q");
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);

		list = Arrays.asList("l", "m", "n");
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		list = Arrays.asList("1", "2");
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);
		
		list = Arrays.asList("a", "b", "c", "q", "l", "m" , "n" , "r","s","t", "1","2");
		System.out.println(list);
		trie.insert(list);
		System.out.println(trie);

		boolean found = false;
		list = Arrays.asList("a", "b", "c", "q", "l", "m" , "n" , "r","s","t", "1","2");
		System.out.println("word prefix search for " + list);
		List<List<String>> l = trie.searchWordPrefixes(list);
		System.out.println("l=" + l);
		
		found = false;
		System.out.println("all prefix search for " + list);
		l = trie.createPrefixes(list);
		System.out.println("l=" + l);
		
		/** test suffix creation **/
		found = false;
		System.out.println("create suffixes from " + list);
		l = trie.createSuffixes(list);
		System.out.println("l=" + l);

	}

}
