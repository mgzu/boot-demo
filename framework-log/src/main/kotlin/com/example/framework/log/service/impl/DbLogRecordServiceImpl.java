package com.example.framework.log.service.impl;

import com.example.framework.log.mapstruct.LogRecordMapper;
import com.example.framework.log.repository.LogRecordRepository;
import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author MaGuangZu
 * @since 2022-08-19
 */
@Service
public class DbLogRecordServiceImpl implements ILogRecordService {

    @Resource
    private LogRecordRepository logRecordRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void record(LogRecord logRecord) {
        logRecordRepository.save(LogRecordMapper.INSTANCE.toDbLogRecord(logRecord));
    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        com.example.framework.log.entity.LogRecord logRecord = new com.example.framework.log.entity.LogRecord();
        logRecord.setBizNo(bizNo);
        logRecord.setType(type);
        return logRecordRepository.findAll(Example.of(logRecord))
                .stream().map(LogRecordMapper.INSTANCE::toLogRecord).toList();
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        com.example.framework.log.entity.LogRecord logRecord = new com.example.framework.log.entity.LogRecord();
        logRecord.setBizNo(bizNo);
        logRecord.setType(type);
        logRecord.setSubType(subType);
        return logRecordRepository.findAll(Example.of(logRecord))
                .stream().map(LogRecordMapper.INSTANCE::toLogRecord).toList();
    }
}
