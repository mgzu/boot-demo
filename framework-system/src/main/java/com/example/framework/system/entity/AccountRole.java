package com.example.framework.system.entity;

import com.example.framework.web.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table
public class AccountRole extends BaseEntity {

	@NotBlank
	@Column(nullable = false)
	private String accountId;

	@NotBlank
	@Column(nullable = false)
	private String roleId;

}
