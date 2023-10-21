package com.example.framework.system.repository;

import com.example.framework.system.entity.Account;
import com.example.framework.web.configure.jpa.repositories.TenantRepository;

public interface AccountRepository extends TenantRepository<Account> {
}
