package com.server.codewarriors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class CodeWarriorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeWarriorsApplication.class, args);
    }

}
