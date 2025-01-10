package com.project.api_store_java.service.interfaces;

import com.project.api_store_java.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    ProductDTO getProductById(Long id);

    List<ProductDTO> getProducts();

    void saveProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);
}
