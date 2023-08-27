package com.example.framework.system.service.impl;

import com.example.framework.system.repository.RolePermissionRepository;
import com.example.framework.system.service.RolePermissionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

	private final RolePermissionRepository rolePermissionRepository;

}
