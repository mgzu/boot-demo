package com.example.framework.testsupport.benchmark;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author MaGuangZu
 * @since 2023-07-31
 */
public class JmhBaseCase {

	public static Runner getRunner(String className) throws IOException {
		String parentDictionary = "framework-test/src/test/jmh/";
		Path dir = Path.of(parentDictionary);
		if (!Files.exists(dir)) {
			Files.createDirectories(dir);
		}
		return new Runner(new OptionsBuilder()
			.include(className)
			.resultFormat(ResultFormatType.TEXT)
			.result(parentDictionary + className + "_result.txt")
			.build());
	}

}
