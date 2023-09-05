package com.example.framework.system.entity;

import com.example.framework.web.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Setter
@Getter
@Entity
@Table
public class Permission extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@ColumnDefault("0")
	@Column(nullable = false)
	private Integer orderPriority;

}
