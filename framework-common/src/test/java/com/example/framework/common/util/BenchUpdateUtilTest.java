package com.example.framework.common.util;

import com.example.framework.common.entity.Product;
import com.example.framework.common.options.BenchUpdateOptions;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author MaGuangZu
 * @since 2023-07-28
 */
class BenchUpdateUtilTest {

	@Test
	void test1() {
		List<Product> oldList = Lists.newArrayList();
		oldList.add(Product.builder().id("id2")
			.name("1")
			.build());
		oldList.add(Product.builder().id("id3")
			.name("123")
			.build());
		oldList.add(Product.builder().id("id4")
			.name("1")
			.build());
		List<Product> newList = Lists.newArrayList();
		newList.add(Product.builder().id("id1")
			.name("123")
			.build());
		newList.add(Product.builder().id("id2")
			.name("1")
			.build());
		newList.add(Product.builder().id("id4")
			.name("12")
			.build());
		List<String> insertIdList = Lists.newArrayList();
		List<String> deleteIdList = Lists.newArrayList();
		List<String> updateIdList = Lists.newArrayList();
		benchUpdate(oldList, newList, insertIdList, deleteIdList, updateIdList, BenchUpdateOptions.defaults());
		assertEquals(List.of("id1"), insertIdList);
		assertEquals(List.of("id3"), deleteIdList);
		assertEquals(List.of("id2", "id4"), updateIdList);
	}

	@Test
	void test2() {
		List<Product> oldList = Lists.newArrayList();
		oldList.add(Product.builder().id("id2")
			.name("1")
			.build());
		oldList.add(Product.builder().id("id3")
			.name("123")
			.build());
		oldList.add(Product.builder().id("id4")
			.name("1")
			.build());
		List<Product> newList = Lists.newArrayList();
		newList.add(Product.builder().id("id1")
			.name("123")
			.build());
		newList.add(Product.builder().id("id2")
			.name("1")
			.build());
		newList.add(Product.builder().id("id4")
			.name("12")
			.build());
		List<String> insertIdList = Lists.newArrayList();
		List<String> deleteIdList = Lists.newArrayList();
		List<String> updateIdList = Lists.newArrayList();
		benchUpdate(oldList, newList, insertIdList, deleteIdList, updateIdList,
			BenchUpdateOptions.builder()
				.updateIgnoreEquals(true)
				.build()
		);
		assertEquals(List.of("id1"), insertIdList);
		assertEquals(List.of("id3"), deleteIdList);
		assertEquals(List.of("id4"), updateIdList);
	}

	void benchUpdate(List<Product> oldList, List<Product> newList, List<String> insertIdList,
					 List<String> deleteIdList, List<String> updateIdList, BenchUpdateOptions options) {
		BenchUpdateUtil.benchUpdate(oldList, newList,
			Product::getId, (newProduct) -> {
				insertIdList.add(newProduct.getId());
			}, (oldProduct) -> {
				deleteIdList.add(oldProduct.getId());
			}, (oldProduct, newProduct) -> {
				updateIdList.add(newProduct.getId());
			}, options);
	}

	private void assertEquals(List<String> list1, List<String> list2) {
		Assertions.assertEquals(list1.size(), list2.size());
		for (int i = 0; i < list1.size(); i++) {
			Assertions.assertEquals(list1.get(i), list2.get(i));
		}
	}

}
