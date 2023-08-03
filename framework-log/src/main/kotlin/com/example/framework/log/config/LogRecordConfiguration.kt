package com.example.framework.log.config

import com.example.framework.common.entity.BaseUser
import com.example.framework.log.entity.OperatorDO
import com.mzt.logapi.service.IOperatorGetService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

/**
 * @author MaGuangZu
 * @since 2022-08-19
 */
@Configuration
open class LogRecordConfiguration {

	@Bean
	open fun operatorGetService(): IOperatorGetService {
        return IOperatorGetService {
            val user = BaseUser()
            user.userId = "1640600092347"
            Optional.of(user)
                .map { item: BaseUser -> OperatorDO(item.userId) }
                .orElseThrow { IllegalArgumentException("user is null") }
        }
    }

}
