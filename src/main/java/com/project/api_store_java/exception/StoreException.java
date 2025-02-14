package com.project.api_store_java.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class StoreException extends RuntimeException {
    private HttpStatus status;

    private String description;

    private List<String> reasons;

    public StoreException(ApiError error) {
        this.status = error.getStatus();
        this.description = error.getMessage();
    }

}
