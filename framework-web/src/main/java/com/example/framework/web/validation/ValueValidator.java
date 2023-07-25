package com.example.framework.web.validation;

/**
 * @author MaGuangZu
 * @since 2023-07-25
 */
public interface ValueValidator {

	default boolean isValid(String value) {
		return false;
	}

}
