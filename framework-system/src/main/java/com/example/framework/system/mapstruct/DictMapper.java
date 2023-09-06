package com.example.framework.system.mapstruct;

import com.example.framework.system.entity.Dict;
import com.example.framework.system.entity.dto.DictDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author MaGuangZu
 * @since 2023-09-06
 */
@Mapper(
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	builder = @Builder(disableBuilder = true)
)
public interface DictMapper {

	DictMapper INSTANCE = Mappers.getMapper(DictMapper.class);

	Dict toDict(DictDto dictDto);

}
