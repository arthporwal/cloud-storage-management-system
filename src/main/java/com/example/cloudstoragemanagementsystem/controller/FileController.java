package com.example.cloudstoragemanagementsystem.controller;

import com.example.cloudstoragemanagementsystem.service.S3Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FileController {

    private final S3Service s3Service;

    public FileController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/files")
    public List<String> listFiles() {
        return s3Service.listObjects();
    }
}