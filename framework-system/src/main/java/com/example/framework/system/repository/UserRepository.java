package com.example.framework.system.repository;

import com.example.framework.system.entity.User;
import com.example.framework.web.configure.jpa.repositories.TenantRepository;

public interface UserRepository extends TenantRepository<User> {
}
