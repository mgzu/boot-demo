package com.example.framework.common.util;

import com.example.framework.common.entity.Product;
import com.example.framework.common.options.BenchUpdateOptions;
import com.example.framework.testsupport.util.CsvUtil;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-07-28
 */
class BenchUpdateUtilTest {

	@Test
	void testByDefaultOptions() {
		List<Product> oldList = CsvUtil.readCsvList("csv/old.csv", Product.class);
		List<Product> newList = CsvUtil.readCsvList("csv/new.csv", Product.class);
		List<String> insertIdList = Lists.newArrayList();
		List<String> deleteIdList = Lists.newArrayList();
		List<String> updateIdList = Lists.newArrayList();
		benchUpdate(oldList, newList, insertIdList, deleteIdList, updateIdList, BenchUpdateOptions.defaults());
		assertThat(List.of("id1")).hasSameElementsAs(insertIdList);
		assertThat(List.of("id3")).hasSameElementsAs(deleteIdList);
		assertThat(List.of("id4")).hasSameElementsAs(updateIdList);
	}

	@Test
	void testByDefaultCustomOptions() {
		List<Product> oldList = CsvUtil.readCsvList("csv/old.csv", Product.class);
		List<Product> newList = CsvUtil.readCsvList("csv/new.csv", Product.class);
		List<String> insertIdList = Lists.newArrayList();
		List<String> deleteIdList = Lists.newArrayList();
		List<String> updateIdList = Lists.newArrayList();
		benchUpdate(oldList, newList, insertIdList, deleteIdList, updateIdList,
			BenchUpdateOptions.builder()
				.updateIgnoreEquals(false)
				.build()
		);
		assertThat(List.of("id1")).hasSameElementsAs(insertIdList);
		assertThat(List.of("id3")).hasSameElementsAs(deleteIdList);
		assertThat(List.of("id2", "id4")).hasSameElementsAs(updateIdList);
	}

	void benchUpdate(List<Product> oldList, List<Product> newList, List<String> insertIdList,
					 List<String> deleteIdList, List<String> updateIdList, BenchUpdateOptions options) {
		BenchUpdateUtil.benchUpdate(oldList, newList,
			Product::getId,
			(newProduct) -> insertIdList.add(newProduct.getId()),
			(oldProduct) -> deleteIdList.add(oldProduct.getId()),
			(oldProduct, newProduct) -> updateIdList.add(newProduct.getId()), options);
	}

}
