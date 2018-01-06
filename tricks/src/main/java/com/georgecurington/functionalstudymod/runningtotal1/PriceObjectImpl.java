/**
 * 
 */
package com.georgecurington.functionalstudymod.runningtotal1;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


/**
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 * <pre>
 * This example exercise is meant to read in pricing information
 * related to a unique input ID and its associated acronyms.
 * Each input item consist as follows:
 * 1. id  which is unique
 * 2. acronyms need not be unique and each element can have up to 10
 * unique acronymns within its own id.
 * 3. current shares being traded at the current transactionTime
 * 4. current share price at the current transactionTime
 * 5. transactionTime based on a 250ms ticker. 
 * 
 * The goal is to print out various statistics regarding the state of
 * a PriceObject upon request. 
 * E.G.
 * avgPricePerDay
 *
 */
public class PriceObjectImpl implements PriceObject, Comparable<PriceObject>{
	
	private final MiniInputIntf miniInput;

	/** data generated from real time input **/
	private long totalVolumePerMonth;
	private BigDecimal avgPricePerDay;
	private long maxSharesAtThisTime;
	private long maxSharesPerMonth;
	private long maxSharesPerDay;
	private Set<String> acronymns=new HashSet<>();
	private long counter=1;
	/**
	 * 
	 */
	public PriceObjectImpl(MiniInputIntf miniInput) {
		this.miniInput = miniInput;
		this.acronymns.add(miniInput.getAcronymn());
	}
	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.PriceObject#getAcronymns()
	 */
	@Override
	public Set<String> getAcronymns() {
		return acronymns;
	}
	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.PriceObject#setAcronymns(java.util.Set)
	 */
	@Override
	public void setAcronymns(Set<String> acronymns) {
		this.acronymns = acronymns;
	}
	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.PriceObject#getTotalVolumePerMonth()
	 */
	@Override
	public long getTotalVolumePerMonth() {
		return totalVolumePerMonth;
	}
	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.PriceObject#setTotalVolumePerMonth(long)
	 */
	@Override
	public void setTotalVolumePerMonth(long totalVolumePerMonth) {
		this.totalVolumePerMonth = totalVolumePerMonth;
	}
	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.PriceObject#getAvgPricePerDay()
	 */
	@Override
	public BigDecimal getAvgPricePerDay() {
		return avgPricePerDay;
	}
	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.PriceObject#setAvgPricePerDay(long)
	 */
	@Override
	public void setAvgPricePerDay(BigDecimal avgPricePerDay) {
		this.avgPricePerDay = avgPricePerDay;
	}
	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.PriceObject#getMaxSharesAtThisTime()
	 */
	@Override
	public long getMaxSharesAtThisTime() {
		return maxSharesAtThisTime;
	}
	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.PriceObject#setMaxSharesAtThisTime(long)
	 */
	@Override
	public void setMaxSharesAtThisTime(long maxSharesAtThisTime) {
		this.maxSharesAtThisTime = maxSharesAtThisTime;
	}
	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.PriceObject#getMaxSharesPerMonth()
	 */
	@Override
	public long getMaxSharesPerMonth() {
		return maxSharesPerMonth;
	}
	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.PriceObject#setMaxSharesPerMonth(long)
	 */
	@Override
	public void setMaxSharesPerMonth(long maxSharesPerMonth) {
		this.maxSharesPerMonth = maxSharesPerMonth;
	}
	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.PriceObject#getCurrentSharePrice()
	 */

	/* (non-Javadoc)
	 * @see com.georgecurington.functionalstudymod.runningtotal1.PriceObject#getId()
	 */
	@Override
	public long getId() {
		return miniInput.getId();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (miniInput.getId() ^ (miniInput.getId() >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PriceObjectImpl))
			return false;
		PriceObjectImpl other = (PriceObjectImpl) obj;
		if (miniInput.getId() != other.miniInput.getId())
			return false;
		return true;
	}
	@Override
	public long getMaxSharesPerDay() {
		// TODO Auto-generated method stub
		return this.maxSharesPerDay;
	}
	@Override
	public void setMaxSharesPerDay(long maxSharesPerDay) {
		this.maxSharesPerDay = maxSharesPerDay;
		
	}
	@Override
	public int compareTo(PriceObject that) {
		long me = getId();
		long you = that.getId();
		if (me  > you ) {
			return 1;
		} else if (me < you ) {
			return -1;
		} else {
			return 0;
		}
	}
	@Override
	public long getMilliseconds() {
		// TODO Auto-generated method stub
		return this.miniInput.getMilliseconds();
	}	@Override
	public LocalDateTime getTransactionTime() {
		// TODO Auto-generated method stub
		return this.miniInput.getTransactionTime();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PriceObjectImpl [miniInput=" + miniInput + ", totalVolumePerMonth=" + totalVolumePerMonth
				+ ", avgPricePerDay=" + avgPricePerDay + ", maxSharesAtThisTime=" + maxSharesAtThisTime
				+ ", maxSharesPerMonth=" + maxSharesPerMonth + ", maxSharesPerDay=" + maxSharesPerDay + ", acronymns="
				+ acronymns + ", counter=" + counter + "]";
	}
	@Override
	public long getCounter() {
		// TODO Auto-generated method stub
		return this.counter;
	}
	@Override
	public long updateCounter() {
		this.counter++;
		return this.counter;
	}
	

}
