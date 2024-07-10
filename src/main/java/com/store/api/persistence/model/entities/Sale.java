package com.store.api.persistence.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(exclude = {"productList"})
@Builder
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long saleId;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate date;

    @Column(name ="total_amount",
            nullable = false)
    private double totalAmount;

    @OneToMany(targetEntity = Product.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Product> productList;

    @OneToOne(targetEntity = Customer.class,
            cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
