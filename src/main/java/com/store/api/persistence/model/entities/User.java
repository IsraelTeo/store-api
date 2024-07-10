package com.store.api.persistence.model.entities;

import com.store.api.persistence.model.enumsEntities.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_customer_type",
        discriminatorType = DiscriminatorType.STRING,
        length = 20)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long userId;

    @Column(nullable = false,
            unique = true,
            length = 255)
    private String email;

    @Column(nullable = false,
            length = 255)
    private String password;

    @ManyToMany(mappedBy = "users",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

}
