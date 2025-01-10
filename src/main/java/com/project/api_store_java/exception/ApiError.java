package com.project.api_store_java.exception;

import org.springframework.http.HttpStatus;

public enum ApiError {
    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND, "Customer noy found"),
    ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST, "Argument not valid"),
    CUSTOMER_ALREADY_EXISTS(HttpStatus.CONFLICT, "Employee already exists"),
    SALE_NOT_FOUND(HttpStatus.NOT_FOUND, "Sale not found"),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product not found"),
    CONVERSION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Conversion failed"),
    GET_ROLE_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Get role failed"),
    INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "Incorrect password"),
    INCORRECT_USERNAME(HttpStatus.UNAUTHORIZED, "Incorrect username"),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "Username not found"),
    BAD_CREDENTIALS(HttpStatus.BAD_REQUEST, "Bad credentials"), ROLE_NOT_FOUND(HttpStatus.NOT_FOUND, "Role not found"),
    ROLE_NOT_EXISTS(HttpStatus.NOT_FOUND, "Role not exists"),
    USER_CREATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "User creation failed"),
    JSON_PROCESSING_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Json processing failed"),;

    private final HttpStatus status;

    private final String message;

    ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
