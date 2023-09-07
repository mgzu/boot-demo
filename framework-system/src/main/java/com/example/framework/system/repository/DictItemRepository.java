package com.example.framework.system.repository;

import com.example.framework.system.entity.DictItem;
import com.example.framework.web.configure.jpa.repositories.TenantRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictItemRepository extends TenantRepository<DictItem> {

	@Override
	List<DictItem> findAll();

}
