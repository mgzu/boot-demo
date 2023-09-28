package com.example.framework.starter.security.configure;

import com.example.framework.starter.security.propties.FrameworkSecurityProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author MaGuangZu
 * @since 2023-09-28
 */
@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final FrameworkSecurityProperties frameworkSecurityProperties;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		String[] whiteList = new String[]{
//			"/docs"
//		};
		String[] permits = frameworkSecurityProperties.getPermits().toArray(new String[0]);
		http
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(permits).permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(AbstractHttpConfigurer::disable)
			.rememberMe(AbstractHttpConfigurer::disable);
		return http.build();
	}

}
