package com.example.framework.starter.security.propties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

/**
 * @author MaGuangZu
 * @since 2023-09-28
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "framework.security")
public class FrameworkSecurityProperties {

	/**
	 * permit urls
	 */
	private Set<String> permits = Collections.emptySet();

}
