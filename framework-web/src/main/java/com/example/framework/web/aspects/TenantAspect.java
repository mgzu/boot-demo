package com.example.framework.web.aspects;

import com.example.framework.web.annotations.DisableTenantFilter;
import com.example.framework.web.constants.TenantConstants;
import com.example.framework.web.contexts.TenantContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.Session;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * @author MaGuangZu
 * @since 2023-09-07
 */
@ConditionalOnProperty(prefix = "framework.tenant", name = "enable", havingValue = "true")
@AllArgsConstructor
@Aspect
@Component
public class TenantAspect {

	@PersistenceContext
	private final EntityManager entityManager;

	@Before("execution(* com.example.framework.web.configure.jpa.repositories.TenantRepository+.find*(..))")
	public void before(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		if (Boolean.TRUE.equals(TenantContext.getDisable()) ||
			AnnotationUtils.getAnnotation(methodSignature.getMethod(), DisableTenantFilter.class) != null
		) {
			entityManager.unwrap(Session.class)
				.disableFilter(TenantConstants.TENANT_FILTER_NAME);
		} else {
			entityManager
				.unwrap(Session.class)
				.enableFilter(TenantConstants.TENANT_FILTER_NAME)
				.setParameter(TenantConstants.TENANT_PARAMETER_NAME, TenantContext.getTenantId());
		}
	}

}
