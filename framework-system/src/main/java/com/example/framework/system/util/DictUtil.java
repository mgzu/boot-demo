package com.example.framework.system.util;

import com.example.framework.system.constants.DictConstants;
import com.example.framework.system.entity.Dict;
import com.example.framework.system.entity.vo.DictVo;
import lombok.experimental.UtilityClass;
import org.dromara.hutool.core.collection.CollUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MaGuangZu
 * @since 2021-09-28
 */
@UtilityClass
public class DictUtil {

	/**
	 * Converts a Dict object to a DictVo object.
	 *
	 * @param dict the Dict object to be converted
	 * @return the converted DictVo object
	 */
	@Nullable
	public static DictVo typeConvert(Dict dict) {
		if (dict == null) {
			return null;
		}
		DictVo dictVo = new DictVo();
		dictVo.setDictType(dict.getDictType());
		switch (dict.getDictType()) {
			case DictConstants.DICT_TYPE_INT -> dictVo.setValue(Integer.parseInt(dict.getValue()));
			case DictConstants.DICT_TYPE_DECIMAL -> dictVo.setValue(new BigDecimal(dict.getValue()));
			case DictConstants.DICT_TYPE_BOOL -> dictVo.setValue(Boolean.parseBoolean(dict.getValue()));
			case DictConstants.DICT_TYPE_STRING -> dictVo.setValue(dict.getValue());
			// 其它都当做 string 处理
			default -> throw new IllegalArgumentException("不支持的字典类型：" + dict.getDictType());
		}
		return dictVo;
	}

	/**
	 * Converts a list of `Dict` objects to a list of `DictVo` objects.
	 *
	 * @param dicts the list of `Dict` objects to be converted
	 * @return the list of converted `DictVo` objects
	 */
	@NotNull
	public static List<DictVo> typeConvert(List<Dict> dicts) {
		if (CollUtil.isEmpty(dicts)) {
			return Collections.emptyList();
		}
		return dicts.stream()
			.map(DictUtil::typeConvert)
			.collect(Collectors.toList());
    }

}
