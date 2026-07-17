package com.example.cloudstoragemanagementsystem.controller;

import com.example.cloudstoragemanagementsystem.dto.ApiResponse;
import com.example.cloudstoragemanagementsystem.dto.UploadResponse;
import com.example.cloudstoragemanagementsystem.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadFile(
            @RequestParam("file") MultipartFile file) throws IOException {

        UploadResponse response = s3Service.uploadFile(file);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/files")
    public ResponseEntity<ApiResponse<Void>> deleteFile(
            @RequestParam String key
    ){
        s3Service.deleteFile(key);

        ApiResponse<Void> apiResponse = new ApiResponse<>("File deleted successfully.", null);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
