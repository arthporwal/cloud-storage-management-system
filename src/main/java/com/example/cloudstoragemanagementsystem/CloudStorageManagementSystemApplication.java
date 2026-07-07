package com.example.cloudstoragemanagementsystem;

import com.example.cloudstoragemanagementsystem.service.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CloudStorageManagementSystemApplication {

 // ssh -i ~/Downloads/aws-learning-key.pem ubuntu@<ip>
    public static void main(String[] args) {
        SpringApplication.run(CloudStorageManagementSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner run(S3Service s3Service) {
        return args -> {

            List<String> files = s3Service.listObjects();

            for (String file : files) {
                System.out.println(file);
            }
        };
    }
}