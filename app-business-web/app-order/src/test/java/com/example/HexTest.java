package com.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author MaGuangZu
 * @since 2021-12-27
 */
@Slf4j
public class HexTest {

    @Test
    public void testHex() {
        long time = new Date().getTime();
//        1640600092347
//        fb62bebb
//        17dfb62bebb
//        1640600114651
        log.info(time + "");
        log.info(Integer.MAX_VALUE + "");
        log.info("FBA" + Integer.toHexString((int) time).toUpperCase());
        log.info(Long.MAX_VALUE + "");
        log.info(Long.toHexString((int) time));
    }
}
