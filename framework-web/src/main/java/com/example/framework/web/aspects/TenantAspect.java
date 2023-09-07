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
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * @author MaGuangZu
 * @since 2023-09-07
 */
@AllArgsConstructor
@Aspect
@Component
public class TenantAspect {

	private final TenantContext tenantContext;

	@PersistenceContext
	private final EntityManager entityManager;

	@Before("execution(* com.example.framework.web.configure.jpa.repositories.TenantRepository+.find*(..))")
	public void before(JoinPoint joinPoint) {
//		entityManager.unwrap(Session.class).disableFilter(TenantConstants.TENANT_FILTER_NAME);
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		if (AnnotationUtils.getAnnotation(methodSignature.getMethod(), DisableTenantFilter.class) == null) {
			entityManager
				.unwrap(Session.class)
				.enableFilter(TenantConstants.TENANT_FILTER_NAME)
				.setParameter(TenantConstants.TENANT_PARAMETER_NAME, tenantContext.getTenantId());
		} else {
			entityManager.unwrap(Session.class).disableFilter(TenantConstants.TENANT_FILTER_NAME);
		}
	}

}
