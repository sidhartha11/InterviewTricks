/**
 * 
 */
package com.georgecurington.functionalstudymod.trees.unbalanced;

import com.georgecurington.functionalstudymod.design.visitorpattern.Visitor;

/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 *
 */
public class NodeVisitor<K, V> implements Visitor<K, V> {

	/**
	 * 
	 */
	public NodeVisitor() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.design.visitorpattern.Visitor#visit(com.georgecurington.functionalstudymod.trees.unbalanced.Node)
	 */
	@Override
	public void visit(Node<K, V> node) {
		System.out.println("visited:" + node);

	}

}
