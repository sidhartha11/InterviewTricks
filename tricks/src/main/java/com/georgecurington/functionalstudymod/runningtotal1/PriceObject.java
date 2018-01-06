package com.georgecurington.functionalstudymod.runningtotal1;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public interface PriceObject {

	long getCounter();
	/**
	 * @return the acronymns
	 */
	Set<String> getAcronymns();

	/**
	 * @param acronymns the acronymns to set
	 */
	void setAcronymns(Set<String> acronymns);

	/**
	 * @return the totalVolumePerMonth
	 */
	long getTotalVolumePerMonth();

	/**
	 * @param totalVolumePerMonth the totalVolumePerMonth to set
	 */
	void setTotalVolumePerMonth(long totalVolumePerMonth);

	/**
	 * @return the avgPricePerDay
	 */
	BigDecimal getAvgPricePerDay();

	/**
	 * @param avgPricePerDay the avgPricePerDay to set
	 */
	void setAvgPricePerDay(BigDecimal avgPricePerDay);

	/**
	 * @return the maxSharesPerDay
	 */
	long getMaxSharesPerDay();

	/**
	 * @param maxSharesPerDay the maxSharesPerDay to set
	 */
	void setMaxSharesPerDay(long maxSharesPerDay);

	/**
	 * @return the maxSharesPerMonth
	 */
	long getMaxSharesPerMonth();

	/**
	 * @param maxSharesPerMonth the maxSharesPerMonth to set
	 */
	void setMaxSharesPerMonth(long maxSharesPerMonth);

	/**
	 * @return the id
	 */
	long getId();

	long getMaxSharesAtThisTime();

	void setMaxSharesAtThisTime(long maxSharesAtThisTime);
	
	long getMilliseconds();

	LocalDateTime getTransactionTime();
	long updateCounter();


}