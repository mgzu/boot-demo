package com.example.framework.log.service.impl

import com.example.framework.log.mapstruct.LogRecordMapper
import com.example.framework.log.repository.LogRecordRepository
import com.mzt.logapi.beans.LogRecord
import com.mzt.logapi.service.ILogRecordService
import jakarta.annotation.Resource
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

/**
 * @author MaGuangZu
 * @since 2022-08-19
 */
@Service
open class DbLogRecordServiceImpl : ILogRecordService {
    @Resource
    private val logRecordRepository: LogRecordRepository? = null

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun record(logRecord: LogRecord) {
        logRecordRepository!!.save(LogRecordMapper.INSTANCE.toDbLogRecord(logRecord))
    }

    override fun queryLog(bizNo: String, type: String): List<LogRecord> {
		val logRecord = com.example.framework.log.entity.LogRecord()
		logRecord.bizNo = bizNo
		logRecord.type = type
		val logRecordList = logRecordRepository!!.findAll(Example.of(logRecord))
		return LogRecordMapper.INSTANCE.toLogRecordList(logRecordList)
	}

    override fun queryLogByBizNo(bizNo: String, type: String, subType: String): List<LogRecord> {
		val logRecord = com.example.framework.log.entity.LogRecord()
		logRecord.bizNo = bizNo
		logRecord.type = type
		logRecord.subType = subType
		val logRecordList = logRecordRepository!!.findAll(Example.of(logRecord))
		return LogRecordMapper.INSTANCE.toLogRecordList(logRecordList)
	}
}
