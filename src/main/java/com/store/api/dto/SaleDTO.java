package com.store.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

public record SaleDTO (Long saleIdDTO,
                       @NotBlank(message = "The field cannot be empty")
                       @Min(0)
                       @Size(min = 0, max = 6)
                       double totalAmount,
                       @NotBlank(message = "The field cannot be empty")
                       List<ProductDTO> productListDTO,
                       @NotBlank(message = "The field cannot be empty")
                       @Valid
                       CustomerDTO customerDTO) implements Serializable {
}
