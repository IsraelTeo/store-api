package com.store.api.persistence.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 70)
    private String lastName;

    @Column(nullable = false, unique = true, updatable = false, length = 20)
    private String dni;

    @Column(name = "phone_number", nullable = false, unique = true, updatable = false, length = 20)
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private LocalDate createAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private LocalDate updateAt;

    @PrePersist
    public void prePersist(){
        this.createAt = LocalDate.now();
    }

    @PostUpdate
    public void postUpdate(){
        this.updateAt = LocalDate.now();
    }

}
