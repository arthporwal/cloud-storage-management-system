package com.example.cloudstoragemanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UploadResponse {

    private String message;
    private String originalFileName;
    private String storedFileName;

    public UploadResponse() {
    }

    public UploadResponse(String message, String originalFileName, String storedFileName) {
        this.message = message;
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
    }

}