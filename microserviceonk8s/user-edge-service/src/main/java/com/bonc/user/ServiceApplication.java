package com.bonc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServiceApplication.class);
        application.run(args);
    }
}
