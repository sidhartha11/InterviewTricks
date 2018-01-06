/**
 * 
 */
package com.georgecurington.functionalstudymod.runningtotal1;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author george
 *
 */
public interface Calculator<T> {

	void process(T input);
	void dumpPriceObjects();
	ConcurrentMap<Long, NSecondPriceObjects> getNsecond();
	ConcurrentMap<Long, PriceObject> getMap();
	CopyOnWriteArrayList<T> getPriceObjectList();

}
