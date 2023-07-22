package com.example.framework.testsupport;

import java.lang.annotation.*;

/**
 * @author MaGuangZu
 * @since 2023-07-06
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CsvConverter {

	/**
	 * path
	 */
	String path();

	/**
	 * path
	 */
	Class<?> clazz();

}
