package com.example.framework.web;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String dictType;
    @NotNull
    private Object value;

}
