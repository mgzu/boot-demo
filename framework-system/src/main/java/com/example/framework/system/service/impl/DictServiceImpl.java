package com.example.framework.system.service.impl;

import com.example.framework.system.entity.Dict;
import com.example.framework.system.mapper.DictMapper;
import com.example.framework.system.service.DictService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DictServiceImpl implements DictService {

	private final DictMapper dictMapper;

	@Override
	public Dict save(Dict dict) {
		dictMapper.insert(dict);
		return dict;
	}

}
