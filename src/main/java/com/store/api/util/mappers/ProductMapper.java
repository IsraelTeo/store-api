package com.store.api.util.mappers;

import com.store.api.dto.ProductDTO;
import com.store.api.persistence.model.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO productToProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .productType(product.getProductType())
                .build();
    }

    public Product productDTOToProduct(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.id())
                .productName(productDTO.productName())
                .productDescription(productDTO.productDescription())
                .productPrice(productDTO.productPrice())
                .productType(productDTO.productType())
                .build();
    }
}
