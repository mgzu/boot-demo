package com.example.fsm.business.enums;

/**
 * 错误码枚举
 *
 * @author MaGuangZu
 * @since 2021-09-26
 */
public class ErrorCodeEnum {
    private ErrorCodeEnum() {
    }

    /* engine error begin */

    public static final String UN_SUPPORT_EVENT = "不支持的事件";
    public static final String NOT_FOUND_PROCESSOR = "未找到订单处理器";
    public static final String FOUND_MORE_PROCESSOR = "找到多个订单处理器";

    /* engine error end */

    /* business error begin */

    public static final String ORDER_NOT_FOUND = "未找到订单";
    public static final String ORDER_STATE_NOT_MATCH = "订单状态不匹配";

    /* business error end */

}
