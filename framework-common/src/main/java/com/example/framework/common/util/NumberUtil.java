package com.example.framework.common.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

/**
 * @author MaGuangZu
 * @since 2023-08-24
 */
@UtilityClass
public class NumberUtil {

	public boolean isInteger(String str, boolean ignoreDecimalsAreAllZero) {
		BigDecimal decimal = new BigDecimal(str);
		if (ignoreDecimalsAreAllZero) {
			return decimal.remainder(BigDecimal.ONE).doubleValue() == 0;
		} else {
			return decimal.scale() == 0;
		}
	}

}
