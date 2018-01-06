/**
 * 
 */
package com.georgecurington.functionalstudymod.runningtotal1;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author george
 *
 */
/**
 * @author george
 *
 */
public class MiniInput implements MiniInputIntf, Comparable<MiniInputIntf> { 
	
	public static final String[] POISONSTRING = 
	{"-1","___", "380", "206.63","1514852274268"};
	public static final MiniInputIntf POISON = new MiniInput(POISONSTRING);
	
	private int ID_F=0;
	private int ACRONYMN_F=1;
	private int CURRENTSHARESSOLD_F=2;
	private int CURRENTSHAREPRICE_F=3;
	private int TRANSACTIONTIME_F=4;
	
	private final long id;
	private final String acronymn;
	private final long currentSharesSold;
	private final BigDecimal currentSharePrice;
	private final LocalDateTime transactionTime;
	private final long milliseconds;
	/**
	 * 
	 */
	public MiniInput(String[] input ) {
		this.id = Long.parseLong(input[ID_F].trim());
		this.acronymn = input[ACRONYMN_F].trim();
		this.currentSharesSold = Long.parseLong(input[CURRENTSHARESSOLD_F].trim());
		this.currentSharePrice = new BigDecimal(input[CURRENTSHAREPRICE_F].trim());
		milliseconds = Long.parseLong(input[TRANSACTIONTIME_F].trim());
		this.transactionTime =
			    LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
		
	}
	
	public MiniInput(MiniInputIntf input ) {
		this.id = input.getId();
		this.acronymn = input.getAcronymn().trim();
		this.currentSharesSold = input.getCurrentSharesSold();
		this.currentSharePrice = input.getCurrentSharePrice();
		milliseconds = input.getMilliseconds();
		this.transactionTime = input.getTransactionTime();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MiniInput [id=" + id + ", acronymn=" + acronymn + ", currentSharesSold=" + currentSharesSold
				+ ", currentSharePrice=" + currentSharePrice + ", transactionTime=" + transactionTime
				+ ", milliseconds=" + milliseconds + "]";
	}
	/**
	 * @return the id
	 */
	@Override
	public final long getId() {
		return id;
	}
	/**
	 * @return the acronymn
	 */
	@Override
	public final String getAcronymn() {
		return acronymn;
	}
	/**
	 * @return the currentSharesSold
	 */
	@Override
	public final long getCurrentSharesSold() {
		return currentSharesSold;
	}
	/**
	 * @return the currentSharePrice
	 */
	@Override
	public final BigDecimal getCurrentSharePrice() {
		return currentSharePrice;
	}
	/**
	 * @return the transactionTime
	 */
	@Override
	public final LocalDateTime getTransactionTime() {
		return transactionTime;
	}
	/**
	 * @return the milliseconds
	 */
	@Override
	public final long getMilliseconds() {
		return milliseconds;
	}
	@Override
	public int compareTo(MiniInputIntf o) {
		if (getId() == o.getId() ){
			return 0;
		} else if ( getId() > o.getId()){
			return 1;
		} else if ( getId() < o.getId()){
			return -1;
		}
		return 0;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (!(obj instanceof MiniInput))
			return false;
		MiniInput other = (MiniInput) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
