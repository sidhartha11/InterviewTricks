/**
 * 
 */
package com.georgecurington.functionalstudymod.runningtotal1;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author george
 *
 */
public interface NSecondPriceObjects {
long getMilliSeconds();
long updateCounter();
long getCounter();
CopyOnWriteArrayList<PriceObject> getPriceObjectList();
}
