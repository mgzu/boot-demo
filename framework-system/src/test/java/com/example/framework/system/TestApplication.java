package com.example.framework.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author MaGuangZu
 * @since 2023-08-31
 */
@ComponentScan({
	"com.example.framework.system",
	"com.example.framework.web.configure.mybatisplus",
	"com.example.framework.web.properties"
})
@MapperScan(basePackages = {
	"com.example.framework.system.mapper"
})
@SpringBootApplication
public class TestApplication {
}
