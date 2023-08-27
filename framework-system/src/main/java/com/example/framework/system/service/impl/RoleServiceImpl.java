package com.example.framework.system.service.impl;

import com.example.framework.system.repository.RoleRepository;
import com.example.framework.system.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;

}
