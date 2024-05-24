package com.example.app.order;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableLogRecord(tenant = "temp")
@EnableTransactionManagement
@MapperScan(basePackages = {"com.example.**.mapper"})
@SpringBootApplication(scanBasePackages = "com.example")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
