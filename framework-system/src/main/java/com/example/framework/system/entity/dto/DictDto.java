package com.example.framework.system.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MaGuangZu
 * @since 2023-09-06
 */
@Setter
@Getter
public class DictDto {

	@NotBlank
	private String code;

	@NotBlank
	private String name;

}
