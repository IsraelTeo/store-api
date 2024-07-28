package com.store.api.dto;

import com.store.api.enums.ProductTypeEnum;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record ProductDTO(Long id,
                         @NotBlank(message = "Product name cannot be blank")
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

                         @NotNull(message = "Product name cannot be blank")
                         ProductTypeEnum productType) implements Serializable {
}
