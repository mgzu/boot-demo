package com.example.framework.starter.security.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @author MaGuangZu
 * @since 2023-09-28
 */
//@Import(FrameworkSecurityProperties.class)
@AutoConfiguration
@ConditionalOnProperty(prefix = "framework.security", value = "enabled", havingValue = "true")
public class SecurityStarterAutoConfiguration {
}
