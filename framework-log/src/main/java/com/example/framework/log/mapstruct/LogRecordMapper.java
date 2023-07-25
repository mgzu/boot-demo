package com.example.framework.log.mapstruct;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.util.List;

/**
 * @author MaGuangZu
 * @since 2021-12-27
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface LogRecordMapper {

	LogRecordMapper INSTANCE = Mappers.getMapper(LogRecordMapper.class);

	com.example.framework.log.entity.LogRecord toDbLogRecord(com.mzt.logapi.beans.LogRecord logRecord);

	com.mzt.logapi.beans.LogRecord toLogRecord(com.example.framework.log.entity.LogRecord logRecord);

	List<com.mzt.logapi.beans.LogRecord> toLogRecordList(List<com.example.framework.log.entity.LogRecord> logRecordList);

	/**
	 * 解决 Serializable 与 String 互相映射的问题
	 *
	 * @param value 实现 Serializable 接口的对象
	 * @return 字符串
	 */
	default String map(Serializable value) {
		if (value == null) {
			return null;
		}
		return value.toString();
	}
}
