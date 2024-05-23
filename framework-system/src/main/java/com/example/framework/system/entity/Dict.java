package com.example.framework.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.framework.web.entity.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MaGuangZu
 * @since 2021-09-27
 */
@Setter
@Getter
@TableName(value = "dict")
public class Dict extends BaseEntity {

	@NotBlank
	private String code;

	@NotBlank
	private String name;

	private Integer orderByPriority;

}
