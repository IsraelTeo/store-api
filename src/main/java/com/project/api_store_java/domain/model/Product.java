package com.project.api_store_java.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(exclude = { "salesList" })
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "product_description", length = 500, nullable = false)
    private String productDescription;

    @Column(name = "product_price")
    private Double productPrice;

    private Integer stock;

    @ManyToMany(
            mappedBy = "productsList",
            targetEntity = Sale.class,
            fetch = FetchType.LAZY
    )
    private List<Sale> salesList;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private LocalDate createAt;

    @PrePersist
    public void prePersist() {
        this.createAt = LocalDate.now();
    }

}