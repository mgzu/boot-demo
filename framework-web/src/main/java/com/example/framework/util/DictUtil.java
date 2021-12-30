package com.example.framework.util;

import com.example.framework.Dict;
import com.example.framework.constants.DictConstants;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author MaGuangZu
 * @since 2021-09-28
 */
public class DictUtil {
    private DictUtil() {
    }

    /**
     * 字典值转换
     *
     * @param dicts 字典列表
     * @return this
     */
    public static List<Dict> typeConvert(List<Dict> dicts) {
        dicts.forEach(i -> {
            switch (i.getDictType()) {
                case DictConstants.DICT_TYPE_INT -> i.setValue(Integer.parseInt(i.getValue().toString()));
                case DictConstants.DICT_TYPE_DECIMAL -> i.setValue(new BigDecimal(i.getValue().toString()));
                case DictConstants.DICT_TYPE_BOOL -> i.setValue(Boolean.parseBoolean(i.getValue().toString()));
                default -> throw new RuntimeException("字典类型不支持");
                // 其它都当做 string 处理
            }
        });
        return dicts;
    }
}
