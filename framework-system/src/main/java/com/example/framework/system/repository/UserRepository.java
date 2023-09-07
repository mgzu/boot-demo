package com.example.framework.system.repository;

import com.example.framework.system.entity.User;
import com.example.framework.web.configure.jpa.repositories.TenantRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends TenantRepository<User> {
}
