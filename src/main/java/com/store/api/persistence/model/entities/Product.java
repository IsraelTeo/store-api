package com.store.api.persistence.model.entities;

import com.store.api.enums.ProductTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(exclude = {"salesList"})
@Builder
@Entity
@Table(name = "products")
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;


    @Column(name = "product_description", length = 500, nullable = false)
    private String productDescription;

    @Column(name = "product_price")
    private double productPrice;

    @ManyToMany(targetEntity = Sale.class,
            fetch = FetchType.LAZY)
    @JoinTable(name = "product_id_sale_id", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "sale_id"))
    private List<Sale> salesList;

    @Enumerated(EnumType.STRING)
    private ProductTypeEnum productType;

}
