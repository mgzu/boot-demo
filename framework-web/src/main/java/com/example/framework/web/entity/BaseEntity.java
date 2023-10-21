package com.example.framework.web.entity;

import com.example.framework.common.entity.PersistableEntity;
import com.example.framework.web.configure.jpa.listeners.TenantEntityListener;
import com.example.framework.web.constants.TenantConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author MaGuangZu
 * @since 2021-12-29
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Setter
@Getter
@FilterDef(name = TenantConstants.TENANT_FILTER_NAME,
	parameters = @ParamDef(name = TenantConstants.TENANT_PARAMETER_NAME, type = String.class),
	defaultCondition = TenantConstants.TENANT_COLUMN_NAME + " = :" + TenantConstants.TENANT_PARAMETER_NAME)
@Filter(name = TenantConstants.TENANT_FILTER_NAME)
@EntityListeners({AuditingEntityListener.class, TenantEntityListener.class})
@MappedSuperclass
public class BaseEntity extends PersistableEntity {

	@NotNull
	@CreatedBy
	@Column(nullable = false, updatable = false)
	private String createdBy;

	@NotNull
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdDate;

	@NotNull
	@LastModifiedBy
	@Column(nullable = false)
	private String lastModifiedBy;

	@NotNull
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private LocalDateTime lastModifiedDate;

	@Nullable
	private String remark;

	@JsonIgnore
	@Column(nullable = false)
	private String tenantId;

	@NotNull
	@Version
	@Column(nullable = false)
	private Integer versionLock;

}
