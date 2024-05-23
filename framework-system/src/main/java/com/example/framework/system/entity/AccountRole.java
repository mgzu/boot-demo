package com.example.framework.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.framework.web.entity.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName(value = "account_role")
public class AccountRole extends BaseEntity {

	@NotBlank
	private String accountId;

	@NotBlank
	private String roleId;

}
