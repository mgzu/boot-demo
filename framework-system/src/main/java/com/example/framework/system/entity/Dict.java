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

/**
 * @author MaGuangZu
 * @since 2021-09-27
 */
@Setter
@Getter
@Entity
@Table
public class Dict extends BaseEntity {

	@ConstantsValidator(constants = {
		DictConstants.DICT_TYPE_STRING,
		DictConstants.DICT_TYPE_INT,
		DictConstants.DICT_TYPE_DECIMAL,
		DictConstants.DICT_TYPE_BOOL,
	})
	@Column(nullable = false)
	private String dictType;

	@NotBlank
	@Column(name = "`VALUE`", nullable = false)
	private String value;

	@ColumnDefault("0")
	@Column(nullable = false)
	private Integer orderPriority;

}
