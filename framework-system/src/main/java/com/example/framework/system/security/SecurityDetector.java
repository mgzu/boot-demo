package com.example.framework.system.security;

import lombok.experimental.UtilityClass;
import org.springframework.util.ClassUtils;

@UtilityClass
public class SecurityDetector {
	public static final SecurityStrategy STRATEGY;

	static {
		ClassLoader classLoader = SecurityDetector.class.getClassLoader();
		if (ClassUtils.isPresent("com.example.framework.system.security.SpringSecurityStrategy", classLoader)) {
			STRATEGY = new SpringSecurityStrategy();
		} else {
			STRATEGY = new SaTokenStrategy();
		}
	}

}
