package com.example.framework.system.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DictVo {

	private String code;

	private String name;

	private List<DictItemVo> items;

}
