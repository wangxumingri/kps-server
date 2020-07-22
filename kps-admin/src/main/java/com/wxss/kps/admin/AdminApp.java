package com.wxss.kps.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wxss.kps")
@MapperScan(basePackages = "com.wxss.kps.**.dao")
public class AdminApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminApp.class,args);
    }
}

