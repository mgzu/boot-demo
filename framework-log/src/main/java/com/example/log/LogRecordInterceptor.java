package com.example.log;

import com.example.log.service.IFunctionService;
import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author MaGuangZu
 * @since 2022-04-29
 */
@Setter
public class LogRecordInterceptor implements MethodInterceptor {
    private LogRecordOperationSource logRecordOperationSource;
    private IFunctionService functionService;
    private String tenant;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object obj = invocation.getThis();
        Object[] arguments = invocation.getArguments();
        Method method = invocation.getMethod();
        if (obj == null) {
            return invocation.proceed();
        }
        String methodName = method.getName();
        Collection<String> collection = logRecordOperationSource.computeLogRecordOperations(method, obj.getClass());
        boolean before = functionService.beforeFunction(methodName);
        Object proceed;
        if (before) {
            collection.forEach(logRecordOperation -> functionService.apply(methodName, logRecordOperation));
            proceed = invocation.proceed();
        } else {
            proceed = invocation.proceed();
            collection.forEach(logRecordOperation -> functionService.apply(methodName, logRecordOperation));
        }
        return proceed;
    }
}
