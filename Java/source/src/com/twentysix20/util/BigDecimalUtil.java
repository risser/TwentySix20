package com.twentysix20.util;

import java.math.BigDecimal;

public class BigDecimalUtil {

	public static final BigDecimal ZERO = new BigDecimal(0);
	public static final BigDecimal ONE = new BigDecimal(1);
	public static final BigDecimal TEN = new BigDecimal(10);
	public static final BigDecimal _64 = new BigDecimal(64);
	public static final BigDecimal _100 = new BigDecimal(100);
	public static final BigDecimal _1000 = new BigDecimal(1000);

	static public boolean greaterThan(BigDecimal first, BigDecimal second) {
		return first.compareTo(second) > 0;
	}
	static public boolean greaterThanOrEqual(BigDecimal first, BigDecimal second) {
		return first.compareTo(second) >= 0;
	}
	static public boolean lessThan(BigDecimal first, BigDecimal second) {
		return first.compareTo(second) < 0;
	}
	static public boolean lessThanOrEqual(BigDecimal first, BigDecimal second) {
		return first.compareTo(second) <= 0;
	}
	static public boolean equal(BigDecimal first, BigDecimal second) {
		return first.compareTo(second) == 0;
	}
	static public boolean notEqual(BigDecimal first, BigDecimal second) {
		return first.compareTo(second) != 0;
	}

    static public BigDecimal maximumOf(BigDecimal bg1, BigDecimal bg2){
		if (bg1 == null) return bg2;
		if (bg2 == null) return bg1;
		return (greaterThan(bg1, bg2) ? bg1 : bg2);
	}

    static public BigDecimal maximumOf(BigDecimal[] decimals) {
    	if (decimals.length == 0) return null;
    	BigDecimal max = decimals[0];
    	for (int i = 1; i < decimals.length; i++)
    		max = maximumOf(max, decimals[i]);
    	return max;
    }
    
    static public BigDecimal minimumOf(BigDecimal bg1, BigDecimal bg2){
    	
    	//this is not universal behavior, this method should throw Exception, but because it's (usage are) custom to 
    	//predictive guidance, I am continuing the trend.
		if (bg1 == null) return bg1;
		if (bg2 == null) return bg1;
		
		return (lessThan(bg1, bg2) ? bg1 : bg2);
	}
}