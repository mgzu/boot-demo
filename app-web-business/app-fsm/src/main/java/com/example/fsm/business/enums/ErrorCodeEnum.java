package com.example.fsm.business.enums;

import lombok.experimental.UtilityClass;

/**
 * 错误码枚举
 *
 * @author MaGuangZu
 * @since 2021-09-26
 */
@UtilityClass
public class ErrorCodeEnum {

	/* engine error begin */

	public static final String UNSUPPORTED_EVENT = "Unsupported event";
	public static final String NOT_FOUND_PROCESSOR = "Not found processor";
	public static final String FOUND_MORE_PROCESSOR = "Found more processor";
	public static final String FOUND_MORE_CONTEXT = "Found more context";
	public static final String ORDER_NOT_FOUND = "Order not found";
	public static final String ORDER_STATE_NOT_MATCH = "Order state not match";

	/* engine error end */

}
