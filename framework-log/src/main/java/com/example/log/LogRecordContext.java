package com.example.log;

import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Stack;

/**
 * @author MaGuangZu
 * @since 2022-04-29
 */
@UtilityClass
public class LogRecordContext {

    // 不能用于多线程环境
    private static final InheritableThreadLocal<Stack<Map<String, Object>>> variableMapStack = new InheritableThreadLocal<>();

    public static Object putVariable(String key, Object value) {
        return variableMapStack.get().peek().put(key, value);
    }

    public static Map<String, Object> getVariables() {
        return variableMapStack.get().peek();
    }

}
