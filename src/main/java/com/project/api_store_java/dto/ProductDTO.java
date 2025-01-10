package com.project.api_store_java.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ProductDTO(Long id,

                         @NotBlank(message = "Product name is required.")
                         @Size(max = 50, message = "Product name must be at most 50 characters.")
                         String productName,

                         @NotBlank(message = "Product description is required.")
                         @Size(max = 500, message = "Product description must be at most 500 characters.")
                         String productDescription,

                         @NotNull(message = "Product price is required.")
                         @Min(value = 0, message = "Product price must be a positive number.")
                         Double productPrice,
                         @NotNull(message = "Product price is required.")

                         @Min(value = 0, message = "Product stock must be a positive number.")
                         Integer stock) {
}
