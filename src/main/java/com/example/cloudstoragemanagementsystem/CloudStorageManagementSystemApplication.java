package com.example.cloudstoragemanagementsystem;

import com.example.cloudstoragemanagementsystem.service.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudStorageManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudStorageManagementSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner run(S3Service s3Service) {
        return args -> {
            s3Service.listObjects();
        };
    }
}