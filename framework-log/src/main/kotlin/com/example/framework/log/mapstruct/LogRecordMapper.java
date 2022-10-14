package com.example.framework.log.mapstruct;

import com.example.framework.log.entity.LogRecord;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * @author MaGuangZu
 * @since 2021-12-27
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogRecordMapper {

    LogRecordMapper INSTANCE = Mappers.getMapper(LogRecordMapper.class);

    com.example.framework.log.entity.LogRecord toDbLogRecord(com.mzt.logapi.beans.LogRecord logRecord);

    com.mzt.logapi.beans.LogRecord toLogRecord(com.example.framework.log.entity.LogRecord logRecord);
}
