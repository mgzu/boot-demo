package com.example.framework.system.util;

import com.example.framework.system.constants.DictConstants;
import com.example.framework.system.entity.DictItem;
import com.example.framework.system.entity.vo.DictItemVo;
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
	 * Converts a DictItem object to a DictItemVo object.
	 *
	 * @param dictItem the DictItem object to be converted
	 * @return the converted DictItemVo object, or null if the input is null
	 */
	@Nullable
	public static DictItemVo convert(DictItem dictItem) {
		if (dictItem == null) {
			return null;
		}
		DictItemVo dictItemVo = new DictItemVo();
		dictItemVo.setDictType(dictItem.getType());
		switch (dictItem.getType()) {
			case DictConstants.DICT_TYPE_INT -> dictItemVo.setValue(Integer.parseInt(dictItem.getValue()));
			case DictConstants.DICT_TYPE_DECIMAL -> dictItemVo.setValue(new BigDecimal(dictItem.getValue()));
			case DictConstants.DICT_TYPE_BOOL -> dictItemVo.setValue(Boolean.parseBoolean(dictItem.getValue()));
			case DictConstants.DICT_TYPE_STRING -> dictItemVo.setValue(dictItem.getValue());
			default -> throw new IllegalArgumentException("Unsupported type：" + dictItem.getValue());
		}
		return dictItemVo;
	}

	/**
	 * Converts a list of DictItem objects to a list of DictItemVo objects.
	 *
	 * @param dicts the list of DictItem objects to convert
	 * @return the converted list of DictItemVo objects
	 */
	@NotNull
	public static List<DictItemVo> convert(List<DictItem> dicts) {
		if (CollUtil.isEmpty(dicts)) {
			return Collections.emptyList();
		}
		return dicts.stream()
			.map(DictUtil::convert)
			.collect(Collectors.toList());
	}

}
