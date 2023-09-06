package com.example.framework.system.repository;

import com.example.framework.system.entity.Dict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictRepository extends JpaRepository<Dict, String> {
}
