package com.example.framework.starter.security.propties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
	 * white list
	 */
	private String[] whiteList = new String[]{
		"/docs"
	};

}
