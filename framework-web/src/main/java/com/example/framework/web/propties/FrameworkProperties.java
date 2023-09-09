package com.example.framework.web.propties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "framework")
public class FrameworkProperties {

	@NestedConfigurationProperty
	private TenantProperties tenant;

}
