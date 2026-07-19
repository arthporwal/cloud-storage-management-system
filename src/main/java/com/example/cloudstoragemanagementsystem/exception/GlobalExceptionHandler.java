package com.example.cloudstoragemanagementsystem.exception;

import com.example.cloudstoragemanagementsystem.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        logger.error("Unhandled exception occurred", ex);

        ApiResponse<Void> apiResponse = new ApiResponse<>("An unexpected error occurred.", null);
        return ResponseEntity
                .internalServerError()
                .body(apiResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(ResourceNotFoundException ex) {

        ApiResponse<Void> response =
                new ApiResponse<>(ex.getMessage(), null);

        logger.warn("Resource not found: {}", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}
