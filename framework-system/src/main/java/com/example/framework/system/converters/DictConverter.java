package com.example.framework.system.converters;

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
public interface DictConverter {

	DictConverter INSTANCE = Mappers.getMapper(DictConverter.class);

	Dict toDict(DictDto dictDto);

}
