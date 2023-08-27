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
@Table
public class Permission extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer orderPriority;

}