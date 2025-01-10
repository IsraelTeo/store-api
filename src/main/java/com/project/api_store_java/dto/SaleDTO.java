package com.project.api_store_java.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record SaleDTO(
        Long id,

        @NotNull(message = "Total amount is required.")
        @Min(value = 0, message = "Total amount must be a positive number.")
        Double totalAmount,

        @NotEmpty(message = "The product IDs list cannot be empty.")
        List<@NotNull(message = "Product ID cannot be null.") @Min(value = 1, message = "Product ID must be a positive number.") Long> productIds,

        @NotNull(message = "Customer ID is required.")
        @Min(value = 1, message = "Customer ID must be a positive number.")
        Long customerId
) {
}