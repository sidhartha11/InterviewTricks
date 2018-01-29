/**
 * 
 */
package com.georgecurington.functionalstudymod.trees.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Jan 28, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public class TrieNodeImpl
<K> implements TrieNode<K> {

	private final Map<K,TrieNode<K>> children = new HashMap<>();
	private boolean endOfWord;
	/**
	 * 
	 */
	public TrieNodeImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<K,TrieNode<K>> getChildren() {
		// TODO Auto-generated method stub
		return children;
	}

	@Override
	public boolean getEndOfWord() {
		// TODO Auto-generated method stub
		return endOfWord;
	}
	
	@Override
	public void setEndOfWord(boolean endOfWord) {
		// TODO Auto-generated method stub
		this.endOfWord = endOfWord;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[children=" + children + ", endOfWord=" + endOfWord + "]";
	}

}
