package com.example.framework.testsupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author MaGuangZu
 * @since 2023-07-24
 */
class StreamTest {

	@Test
	void test1() {
		List<String> collect = Stream.of("123", "456")
			.collect(Collectors.toList());
		collect.add("789");
		Assertions.assertEquals(3, collect.size());
	}

	@Test
	void test2() {
		List<String> collect = Stream.of("123", "456")
			.toList();
		Assertions.assertThrows(UnsupportedOperationException.class, () -> {
			collect.add("789");
		});
	}

}
