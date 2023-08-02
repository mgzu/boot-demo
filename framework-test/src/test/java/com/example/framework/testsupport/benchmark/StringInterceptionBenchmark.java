package com.example.framework.testsupport.benchmark;

import lombok.extern.slf4j.Slf4j;
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
public class StringInterceptionBenchmark extends JmhBaseCase {

	public static void main(String[] args) throws RunnerException, IOException {
		getRunner(MethodHandles.lookup().lookupClass().getSimpleName()).run();
	}

	String boxNo = "15GLLWZ1LU002";

	@Benchmark
	public void test1(Blackhole bh) {
		bh.consume(boxNo.replaceAll("[A-Z0-9]+U(0*)", ""));
	}

	@Benchmark
	public void test2(Blackhole bh) {
		bh.consume(boxNo.split("U")[0]);
	}

	@Benchmark
	public void test3(Blackhole bh) {
		bh.consume(boxNo.substring(boxNo.lastIndexOf("U") + 1));
	}

}
