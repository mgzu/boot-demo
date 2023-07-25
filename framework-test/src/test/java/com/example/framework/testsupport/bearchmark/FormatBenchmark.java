package com.example.framework.testsupport.bearchmark;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * @author MaGuangZu
 * @since 2021-09-22
 */
@Slf4j
@Fork(3)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class FormatBenchmark {

	private static final int FOR_I = 10000000;

	@Benchmark
	public void test1(Blackhole bh) {
		for (int i = 0; i < FOR_I; i++) {
			String format = String.format("%s%s", "a", "b");
			bh.consume(format);
		}
	}

	@Benchmark
	public void test2(Blackhole bh) {
		for (int i = 0; i < FOR_I; i++) {
			String format = "a" + "b";
			bh.consume(format);
		}
	}

	@Benchmark
	public void test3(Blackhole bh) {
		for (int i = 0; i < FOR_I; i++) {
			String format = StrUtil.format("{}{}", "a", "b");
			bh.consume(format);
		}
	}

}
