package com.example.demo.framework;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author MaGuangZu
 * @since 2021-09-27
 */
public class Dict {
    @NotBlank
    private String dictType;
    @NotNull
    private Object value;

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Dict{" +
                "dictType='" + dictType + '\'' +
                ", value=" + value +
                '}';
    }
}
