package com.example.framework.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.framework.log.entity.LogRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogRecordMapper extends BaseMapper<LogRecord> {

}
