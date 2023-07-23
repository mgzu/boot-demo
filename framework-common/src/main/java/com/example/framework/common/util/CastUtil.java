package com.example.framework.common.util;

import lombok.experimental.UtilityClass;

/**
 * @author MaGuangZu
 * @since 2023-07-18
 */
@UtilityClass
public class CastUtil {

	@SuppressWarnings("unchecked")
	public static <T> T safeFakeCast(Object o) {
		return (T) o;
	}

}
