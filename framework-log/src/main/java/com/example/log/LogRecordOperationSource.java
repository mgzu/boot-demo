package com.example.log;

import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;

/**
 * @author MaGuangZu
 * @since 2022-04-29
 */
public class LogRecordOperationSource implements Advice {
    public Collection<String> computeLogRecordOperations(Method method, Class<?> targetClass) {
        if (targetClass == null) {
            return Collections.emptyList();
        }
        return null;
    }
}
