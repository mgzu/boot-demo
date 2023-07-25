package com.example;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MaGuangZu
 * @since 2021-09-27
 */
class ConvertTest {

    private final List<String> list = new ArrayList<>();

    private final int forNum = 1000000;

    @BeforeEach
    public void before() {
        for (int i = 0; i < forNum; i++) {
            list.add(RandomUtil.randomBigDecimal().toString());
        }
    }

    @Test
	void Test2() {
        for (int i = 0; i < forNum; i++) {
            new BigDecimal(list.get(i));
        }
    }

    @Test
	void Test1() {
        for (int i = 0; i < forNum; i++) {
            Convert.toBigDecimal(list.get(i));
        }
    }
}
