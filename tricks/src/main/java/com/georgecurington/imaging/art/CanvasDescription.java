/**
 * 
 */
package com.georgecurington.imaging.art;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Owner
 *
 */
public class CanvasDescription implements CanvasDescriptionInf {

	/**
	 * 
	 */
	public CanvasDescription() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.imaging.art.CanvasDescriptionInf#getCanvas()
	 */
	@Override
	public List<List<CellIntf>> getCanvas() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.imaging.art.CanvasDescriptionInf#getRow(int)
	 */
	@Override
	public List<CellIntf> getRow(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.imaging.art.CanvasDescriptionInf#getColumn(int, int)
	 */
	@Override
	public CellIntf getColumn(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.imaging.art.CanvasDescriptionInf#isNeighborCanyon(int, int)
	 */
	@Override
	public boolean isNeighborCanyon(int row, int col) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.imaging.art.CanvasDescriptionInf#createRow(int, int, int, int, int)
	 */
	@Override
	public List<CellIntf> createRow(int yi, int width,int height, int minw, int maxw, int minh, int maxh) {
		List<CellIntf> list = new ArrayList<>();
		boolean going=true;
		int x=0;
		int y=yi;
		while ( going ) {
			int widthi = ThreadLocalRandom.current().nextInt(minw, maxw+1);
//			int heighti= ThreadLocalRandom.current().nextInt(minh, maxh+1);
			int heighti= maxh;

			list.add(new Cell(x,y,widthi,heighti));
			x += widthi;
			if ( x >= width ) {
				going = false ;
			}
		}
		return list;
	}

	@Override
	public List<CellIntf> createRowEqual(int yi, int width, int height, int inc) {
		List<CellIntf> list = new ArrayList<>();
		boolean going=true;
		int x=0;
		int y=yi;
		while ( going ) {
			int widthi = inc;
//			int heighti= ThreadLocalRandom.current().nextInt(minh, maxh+1);
			int heighti= inc;

			list.add(new Cell(x,y,widthi,heighti));
			x += widthi;
			if ( x >= width ) {
				going = false ;
			}
		}
		return list;
	}

}
