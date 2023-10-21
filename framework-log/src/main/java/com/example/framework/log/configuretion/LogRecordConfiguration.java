package com.example.framework.log.configuretion;

import com.example.framework.common.entity.BaseUser;
import com.example.framework.log.entity.bo.OperatorBO;
import com.mzt.logapi.service.IOperatorGetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 * @author MaGuangZu
 * @since 2022-08-19
 */
@Configuration
public class LogRecordConfiguration {

	@Bean
	public IOperatorGetService operatorGetService() {
		var user = new BaseUser();
		user.setUserId("1640600092347");
		return () -> Optional.of(user)
			.map(item -> new OperatorBO(item.getUserId()))
			.orElseThrow(() -> new IllegalArgumentException("user is null"));
	}

}
