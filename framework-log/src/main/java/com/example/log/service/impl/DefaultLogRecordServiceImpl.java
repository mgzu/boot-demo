package com.example.log.service.impl;

import com.example.log.entity.LogRecord;
import com.example.log.service.ILogRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MaGuangZu
 * @since 2022-04-29
 */
@Slf4j
public class DefaultLogRecordServiceImpl implements ILogRecordService {
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void record(LogRecord logRecord) {
        log.info("【logRecord】log={}", logRecord);
    }
}
