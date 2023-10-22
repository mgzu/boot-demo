package com.example.framework.starter.security

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@ComponentScan(basePackages = ["com.example"])
@EntityScan(basePackages = ["com.example"])
@EnableJpaRepositories(basePackages = ["com.example"])
@SpringBootApplication
open class SecurityApplication

fun main(args: Array<String>) {
	SpringApplication.run(SecurityApplication::class.java, *args)
}
