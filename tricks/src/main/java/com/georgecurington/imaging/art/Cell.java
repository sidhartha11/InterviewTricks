/**
 * 
 */
package com.georgecurington.imaging.art;

/**
 * @author Owner
 *
 */
public class Cell implements CellIntf {

	final private int x;
	final private int y;
	final private int w;
	final private int h;
	/**
	 * 
	 */
	public Cell(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.imaging.art.CellIntf#getX()
	 */
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.imaging.art.CellIntf#getY()
	 */
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.imaging.art.CellIntf#getW()
	 */
	@Override
	public int getW() {
		// TODO Auto-generated method stub
		return w;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.imaging.art.CellIntf#getH()
	 */
	@Override
	public int getH() {
		// TODO Auto-generated method stub
		return h;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", w=" + w + ", h=" + h + "]";
	}

}
