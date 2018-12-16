package com.bookshelf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tinkoff.test")
@EntityScan(basePackages = "com.tinkoff.test.entity")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(new Class[] {Application.class}, args);
    }


}
