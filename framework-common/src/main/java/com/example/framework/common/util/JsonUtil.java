package com.example.framework.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.dromara.hutool.core.date.DateFormatPool;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class JsonUtil {

	@Getter
	private static final ObjectMapper objectMapper = new ObjectMapper();

	static {
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateFormatPool.NORM_DATETIME_PATTERN)));

		objectMapper.findAndRegisterModules();
		objectMapper.registerModule(simpleModule);
		objectMapper.setDateFormat(new SimpleDateFormat(DateFormatPool.NORM_DATETIME_PATTERN));
	}

	@SneakyThrows
	public static <T> T readValue(String json, Class<T> clazz) {
		return objectMapper.readValue(json, clazz);
	}

	@SneakyThrows
	public static String toJson(Object obj) {
		return objectMapper.writeValueAsString(obj);
	}

}
