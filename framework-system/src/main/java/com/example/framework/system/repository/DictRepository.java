package com.example.framework.system.repository;

import com.example.framework.system.entity.Dict;
import com.example.framework.web.configure.jpa.repositories.TenantRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictRepository extends TenantRepository<Dict> {
}
