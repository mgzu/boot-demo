package com.example.framework.testsupport.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.io.resource.ResourceUtil;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author MaGuangZu
 * @since 2023-09-27
 */
@Slf4j
@UtilityClass
public class CsvUtil {

	private static final CsvMapper csvMapper = CsvMapper.builder()
		.enable(CsvParser.Feature.SKIP_EMPTY_LINES)
		.build();

	public static <T> List<T> readCsvList(String path, Class<T> clazz) {
		return readCsvList(path, clazz, true);
	}

	public static <T> List<T> readCsvList(String path, Class<T> clazz, boolean withColumnReordering) {
		try {
			var schema = csvMapper.typedSchemaFor(clazz)
				.withHeader()
				.withColumnReordering(withColumnReordering);

			MappingIterator<T> iterator = csvMapper
				.readerWithTypedSchemaFor(clazz)
				.with(schema)
				.readValues(ResourceUtil.getReader(path, StandardCharsets.UTF_8));
			return iterator.readAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
