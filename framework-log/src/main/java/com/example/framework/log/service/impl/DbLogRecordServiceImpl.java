package com.example.framework.log.service.impl;

import com.example.framework.log.mapstruct.LogRecordMapper;
import com.example.framework.log.repository.LogRecordRepository;
import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author MaGuangZu
 * @since 2022-08-19
 */
@AllArgsConstructor
@Service
public class DbLogRecordServiceImpl implements ILogRecordService {

	private final LogRecordRepository logRecordRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void record(LogRecord logRecord) {
		logRecordRepository.save(LogRecordMapper.INSTANCE.toDbLogRecord(logRecord));
	}

	@Override
	public List<LogRecord> queryLog(String bizNo, String type) {
		var logRecord = new com.example.framework.log.entity.LogRecord();
		logRecord.setBizNo(bizNo);
		logRecord.setType(type);
		var logRecordList = logRecordRepository.findAll(Example.of(logRecord));
		return LogRecordMapper.INSTANCE.toLogRecordList(logRecordList);
	}

	@Override
	public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
		var logRecord = new com.example.framework.log.entity.LogRecord();
		logRecord.setBizNo(bizNo);
		logRecord.setType(type);
		logRecord.setSubType(subType);
		var logRecordList = logRecordRepository.findAll(Example.of(logRecord));
		return LogRecordMapper.INSTANCE.toLogRecordList(logRecordList);
	}
}
