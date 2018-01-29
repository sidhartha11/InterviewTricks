/**
 * 
 */
package com.georgecurington.functionalstudymod.trees.trie;

import java.util.Map;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * <pre>
 * TrieNode
 * A selfreferencial datastructure containing two main components.
 * A Map of key,TrieNode and a boolean value representing the end
 * of a word that is part of the Node.
 * </pre>
 * 
 * @author George Curington
 * @version 1.0.0
 * @since Jan 28, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public interface TrieNode<K> {
	public Map<K,TrieNode<K>> getChildren();
	public boolean getEndOfWord();
	void setEndOfWord(boolean endOfWord);
	public static <K> TrieNode<K> newNode() {
		return new TrieNodeImpl<K>();
	}

}
