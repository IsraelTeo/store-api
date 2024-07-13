package com.store.api.dto;

import com.store.api.enums.RoleEnum;
import com.store.api.persistence.model.entities.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

public record RoleDTO(Long roleId,
                      @NotBlank(message = "The field cannot be empty")
                      RoleEnum role,
                      @NotEmpty(message = "The field cannot be empty")
                      List<UserEntity> users) implements Serializable {

}
