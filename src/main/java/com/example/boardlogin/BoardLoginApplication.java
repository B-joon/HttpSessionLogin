package com.example.boardlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.example.boardlogin.config")
public class BoardLoginApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BoardLoginApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BoardLoginApplication.class);
    }

}
