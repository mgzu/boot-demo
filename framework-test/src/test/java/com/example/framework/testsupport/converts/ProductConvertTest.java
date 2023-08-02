package com.example.framework.testsupport.converts;

import cn.hutool.core.util.IdUtil;
import com.example.framework.testsupport.entity.Product;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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

		Assertions.assertEquals(product.getId(), copy.getId());
		Assertions.assertEquals(product.getName(), copy.getName());
		Assertions.assertEquals(product.getStores().get(0), copy.getStores().get(0));
		Assertions.assertEquals(product.getStores().get(1), copy.getStores().get(1));

		copy.setId(IdUtil.getSnowflakeNextIdStr());
		copy.setName("new str");
		copy.getStores().set(0, "new store1");
		copy.getStores().set(1, "new store2");

		Assertions.assertNotEquals(product.getId(), copy.getId());
		Assertions.assertNotEquals(product.getName(), copy.getName());
		Assertions.assertEquals(product.getStores().get(0), copy.getStores().get(0));
		Assertions.assertEquals(product.getStores().get(1), copy.getStores().get(1));
	}

	@Test
	void testDeepClone() {
		var product = Product.builder()
			.id(IdUtil.getSnowflakeNextIdStr())
			.name("product")
			.stores(Lists.newArrayList("store1", "store2"))
			.build();

		var copy = ProductConvert.INSTANCE.deepClone(product);

		Assertions.assertEquals(product.getId(), copy.getId());
		Assertions.assertEquals(product.getName(), copy.getName());
		Assertions.assertEquals(product.getStores().get(0), copy.getStores().get(0));
		Assertions.assertEquals(product.getStores().get(1), copy.getStores().get(1));

		copy.setId(IdUtil.getSnowflakeNextIdStr());
		copy.setName("new str");
		copy.getStores().set(0, "new store1");
		copy.getStores().set(1, "new store2");

		Assertions.assertNotEquals(product.getId(), copy.getId());
		Assertions.assertNotEquals(product.getName(), copy.getName());
		Assertions.assertNotEquals(product.getStores().get(0), copy.getStores().get(0));
		Assertions.assertNotEquals(product.getStores().get(1), copy.getStores().get(1));
	}

}
