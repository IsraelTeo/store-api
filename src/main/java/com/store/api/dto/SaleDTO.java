package com.store.api.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.List;

@Builder
public record SaleDTO(Long id,
                      @Positive(message = "Total amount must be greater than zero")
                      @DecimalMin(value = "0.01", message = "Total amount must be at least 0.01")
                      @Digits(integer = 10, fraction = 2,
                              message = "Total amount must be a valid monetary amount with up to 10 integer digits and 2 fractional digits")
                      double totalAmount,

                      @NotEmpty(message = "Products list cannot be empty")
                      List<ProductDTO> productsList,

                      @NotNull(message = "Customer cannot be null")
                      CustomerDTO customer) {
}
