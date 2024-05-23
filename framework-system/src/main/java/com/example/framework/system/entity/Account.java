package com.example.framework.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.framework.web.entity.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Setter
@Getter
@TableName(value = "account")
public class Account extends BaseEntity {

	@NotBlank
	private String email;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@NotNull
	private Boolean enabled;

	private Boolean activation;

	private LocalDateTime activationDate;

	private Integer orderByPriority;

}
