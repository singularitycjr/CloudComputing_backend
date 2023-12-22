package com.example.cloudcomputing2023;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.cloudcomputing2023.mapper")
@Slf4j
public class CloudComputing2023Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudComputing2023Application.class, args);
        log.info("http://localhost:8080/doc.html");
    }

}
