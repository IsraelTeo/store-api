package com.store.api.persistence.model.entities;

import com.store.api.persistence.model.enumsEntities.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@DiscriminatorValue("CUSTOMER")
@Entity
public class Customer extends User{

    @Column(name = "first_name",
            nullable = false,
            length = 50)
    private String firstName;

    @Column(name = "last_name",
            nullable = false,
            length = 70)
    private String lastName;

    @Column(nullable = false,
            unique = true,
            updatable = false,
            length = 20)
    private String dni;

    @Column(name = "phone_number",
            nullable = false,
            unique = true,
            updatable = false,
            length = 20)
    private String phoneNumber;

    @OneToOne(mappedBy = "customer",
            cascade = CascadeType.PERSIST)
    private Sale sale;


    public Customer(Long userId, String email, String password, Set<Role> roles, String firstName, String lastName,
                    String dni, String phoneNumber, Sale sale) {
        super(userId, email, password, roles);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.phoneNumber = phoneNumber;
        this.sale = sale;
    }

}
