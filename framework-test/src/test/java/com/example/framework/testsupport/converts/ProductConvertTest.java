package com.example.framework.testsupport.converts;

import com.example.framework.testsupport.entity.Product;
import com.google.common.collect.Lists;
import org.dromara.hutool.core.data.id.IdUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-07-26
 */
class ProductConvertTest {

	@Disabled("don't know mapstruct shadow copy")
	@Test
	void testShadowClone() {
		var product = Product.builder()
			.id(IdUtil.getSnowflakeNextIdStr())
			.name("product")
			.stores(Lists.newArrayList("store1", "store2"))
			.build();

		var copy = ProductConvert.INSTANCE.clone(product);

		assertThat(product.getId()).isEqualTo(copy.getId());
		assertThat(product.getName()).isEqualTo(copy.getName());
		assertThat(product.getStores().get(0)).isEqualTo(copy.getStores().get(0));
		assertThat(product.getStores().get(1)).isEqualTo(copy.getStores().get(1));

		copy.setId(IdUtil.getSnowflakeNextIdStr());
		copy.setName("new str");
		copy.getStores().set(0, "new store1");
		copy.getStores().set(1, "new store2");

		assertThat(product.getId()).isNotEqualTo(copy.getId());
		assertThat(product.getName()).isNotEqualTo(copy.getName());
		assertThat(product.getStores().get(0)).isEqualTo(copy.getStores().get(0));
		assertThat(product.getStores().get(1)).isEqualTo(copy.getStores().get(1));
	}

	@Test
	void testDeepClone() {
		var product = Product.builder()
			.id(IdUtil.getSnowflakeNextIdStr())
			.name("product")
			.stores(Lists.newArrayList("store1", "store2"))
			.build();

		var copy = ProductConvert.INSTANCE.deepClone(product);

		assertThat(product.getId()).isEqualTo(copy.getId());
		assertThat(product.getName()).isEqualTo(copy.getName());
		assertThat(product.getStores().get(0)).isEqualTo(copy.getStores().get(0));
		assertThat(product.getStores().get(1)).isEqualTo(copy.getStores().get(1));

		copy.setId(IdUtil.getSnowflakeNextIdStr());
		copy.setName("new str");
		copy.getStores().set(0, "new store1");
		copy.getStores().set(1, "new store2");

		assertThat(product.getId()).isNotEqualTo(copy.getId());
		assertThat(product.getName()).isNotEqualTo(copy.getName());
		assertThat(product.getStores().get(0)).isNotEqualTo(copy.getStores().get(0));
		assertThat(product.getStores().get(1)).isNotEqualTo(copy.getStores().get(1));
	}

}
