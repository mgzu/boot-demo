package com.example.framework.web.configure.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.example.framework.web.constants.TenantConstants;
import com.example.framework.web.contexts.TenantContext;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;

/**
 * @author MaGuangZu
 * @since 2024-05-19
 */
public class FrameworkTenantHandler implements TenantLineHandler {

	@Override
	public Expression getTenantId() {
		return new StringValue(TenantContext.getTenantId());
	}

	@Override
	public String getTenantIdColumn() {
		return TenantConstants.TENANT_COLUMN_NAME;
	}

}
