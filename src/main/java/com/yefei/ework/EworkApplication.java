package com.yefei.ework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yefei.ework.dao")
public class EworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(EworkApplication.class, args);
    }

}
