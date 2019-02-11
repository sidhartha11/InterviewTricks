/**
 * 
 */
package com.georgecurington.imaging.art;

import java.util.List;

/**
 * @author Owner
 *
 */
public interface CanvasDescriptionInf  {
   List<List<CellIntf>> getCanvas();
   List<CellIntf> getRow(int index);
   CellIntf getColumn(int row, int col);
   boolean isNeighborCanyon(int row, int col);
   List<CellIntf> createRow(int y, int width,int height, int minw, int maxw, int minh, int maxh);
   List<CellIntf> createRowEqual(int y, int width,int height, int inc);

}
