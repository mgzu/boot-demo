package com.example.app.waybill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = "com.example.**.entity.**")
@ComponentScan(basePackages = "com.example")
@SpringBootApplication
public class WaybillApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaybillApplication.class, args);
    }

}
