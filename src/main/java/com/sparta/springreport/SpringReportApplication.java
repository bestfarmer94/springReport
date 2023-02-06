package com.sparta.springreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringReportApplication.class, args);
    }

}
