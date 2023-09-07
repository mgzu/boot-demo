package com.example.framework.web.configure.jpa.listeners;

import com.example.framework.web.contexts.TenantContext;
import com.example.framework.web.entity.BaseEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author MaGuangZu
 * @since 2023-09-07
 */
@Component
public class TenantEntityListener {

	@PrePersist
	@PreUpdate
	public void prePersistAndUpdate(Object object) {
		if (object instanceof BaseEntity baseEntity) {
			baseEntity.setTenantId(TenantContext.getTenantId());
		}
	}

	@PreRemove
	public void preRemove(Object object) {
		if (object instanceof BaseEntity baseEntity &&
			!Objects.equals(baseEntity.getTenantId(), TenantContext.getTenantId())) {
			throw new EntityNotFoundException();
		}
	}

}
