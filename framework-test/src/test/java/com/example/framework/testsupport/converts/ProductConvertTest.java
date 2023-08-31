package com.example.framework.testsupport.converts;

import com.example.framework.testsupport.entity.Product;
import com.example.framework.testsupport.entity.Stock;
import com.google.common.collect.Lists;
import org.dromara.hutool.core.data.id.IdUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-07-26
 */
class ProductConvertTest {

	@MethodSource("getProduct")
	@ParameterizedTest
	void shadowCloneTest(Product product) {
		var copy = ProductConvert.INSTANCE.clone(product);

		assertThat(product).isEqualTo(copy);

		copy.setId(IdUtil.getSnowflakeNextIdStr());
		copy.setName("new str");
		copy.getStocks().get(0).setName("new stock1");
		copy.getStocks().get(1).setName("new stock2");

		assertThat(product.getId()).isNotEqualTo(copy.getId());
		assertThat(product.getName()).isNotEqualTo(copy.getName());
		assertThat(product.getStocks()).isEqualTo(copy.getStocks());
	}

	@MethodSource("getProduct")
	@ParameterizedTest
	void deepCloneTest(Product product) {
		var copy = ProductConvert.INSTANCE.deepClone(product);

		assertThat(product).isEqualTo(copy);

		copy.setId(IdUtil.getSnowflakeNextIdStr());
		copy.setName("new str");
		copy.getStocks().get(0).setName("new stock1");
		copy.getStocks().get(1).setName("new stock2");

		assertThat(product.getId()).isNotEqualTo(copy.getId());
		assertThat(product.getName()).isNotEqualTo(copy.getName());
		assertThat(product.getStocks()).isNotEqualTo(copy.getStocks());
	}

	static List<Product> getProduct() {
		return List.of(Product.builder()
			.id(IdUtil.getSnowflakeNextIdStr())
			.name("product")
			.stocks(Lists.newArrayList(
				Stock.builder().id("1").name("stock1").build(),
				Stock.builder().id("2").name("stock2").build()
			))
			.build());
	}

}
