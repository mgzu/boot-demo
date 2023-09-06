package com.example.framework.system.service.impl;

import com.example.framework.system.entity.Dict;
import com.example.framework.system.repository.DictRepository;
import com.example.framework.system.service.DictService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DictServiceImpl implements DictService {

	private final DictRepository dictRepository;

	@Override
	public Dict save(Dict dict) {
		return dictRepository.save(dict);
	}

}
