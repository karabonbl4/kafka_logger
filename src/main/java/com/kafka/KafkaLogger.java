package com.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class KafkaLogger extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(KafkaLogger.class, args);
    }
}
