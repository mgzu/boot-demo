package com.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author MaGuangZu
 * @since 2021-12-22
 */
@Slf4j
class StringTest extends BaseTest {

    @Test
    void trimWhiteSpaceTest() {
        log.info("test begin");
        log.info("{}", Character.isWhitespace(' '));
        log.info("{}", "         abc        ".strip());
        log.info("test end");
    }

}
