/**
 * 
 */
package com.georgecurington.functionalstudymod.strings.anagram;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * This is a anagram trick algorithm. It is used to determine if 
 * two strings are anagrams of one another. It is not a "pure"
 * anagram in the sense that the two input strings do not have to 
 * be of equal size. If one string is larger than the other, then it
 * is possible that the smaller string might be an anagram of the larger
 * string if and only if the number of characters that are equal in the 
 * two strings are equal. 
 * 
 * For example, the following two strings would be considered anagrams:
 * 
 * {@code
 * String teststring1 = "AppleAppleAppleBob";
 * String teststring2 = "elppAelppAelppABobXrX";
 * AnagramHashIntf anagram1 = new AnagramHash(teststring1);
 * AnagramHashIntf anagram2 = new AnagramHash(teststring2);
 * assertTrue ("this should be an anagram", anagram1.isAnagram(anagram2)); 
 * }
 * 
 * In a simpler case:
 * Apple and elppA would also be considered anagrams.
 * or here is another anagram:
 * {@code}
 * 	teststring1 = "The grey fox jumped over the moon";
 *	teststring2 = "over the moon jumped fox The grey";
 * }
 * 
 * METHODOLOGY:
 * This program works as follows:
 * First the input string is converted into a form that can easily be
 * processed. It is simply put into a hash map, with the key containing 
 * the character of the string being processed. In addition to the character being 
 * the key, the number of occurrences of that character in the string is recorded. 
 * 
 * After the String is converted to this anagram friendly object, it is just a simple
 * case of looking up a keep in a map and comparing the value and the number of occurences.
 * 
 * Since this implementation loads the data into a hashmap, the time complexity must
 * be equal to the number of items in the string. And since we have two strings
 * involved it would be at worst case O(2n), and dropping the constant would make it 
 * linear. The later usage of map if O(0) complexity since maps are generally processed
 * in constant time.
 * 
 * Optmizations:
 * It might be possible to optimize this approach by converting the Map to a Set and 
 * using a combination of the character key and counter as the key of the set. With that
 * approach you might be able to use simple Set operations to determine if one set is an
 * acronym of the other.
 * </pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 *
 */
public class AnagramHash implements AnagramHashIntf {
	Map<Anagram, Anagram> map = new HashMap<>();

	public AnagramHash(String teststring2) {
		init(teststring2);
	}

	private void init(String teststring2) {
		StringBuilder sb = new StringBuilder(teststring2);
		for (int i = 0; i < sb.length(); i++) {
			Anagram a = new AnagramImpl(sb.charAt(i));
			Anagram tmp = map.putIfAbsent(a, a);
			if (tmp != null) {
				tmp.updateCount();
			}
		}
	}

	/**
	 * @return the map
	 */
	@Override
	public final Map<Anagram, Anagram> getMap() {
		return map;
	}

	@Override
	public boolean isAnagram(AnagramHashIntf anagram2) {
		return isAnagram(anagram2, false );
	}

	@Override
	public boolean isAnagram(AnagramHashIntf anagram2, boolean exactsize ) {

		/** determine which is larger **/
		int szMe = map.size();
		int szYou = anagram2.getMap().size();
		if ( exactsize ){
			if ( szMe != szYou ){
			return false;
			}
		}
		/** see if the larger one contains the smaller one **/
		Map<Anagram, Anagram> larger = null;
		Map<Anagram, Anagram> smaller = null;
		if (szMe > szYou) {
			larger = map;
			smaller = anagram2.getMap();
		} else if (szMe < szYou) {
			smaller = map;
			larger = anagram2.getMap();
		} else {
			larger = map;
			smaller = anagram2.getMap();
		}
		boolean looking = true;
		for (Anagram ele : smaller.values()) {
			Anagram you = larger.get(ele);
			if (you == null || you.getCount() != ele.getCount()) {
				looking = false;
				break;
			}
		}
		return looking;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AnagramHash [map=" + map + "]";
	}

}
