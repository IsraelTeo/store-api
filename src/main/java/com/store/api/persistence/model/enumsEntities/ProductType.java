package com.store.api.persistence.model.enumsEntities;

import com.store.api.enums.ProductTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products_types")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_type_id", nullable = false, unique = true, updatable = false)
    private Long productTypeId;

    @Enumerated(EnumType.STRING)
    private ProductTypeEnum productType;
}
