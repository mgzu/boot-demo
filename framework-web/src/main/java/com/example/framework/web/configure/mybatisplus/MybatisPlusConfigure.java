package com.example.framework.web.configure.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.example.framework.web.properties.FrameworkProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MaGuangZu
 * @since 2024-05-19
 */
@AllArgsConstructor
@Configuration
public class MybatisPlusConfigure {

	private final FrameworkProperties frameworkProperties;

	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		if (frameworkProperties.getTenant() != null && frameworkProperties.getTenant().isEnable()) {
			TenantLineInnerInterceptor tenantInterceptor = new TenantLineInnerInterceptor();
			tenantInterceptor.setTenantLineHandler(new FrameworkTenantHandler());
			interceptor.addInnerInterceptor(tenantInterceptor);
		}
		interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 如果配置多个插件, 切记分页最后添加
		// 如果有多数据源可以不配具体类型, 否则都建议配上具体的 DbType
		return interceptor;
	}

	@Bean
	public IdentifierGenerator idGenerator() {
		return new IdGenerator();
	}

}
