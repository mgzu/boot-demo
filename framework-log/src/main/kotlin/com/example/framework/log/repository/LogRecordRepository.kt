package com.example.framework.log.repository;

import com.example.framework.log.entity.LogRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LogRecordRepository : JpaRepository<LogRecord, String> {
}
