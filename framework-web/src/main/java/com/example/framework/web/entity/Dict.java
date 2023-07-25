package com.example.framework.web.entity;

import com.example.framework.web.annotations.ConstantsValidator;
import com.example.framework.web.constants.DictConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MaGuangZu
 * @since 2021-09-27
 */
@Setter
@Getter
public class Dict {

	@ConstantsValidator(constants = {
		DictConstants.DICT_TYPE_STRING,
		DictConstants.DICT_TYPE_INT,
		DictConstants.DICT_TYPE_DECIMAL,
		DictConstants.DICT_TYPE_BOOL,
	})
	private String dictType;
	@NotNull
	private Object value;

}
