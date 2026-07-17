package com.example.cloudstoragemanagementsystem.service;

import com.example.cloudstoragemanagementsystem.dto.UploadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class S3Service {

    private static final Logger logger = LoggerFactory.getLogger(S3Service.class);

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

        logger.info("Sending S3 list-objects request for bucket={}", bucketName);
        ListObjectsV2Response response = s3Client.listObjectsV2(request);

        logger.info(
                "Received S3 list-objects response for bucket={}: status={}, objectCount={}",
                bucketName,
                response.sdkHttpResponse().statusCode(),
                response.keyCount()
        );

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

        logger.info(
                "Sending S3 upload request for bucket={}, key={}, size={} bytes",
                bucketName,
                uniqueFileName,
                file.getSize()
        );
        PutObjectResponse response = s3Client.putObject(
                request,
                RequestBody.fromInputStream(
                        file.getInputStream(),
                        file.getSize()
                )
        );

        logger.info(
                "Received S3 upload response for key={}: status={}, eTag={}",
                uniqueFileName,
                response.sdkHttpResponse().statusCode(),
                response.eTag()
        );

        return new UploadResponse(
                "File uploaded successfully",
                file.getOriginalFilename(),
                uniqueFileName
        );
    }

    public void deleteFile(String key) {

        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        logger.info("Sending S3 delete request for bucket={}, key={}", bucketName, key);
        DeleteObjectResponse response = s3Client.deleteObject(request);

        logger.info(
                "Received S3 delete response for key={}: status={}",
                key,
                response.sdkHttpResponse().statusCode()
        );
    }
}
