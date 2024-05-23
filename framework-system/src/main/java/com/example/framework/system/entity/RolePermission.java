package com.example.framework.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.framework.web.entity.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName(value = "role_permission")
public class RolePermission extends BaseEntity {

	@NotBlank
	private String roleId;

	@NotBlank
	private String permissionId;

}
