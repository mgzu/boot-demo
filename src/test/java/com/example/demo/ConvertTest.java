package com.example.demo;

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
public class ConvertTest {

    private List<String> list = new ArrayList<>();

    private int forNum = 1000000;

    @BeforeEach
    public void before() {
        for (int i = 0; i < forNum; i++) {
            list.add(RandomUtil.randomBigDecimal().toString());
        }
    }

    @Test
    public void Test2() {
        for (int i = 0; i < forNum; i++) {
            new BigDecimal(list.get(i));
        }
    }

    @Test
    public void Test1() {
        for (int i = 0; i < forNum; i++) {
            Convert.toBigDecimal(list.get(i));
        }
    }
}
