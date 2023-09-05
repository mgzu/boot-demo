package com.example.framework.system.entity;

import com.example.framework.web.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@ColumnDefault("0")
	@Column(nullable = false)
	private Integer orderPriority;

}
