package com.example.framework.web.constants;

import com.example.framework.web.validation.ValueValidator;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * @author MaGuangZu
 * @since 2021-09-28
 */
@UtilityClass
public class DictConstants implements ValueValidator {

	/**
	 * 字符串类型
	 */
	public static final String DICT_TYPE_STRING = "string";

	/**
	 * 整数类型
	 */
	public static final String DICT_TYPE_INT = "int";

	/**
	 * 小数类型
	 */
	public static final String DICT_TYPE_DECIMAL = "decimal";

	/**
	 * 布尔类型
	 */
	public static final String DICT_TYPE_BOOL = "bool";

}
