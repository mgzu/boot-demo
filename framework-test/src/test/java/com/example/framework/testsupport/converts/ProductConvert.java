package com.example.framework.testsupport.converts;

import com.example.framework.testsupport.entity.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.control.MappingControl;
import org.mapstruct.factory.Mappers;

/**
 * @author MaGuangZu
 * @since 2023-07-26
 */
@Mapper(
	builder = @Builder(disableBuilder = true),
	mappingControl = DeepClone.class
)
public interface ProductConvert {

	ProductConvert INSTANCE = Mappers.getMapper(ProductConvert.class);

	@SuppressWarnings("DefaultAnnotationParam")
	@BeanMapping(mappingControl = MappingControl.class)
	Product clone(Product product);

	Product deepClone(Product product);

}
