package com.example.framework.log.repository;

import com.example.framework.log.entity.LogRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRecordRepository extends JpaRepository<LogRecord, String> {
}
