package com.store.api.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductDTO(@NotBlank(message = "Product name cannot be blank")
                         @Size(max = 70, message = "Product name cannot exceed 50 characters")
                         String productName,

                         @NotBlank(message = "Product description cannot be blank")
                         @Size(max = 500, message = "Product description cannot exceed 500 characters")
                         String productDescription,

                         @Positive(message = "Product price must be greater than zero")
                         @Digits(integer = 10, fraction = 2,
                                 message = "Product price must be a valid monetary amount with up to 10 integer digits and 2 fractional digits")
                         @DecimalMin(value = "0.01", message = "Total amount must be at least 0.01")
                         double productPrice,

                         @NotEmpty(message = "Sales list cannot be empty")
                         List<SaleDTO> salesList) {
}
