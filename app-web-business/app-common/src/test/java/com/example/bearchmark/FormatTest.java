package com.example.bearchmark;

import cn.hutool.core.util.StrUtil;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author MaGuangZu
 * @since 2021-09-22
 */
@Slf4j
public class FormatTest {

    private static final int FOR_I = 10000000;

    @Test
    public void test1() {
        for (int i = 0; i < FOR_I; i++) {
            String format = String.format("%s%s", "a", "b");
        }
    }

    @Test
    public void test2() {
        for (int i = 0; i < FOR_I; i++) {
            String format = "a" + "b";
        }
    }

    @Test
    public void test3() {
        for (int i = 0; i < FOR_I; i++) {
            String format = StrUtil.format("{}{}", "a", "b");
        }
    }

}
