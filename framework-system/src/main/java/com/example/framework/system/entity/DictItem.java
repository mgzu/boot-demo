package com.example.framework.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.framework.system.constants.DictConstants;
import com.example.framework.web.annotations.ConstantsValidator;
import com.example.framework.web.entity.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MaGuangZu
 * @since 2023-09-06
 */
@Setter
@Getter
@TableName(value = "dict_item")
public class DictItem extends BaseEntity {

	private String dictId;

	@ConstantsValidator(constants = {
		DictConstants.DICT_TYPE_STRING,
		DictConstants.DICT_TYPE_INT,
		DictConstants.DICT_TYPE_DECIMAL,
		DictConstants.DICT_TYPE_BOOL,
	})
	private String type;

	@NotBlank
	private String value;

	private Integer orderByPriority;

}
