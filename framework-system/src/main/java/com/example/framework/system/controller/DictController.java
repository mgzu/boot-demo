package com.example.framework.system.controller;

import com.example.framework.system.converters.DictConverter;
import com.example.framework.system.entity.Dict;
import com.example.framework.system.entity.dto.DictDto;
import com.example.framework.system.service.DictService;
import com.example.framework.web.controller.BaseController;
import com.example.framework.web.entity.Result;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MaGuangZu
 * @since 2021-09-27
 */
@AllArgsConstructor
@RequestMapping("dict")
@RestController
public class DictController extends BaseController {

	private final DictService dictService;

	@PostMapping
	public Result<Dict> save(@Validated @RequestBody DictDto dictDto) {
		Dict dict = DictConverter.INSTANCE.toDict(dictDto);
		return Result.ok(dictService.save(dict));
	}

}
