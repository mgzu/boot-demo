package com.example.framework.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.framework.web.entity.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName(value = "permission")
public class Permission extends BaseEntity {

	@NotBlank
	private String name;

	private Integer orderByPriority;

}
