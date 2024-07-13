package com.store.api.persistence.model.entities;

import com.store.api.persistence.model.enumsEntities.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
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
public class Customer extends UserEntity {

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 70)
    private String lastName;

    @Column(nullable = false, unique = true, updatable = false, length = 20)
    private String dni;

    @Column(name = "phone_number", nullable = false, unique = true, updatable = false, length = 20)
    private String phoneNumber;

    @OneToOne(mappedBy = "customer",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private Sale sale;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private LocalDate createAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private LocalDate updateAt;

    public Customer(Long userId, String email, String password, Set<Role> roles, String firstName, String lastName,
                    String dni, String phoneNumber, Sale sale) {
        super(userId, email, password, roles);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.phoneNumber = phoneNumber;
        this.sale = sale;
    }

    @PrePersist
    public void prePersist(){
        this.createAt = LocalDate.now();
    }

    @PostUpdate
    public void postUpdate(){
        this.updateAt = LocalDate.now();
    }

}
