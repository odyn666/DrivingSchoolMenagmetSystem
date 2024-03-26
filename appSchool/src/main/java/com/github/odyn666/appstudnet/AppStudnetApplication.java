package com.github.odyn666.appstudnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppStudnetApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppStudnetApplication.class, args);
    }

}
