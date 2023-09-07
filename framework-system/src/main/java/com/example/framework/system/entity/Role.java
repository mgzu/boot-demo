package com.example.framework.system.entity;

import com.example.framework.web.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Setter
@Getter
@DynamicInsert
@Entity
@Table
public class Role extends BaseEntity {

	@NotBlank
	@Column(nullable = false)
	private String name;

	@NotNull
	@Column(nullable = false)
	private Boolean isAdmin;

	@NotNull
	@Column(nullable = false)
	private Boolean isSuperAdmin;

	@ColumnDefault("0")
	@Column(nullable = false)
	private Integer orderByPriority;

}
