package com.georgecurington.functionalstudymod.runningtotal1;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface MiniInputIntf extends CombiningPriceOBject {

	int compareTo(MiniInputIntf poison);

	long getMilliseconds();

	LocalDateTime getTransactionTime();

	BigDecimal getCurrentSharePrice();

	long getCurrentSharesSold();

	String getAcronymn();

	long getId();

}