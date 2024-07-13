package com.store.api.dto;

import com.store.api.persistence.model.enumsEntities.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.io.Serializable;
import java.util.Set;

@Getter
public record CustomerDTO (Long customerIdDTO,
                          @NotBlank(message = "The field cannot be empty")
                          @Email(message = "You must enter a valid email")
                          String email,
                          @NotBlank(message = "The field cannot be empty")
                          String password,
                          @NotEmpty(message = "the field cannot be empty")
                           @Valid
                          Set<RoleDTO> rolesDTO,
                          @NotBlank(message = "The field cannot be empty")
                          String firstName,
                          @NotBlank(message = "The field cannot be empty")
                          String lastName,
                          @NotBlank(message = "The field cannot be empty")
                          String dni,
                          @NotBlank(message = "The field cannot be empty")
                          String phoneNumber,
                          @Valid
                          SaleDTO saleDTO) implements Serializable {
}
