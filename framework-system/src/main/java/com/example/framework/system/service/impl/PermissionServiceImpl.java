package com.example.framework.system.service.impl;

import com.example.framework.system.repository.PermissionRepository;
import com.example.framework.system.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PermissionServiceImpl implements RoleService {

	private final PermissionRepository permissionRepository;

}
