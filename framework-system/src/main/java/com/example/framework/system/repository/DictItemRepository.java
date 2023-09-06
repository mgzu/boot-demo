package com.example.framework.system.repository;

import com.example.framework.system.entity.DictItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictItemRepository extends JpaRepository<DictItem, String> {
}
