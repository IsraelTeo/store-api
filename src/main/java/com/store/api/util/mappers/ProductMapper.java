package com.store.api.util.mappers;

import com.store.api.dto.ProductDTO;
import com.store.api.persistence.model.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final SaleMapper saleMapper;

    public ProductDTO productToProductDTO(Product product) {
        return ProductDTO.builder()
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .salesList(product.getSalesList().stream()
                        .map(saleMapper::saleToSaleDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public Product productDTOToProduct(ProductDTO productDTO) {
            return Product.builder()
                    .productName(productDTO.productName())
                    .productDescription(productDTO.productDescription())
                    .productPrice(productDTO.productPrice())
                    .salesList(productDTO.salesList().stream()
                            .map(saleMapper::saleDTOToSale)
                            .collect(Collectors.toList()))
                    .build();
    }
}
