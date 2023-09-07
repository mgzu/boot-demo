package com.example.framework.system.entity;

import com.example.framework.system.constants.DictConstants;
import com.example.framework.web.annotations.ConstantsValidator;
import com.example.framework.web.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

/**
 * @author MaGuangZu
 * @since 2023-09-06
 */
@Setter
@Getter
@DynamicInsert
@Entity
@Table
public class DictItem extends BaseEntity {

	@Column(nullable = false)
	private String dictId;

	@ConstantsValidator(constants = {
		DictConstants.DICT_TYPE_STRING,
		DictConstants.DICT_TYPE_INT,
		DictConstants.DICT_TYPE_DECIMAL,
		DictConstants.DICT_TYPE_BOOL,
	})
	@Column(nullable = false)
	private String type;

	@NotBlank
	@Column(name = "`VALUE`", nullable = false)
	private String value;

	@ColumnDefault("0")
	@Column(nullable = false)
	private Integer orderByPriority;

}
