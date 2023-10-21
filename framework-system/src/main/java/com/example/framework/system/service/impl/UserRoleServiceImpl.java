package com.example.framework.system.service.impl;

import com.example.framework.system.repository.AccountRoleRepository;
import com.example.framework.system.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserRoleServiceImpl implements UserRoleService {

	private final AccountRoleRepository accountRoleRepository;

}
