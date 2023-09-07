package com.example.framework.system;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author MaGuangZu
 * @since 2023-08-31
 */
@ComponentScan({
	"com.example.framework.system",
	"com.example.framework.web.configure.jpa",
	"com.example.framework.web.aspects"
})
@SpringBootApplication
public class TestApplication {
}
