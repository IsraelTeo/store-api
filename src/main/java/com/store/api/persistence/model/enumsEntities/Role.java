package com.store.api.persistence.model.enumsEntities;

import com.store.api.enums.RoleEnum;
import com.store.api.persistence.model.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, unique = true, updatable = false)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @ManyToMany
    @JoinTable(name="role_user", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> users;
}
