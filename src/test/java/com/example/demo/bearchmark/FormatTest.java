package com.example.demo.bearchmark;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MaGuangZu
 * @since 2021-09-22
 */
public class FormatTest {

    private static final Logger logger = LoggerFactory.getLogger(FormatTest.class);

    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            String format = String.format("%s%s", "a", "b");
        }
        long end = System.currentTimeMillis();
        logger.info("{} ms", (end - start) / 1000);
    }

    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            String format = "a" + "b";
        }
        long end = System.currentTimeMillis();
        logger.info("{} ms", (end - start) / 1000);
    }

    @Test
    public void test3() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            String format = StrUtil.format("{}{}", "a", "b");
        }
        long end = System.currentTimeMillis();
        logger.info("{} ms", (end - start) / 1000);
    }

}
