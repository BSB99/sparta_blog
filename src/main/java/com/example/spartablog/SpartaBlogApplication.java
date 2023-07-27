package com.example.spartablog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class SpartaBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartaBlogApplication.class, args);
    }

}