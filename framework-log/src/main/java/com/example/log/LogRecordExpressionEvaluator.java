package com.example.log;

import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MaGuangZu
 * @since 2022-04-29
 */
public class LogRecordExpressionEvaluator extends CachedExpressionEvaluator {

    private Map<ExpressionKey, Expression> expressionCache = new ConcurrentHashMap<>(64);

    private final Map<AnnotatedElementKey, Method> targetMethodCache = new ConcurrentHashMap<>(64);

    public String parseExpression(String conditionExpression, AnnotatedElementKey methodKey, EvaluationContext evalContext) {
        return getExpression(this.expressionCache, methodKey, conditionExpression).getValue(evalContext, String.class);
    }
}
