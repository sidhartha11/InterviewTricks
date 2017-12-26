/**
 * 
 */
package com.georgecurington.functionalstudymod.utilities;

/**
 * @author george
 *
 */
public class PairImpl<L,R> implements Pair<L,R> {
	private final L left;
	private final R right;
	/**
	 * 
	 */
	public PairImpl(L left, R right) {
		this.left  = left;
		this.right = right;
	}

	@Override
	public L getLeft() {
		// TODO Auto-generated method stub
		return this.left;
	}

	@Override
	public R getRight() {
		// TODO Auto-generated method stub
		return this.right;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PairImpl [left=" + left + ", right=" + right + "]";
	}

}
