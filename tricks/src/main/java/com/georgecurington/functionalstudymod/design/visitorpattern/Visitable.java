package com.georgecurington.functionalstudymod.design.visitorpattern;
/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public interface Visitable<K,V> {
	public void accept(Visitor<K,V> visitor);
}
