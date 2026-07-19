package com.example.cloudstoragemanagementsystem.exception;


public class ResourceNotFoundException
        extends RuntimeException {

    public ResourceNotFoundException(
            String message) {

        super(message);
    }
}
