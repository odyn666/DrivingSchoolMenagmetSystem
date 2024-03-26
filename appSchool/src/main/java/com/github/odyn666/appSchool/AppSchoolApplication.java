package com.github.odyn666.appSchool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppSchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppSchoolApplication.class, args);
    }

}
