package com.store.api.service.interfaces;

import com.store.api.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    void createProduct(ProductDTO productDTO);

    void updateProduct(Long id, ProductDTO productDTO);

    ProductDTO getProduct(Long id);

    List<ProductDTO> getProducts();

    void deleteProduct(Long id);
}
