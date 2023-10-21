package com.example.framework.system.entity;

import com.example.framework.web.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Setter
@Getter
@DynamicInsert
@Entity
@Table(name = "`USER`")
public class User extends BaseEntity {

	@NotBlank
	@Column(nullable = false)
	private String username;

	@NotBlank
	@Column(nullable = false)
	private String password;

	@NotBlank
	@Column(nullable = false)
	private Boolean enabled;

	@ColumnDefault("0")
	@Column(nullable = false)
	private Integer orderByPriority;

}
