package com.example.log;

import com.example.log.annotation.LogRecordPointcut;
import lombok.Setter;
import org.aopalliance.aop.Advice;
import org.jetbrains.annotations.NotNull;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;

/**
 * @author MaGuangZu
 * @since 2022-04-29
 */
@Setter
public class BeanFactoryLogRecordAdvisor extends AbstractGenericPointcutAdvisor {
    private LogRecordOperationSource logRecordOperationSource;
    private Pointcut pointcut;

    public BeanFactoryLogRecordAdvisor(LogRecordOperationSource logRecordOperationSource) {
        this.logRecordOperationSource = logRecordOperationSource;
        this.pointcut = new LogRecordPointcut(logRecordOperationSource);
    }

    @NotNull
    @Override
    public Advice getAdvice() {
        return logRecordOperationSource;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }
}
