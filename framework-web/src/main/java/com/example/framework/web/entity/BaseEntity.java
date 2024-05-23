package com.example.framework.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.example.framework.common.entity.PersistableEntity;
import com.example.framework.web.constants.TenantConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
public class BaseEntity extends PersistableEntity {

	@NotNull
	@TableField(value = "created_by", fill = FieldFill.INSERT)
	protected String createdBy;

	@TableField(value = "created_date", fill = FieldFill.INSERT)
	protected LocalDateTime createdDate;

	@TableField(value = "last_modified_by", fill = FieldFill.UPDATE)
	protected String lastModifiedBy;

	@TableField(value = "last_modified_date", fill = FieldFill.UPDATE)
	protected LocalDateTime lastModifiedDate;

	@Nullable
	private String remark;

	@JsonIgnore
	@TableField(value = TenantConstants.TENANT_COLUMN_NAME)
	private String tenantId;

	@NotNull
	@Version
	private Integer versionLock;

	@TableLogic
	private LocalDateTime deleted;

}
