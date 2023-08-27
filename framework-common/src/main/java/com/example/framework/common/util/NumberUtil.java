package com.example.framework.common.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

/**
 * @author MaGuangZu
 * @since 2023-08-24
 */
@UtilityClass
public class NumberUtil {

	/**
	 * Determines if the given string represents an integer number.
	 *
	 * @param stringNumber             the string to be checked
	 * @param ignoreDecimalsAreAllZero a boolean indicating whether to ignore decimals that are all zero
	 * @return true if the string represents an integer number, false otherwise
	 */
	public boolean isInteger(String stringNumber, boolean ignoreDecimalsAreAllZero) {
		try {
			BigDecimal decimal = new BigDecimal(stringNumber);
			if (ignoreDecimalsAreAllZero) {
				return decimal.remainder(BigDecimal.ONE).doubleValue() == 0;
			} else {
				return decimal.scale() == 0;
			}
		} catch (NumberFormatException | NullPointerException e) {
			// ignore
		}
		return false;
	}

}
