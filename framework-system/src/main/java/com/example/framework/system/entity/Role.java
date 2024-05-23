package com.example.framework.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.framework.web.entity.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName(value = "role")
public class Role extends BaseEntity {

	@NotBlank
	private String name;

	@NotNull
	private Boolean isAdmin;

	@NotNull
	private Boolean isSuperAdmin;

	private Integer orderByPriority;

}
