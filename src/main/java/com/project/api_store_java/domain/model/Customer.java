package com.project.api_store_java.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false, updatable = false, unique = true)
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

    @PrePersist
    public void prePersist() {
        this.createAt = LocalDate.now();
    }
}
