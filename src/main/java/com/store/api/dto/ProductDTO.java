package com.store.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record ProductDTO(Long productId,
                         @NotBlank(message = "The field cannot be empty")
                         String productName,
                         @NotBlank(message = "The field cannot be empty")
                         String productDescription,
                         @Size(min = 0, max = 6)
                         double productPrice) implements Serializable {
}
