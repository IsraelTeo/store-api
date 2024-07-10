package com.store.api.persistence.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long productId;

    @Column(name = "product_name",
            nullable = false,
             length = 50)
    private String productName;

    @Column(name = "product_description",
            length = 500,
            nullable = false)
    private String productDescription;

    @Column(name = "product_price")
    private Double productPrice;

}
