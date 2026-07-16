package com.example.cloudstoragemanagementsystem.service;

import com.example.cloudstoragemanagementsystem.dto.UploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public List<String> listObjects() {
        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        ListObjectsV2Response response = s3Client.listObjectsV2(request);

        return response.contents()
                .stream()
                .map(object -> object.key())
                .toList();
    }

    public UploadResponse uploadFile(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();

        String uniqueFileName = "users/101/" + UUID.randomUUID() + "-" + fileName;

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(uniqueFileName)
                .build();

        s3Client.putObject(
                request,
                RequestBody.fromInputStream(
                        file.getInputStream(),
                        file.getSize()
                )
        );

        return new UploadResponse(
                "File uploaded successfully",
                file.getOriginalFilename(),
                uniqueFileName
        );
    }
}