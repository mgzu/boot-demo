package com.example.log.service;

import com.example.log.entity.LogRecord;

/**
 * @author MaGuangZu
 * @since 2022-04-29
 */
public interface ILogRecordService {
    /**
     * 保存 log
     *
     * @param logRecord 日志实体
     */
    void record(LogRecord logRecord);
}
