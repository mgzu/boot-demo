package com.example.framework.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.framework.log.converters.LogRecordConverter;
import com.example.framework.log.mapper.LogRecordMapper;
import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import lombok.AllArgsConstructor;
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

	private final LogRecordMapper logRecordMapper;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void record(LogRecord logRecord) {
		logRecordMapper.insert(LogRecordConverter.INSTANCE.toDbLogRecord(logRecord));
	}

	@Override
	public List<LogRecord> queryLog(String bizNo, String type) {
		var logRecord = new com.example.framework.log.entity.LogRecord();
		logRecord.setBizNo(bizNo);
		logRecord.setType(type);
		var logRecordList = logRecordMapper.selectList(new QueryWrapper<>(logRecord));
		return LogRecordConverter.INSTANCE.toLogRecordList(logRecordList);
	}

	@Override
	public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
		var logRecord = new com.example.framework.log.entity.LogRecord();
		logRecord.setBizNo(bizNo);
		logRecord.setType(type);
		logRecord.setSubType(subType);
		var logRecordList = logRecordMapper.selectList(new QueryWrapper<>(logRecord));
		return LogRecordConverter.INSTANCE.toLogRecordList(logRecordList);
	}
}
