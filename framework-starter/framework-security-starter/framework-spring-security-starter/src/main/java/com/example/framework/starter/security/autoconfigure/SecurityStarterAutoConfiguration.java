package com.example.framework.starter.security.autoconfigure;

import com.example.framework.starter.security.propties.FrameworkSecurityProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;

/**
 * @author MaGuangZu
 * @since 2023-09-28
 */
@Import(FrameworkSecurityProperties.class)
@AutoConfiguration
@ConditionalOnProperty(prefix = "framework.security", value = "enabled", havingValue = "true")
public class SecurityStarterAutoConfiguration {
}
