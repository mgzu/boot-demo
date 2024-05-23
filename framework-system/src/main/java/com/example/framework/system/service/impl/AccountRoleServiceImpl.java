package com.example.framework.system.service.impl;

import com.example.framework.system.mapper.AccountRoleMapper;
import com.example.framework.system.service.AccountRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccountRoleServiceImpl implements AccountRoleService {

	private final AccountRoleMapper accountRoleMapper;

}
