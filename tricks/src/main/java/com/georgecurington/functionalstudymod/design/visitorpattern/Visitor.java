/**
 * 
 */
package com.georgecurington.functionalstudymod.design.visitorpattern;

import com.georgecurington.functionalstudymod.trees.unbalanced.Node;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public interface Visitor<K,V> {
	public void visit(Node<K,V> node);
}
