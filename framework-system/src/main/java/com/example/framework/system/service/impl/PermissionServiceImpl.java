package com.example.framework.system.service.impl;

import com.example.framework.system.mapper.PermissionMapper;
import com.example.framework.system.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PermissionServiceImpl implements RoleService {

	private final PermissionMapper permissionMapper;

}
