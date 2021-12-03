package com.example.demo.framework;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author MaGuangZu
 * @since 2021-09-27
 */
@Setter
@Getter
public class Dict {
    @NotBlank
    private String dictType;
    @NotNull
    private Object value;

}
