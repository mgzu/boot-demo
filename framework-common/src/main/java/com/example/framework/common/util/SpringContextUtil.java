package com.example.framework.common.util;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author MaGuangZu
 * @since 2022-08-16
 */
@SuppressWarnings({"java:S1104", "java:S1444", "java:S2696"}) // ignore sonar lint
@Component
public class SpringContextUtil implements ApplicationContextAware {

	public static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}
}
