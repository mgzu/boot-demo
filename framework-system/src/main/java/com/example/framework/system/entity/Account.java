package com.example.framework.system.entity;

import com.example.framework.web.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Setter
@Getter
@DynamicInsert
@Entity
@Table
public class Account extends BaseEntity {

	@NotBlank
	@Column(nullable = false)
	private String email;

	@NotBlank
	@Column(nullable = false)
	private String username;

	@NotBlank
	@Column(nullable = false)
	private String password;

	@NotNull
	@Column(nullable = false)
	private Boolean enabled;

	@ColumnDefault("false")
	@Column(nullable = false)
	private Boolean activation;

	private LocalDateTime activationDate;

	@ColumnDefault("0")
	@Column(nullable = false)
	private Integer orderByPriority;

}
