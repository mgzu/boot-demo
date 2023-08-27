package com.example.framework.system.entity;

import com.example.framework.web.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "`USER`")
public class User extends BaseEntity {

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

}
