package com.example;

import com.example.framework.testsupport.BaseCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author MaGuangZu
 * @since 2021-12-22
 */
@Slf4j
class StringTest extends BaseCase {

	@Test
	void trimWhiteSpaceTest() {
		Assertions.assertTrue(Character.isWhitespace(' '));
		Assertions.assertFalse(Character.isWhitespace(' '));
	}

}
