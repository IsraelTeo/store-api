package com.project.api_store_java.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CustomerDTO(Long id,
        @NotBlank(message = "First name is required") @Size(max = 50, message = "First name must be at most 50 characters") String firstName,

        @NotBlank(message = "Last name is required") @Size(max = 70, message = "Last name must be at most 70 characters") String lastName,

        @NotBlank(message = "DNI is required") @Size(max = 20, message = "DNI must be at most 20 characters") String dni,

        @NotBlank(message = "Phone number is required") @Size(max = 20, message = "Phone number must be at most 20 characters") String phoneNumber) {
}
