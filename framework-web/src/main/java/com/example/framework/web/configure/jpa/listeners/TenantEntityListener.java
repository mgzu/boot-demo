package com.example.framework.web.configure.jpa.listeners;

import com.example.framework.web.contexts.TenantContext;
import com.example.framework.web.entity.BaseEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author MaGuangZu
 * @since 2023-09-07
 */
@Component
public class TenantEntityListener {

	private TenantContext tenantContext;

	@Autowired
	public void setTenantContext(TenantContext tenantContext) {
		this.tenantContext = tenantContext;
	}

	@PrePersist
	@PreUpdate
	public void prePersistAndUpdate(Object object) {
		if (object instanceof BaseEntity baseEntity) {
			baseEntity.setTenantId(tenantContext.getTenantId());
		}
	}

	@PreRemove
	public void preRemove(Object object) {
		if (object instanceof BaseEntity baseEntity && baseEntity.getTenantId() != tenantContext.getTenantId()) {
			throw new EntityNotFoundException();
		}
	}

}
