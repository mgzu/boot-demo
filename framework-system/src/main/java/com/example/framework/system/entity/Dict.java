package com.example.framework.system.entity;

import com.example.framework.web.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

/**
 * @author MaGuangZu
 * @since 2021-09-27
 */
@Setter
@Getter
@DynamicInsert
@Entity
@Table(indexes = {
	@Index(columnList = "code", unique = true)
})
public class Dict extends BaseEntity {

	@NotBlank
	@Column(nullable = false)
	private String code;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@ColumnDefault("0")
	@Column(nullable = false)
	private Integer orderByPriority;

}
