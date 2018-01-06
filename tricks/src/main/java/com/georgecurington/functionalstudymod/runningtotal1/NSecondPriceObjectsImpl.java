/**
 * 
 */
package com.georgecurington.functionalstudymod.runningtotal1;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author george
 *
 */
public class NSecondPriceObjectsImpl implements NSecondPriceObjects {
	
	private final CopyOnWriteArrayList<PriceObject> priceObjectList=new CopyOnWriteArrayList<>();
	private final long milliSeconds;
	private long counter=1;
	/**
	 * @param priceObject
	 * @param milliSeconds
	 */
	public NSecondPriceObjectsImpl(PriceObject priceObject, long milliSeconds) {
		super();
		priceObjectList.add(priceObject);
		this.milliSeconds = milliSeconds;
	}

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.NSecondPriceObjects#getMilliSeconds()
	 */
	@Override
	public long getMilliSeconds() {
		// TODO Auto-generated method stub
		return this.milliSeconds;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NSecondPriceObjectsImpl [priceObjectList=" + priceObjectList + ", milliSeconds=" + milliSeconds
				+ ", counter=" + counter + "]";
	}
	
	@Override 
	public long getCounter(){
		return this.counter;
	}
	@Override
	public long updateCounter(){
		return ++this.counter;
	}


	/**
	 * @return the priceObjectList
	 */
	@Override
	public final CopyOnWriteArrayList<PriceObject> getPriceObjectList() {
		return priceObjectList;
	}
}
