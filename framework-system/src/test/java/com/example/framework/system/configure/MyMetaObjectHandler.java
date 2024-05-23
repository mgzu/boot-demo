package com.example.framework.system.configure;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.framework.web.constants.TenantConstants;
import com.example.framework.web.contexts.TenantContext;
import lombok.AllArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author MaGuangZu
 * @since 2024-05-23
 */
@AllArgsConstructor
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		this.strictInsertFill(metaObject, "createdBy", String.class, "1111");
		this.strictInsertFill(metaObject, "createdDate", LocalDateTime.class, LocalDateTime.now());
		this.strictInsertFill(metaObject, "lastModifiedBy", String.class, "1111");
		this.strictInsertFill(metaObject, "lastModifiedDate", LocalDateTime.class, LocalDateTime.now());
		this.strictInsertFill(metaObject, TenantConstants.TENANT_PARAMETER_NAME, String.class, TenantContext.getTenantId());
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, "lastModifiedBy", String.class, "1111");
		this.strictUpdateFill(metaObject, "lastModifiedDate", LocalDateTime.class, LocalDateTime.now());
	}
}
