package com.example.framework.testsupport;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author MaGuangZu
 * @since 2023-07-24
 */
class StreamTest {

	@Test
	void test1() {
		List<String> list = Stream.of("123", "456")
			.collect(Collectors.toList());
		list.add("789");
		assertThat(list).hasSize(3).isInstanceOf(ArrayList.class);
	}

	@Test
	void test2() {
		List<String> collect = Stream.of("123", "456").toList();
		//noinspection DataFlowIssue
		assertThatThrownBy(() -> collect.add("789")).isInstanceOf(UnsupportedOperationException.class);
		assertThat(collect.getClass().getName()).startsWith("java.util.ImmutableCollections$");
	}

}
