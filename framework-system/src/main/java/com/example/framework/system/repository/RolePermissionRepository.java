package com.example.framework.system.repository;

import com.example.framework.system.entity.RolePermission;
import com.example.framework.web.configure.jpa.repositories.TenantRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends TenantRepository<RolePermission> {
}
