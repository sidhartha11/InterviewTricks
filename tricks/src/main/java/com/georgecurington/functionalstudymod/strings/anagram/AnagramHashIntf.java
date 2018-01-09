/**
 * 
 */
package com.georgecurington.functionalstudymod.strings.anagram;

import java.util.Map;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 *
 */
public interface AnagramHashIntf {

	boolean isAnagram(AnagramHashIntf anagram2);

	Map<Anagram, Anagram> getMap();

	boolean isAnagram(AnagramHashIntf anagram2, boolean b);

}
