package com.example.framework.system.service.impl;

import com.example.framework.system.repository.UserRoleRepository;
import com.example.framework.system.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserRoleServiceImpl implements UserRoleService {

	private final UserRoleRepository userRoleRepository;

}
