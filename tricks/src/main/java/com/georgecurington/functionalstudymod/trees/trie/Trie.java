/**
 * 
 */
package com.georgecurington.functionalstudymod.trees.trie;

import java.util.List;
import java.util.Set;

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
public interface Trie<K> {

	public boolean insert(List<K> key);

	boolean searchPrefix(List<K> key);

	boolean searchWord(List<K> key);

	public TrieNode<K> getRoot();

	List<List<K>> searchWordPrefixes(List<K> key);

	List<List<K>> createPrefixes(List<K> key);

	List<List<K>> createSuffixes(List<K> key);
}
