package com.example.framework.testsupport.benchmark;

import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.TimeUnit;

/**
 * @author MaGuangZu
 * @since 2021-09-22
 */
@Slf4j
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class FormatBenchmark extends JmhBaseCase {

	public static void main(String[] args) throws RunnerException, IOException {
		getRunner(MethodHandles.lookup().lookupClass().getSimpleName()).run();
	}

	@Benchmark
	public void test1(Blackhole bh) {
		String format = String.format("%s%s%s", "a", "b", "c");
		bh.consume(format);
	}

	@Benchmark
	public void test2(Blackhole bh) {
		String format = "a" + "b" + "c";
		bh.consume(format);
	}

	@Benchmark
	public void test3(Blackhole bh) {
		String format = StrUtil.format("{}{}{}", "a", "b", "c");
		bh.consume(format);
	}

}
