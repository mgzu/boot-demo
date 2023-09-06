package com.example.framework.system.repository;

import com.example.framework.system.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, String> {
}
