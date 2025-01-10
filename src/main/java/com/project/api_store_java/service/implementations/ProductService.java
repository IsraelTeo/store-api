package com.project.api_store_java.service.implementations;

import com.project.api_store_java.domain.model.Product;
import com.project.api_store_java.dto.ProductDTO;
import com.project.api_store_java.exception.ApiError;
import com.project.api_store_java.exception.StoreException;
import com.project.api_store_java.repository.IProductRepository;
import com.project.api_store_java.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final IProductRepository productRepository;

    private final ConversionService conversionService;

    @Transactional(readOnly = true)
    @Override
    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            LOGGER.warn("Product with ID {} not found", id);
            throw new StoreException(ApiError.PRODUCT_NOT_FOUND);
        }

        return conversionService.convert(product.get(), ProductDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getProducts() {
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) {
            LOGGER.info("Product list is empty");
            return Collections.emptyList();
        }

        return productList.stream()
                .map(product -> conversionService.convert(product, ProductDTO.class))
                .toList();
    }

    @Transactional
    @Override
    public void saveProduct(ProductDTO productDTO) {
        Product product = Optional.ofNullable(conversionService.convert(productDTO, Product.class))
                .orElseThrow(() -> new StoreException(ApiError.CONVERSION_FAILED));
        productRepository.save(product);
    }

    @Transactional
    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            LOGGER.warn("Product with ID {} not found, cannot be updated.", id);
            throw new StoreException(ApiError.PRODUCT_NOT_FOUND);
        }

        Product existingProduct = productOptional.get();
        Product updatedProduct = updateProductFields(existingProduct, productDTO);
        productRepository.save(updatedProduct);

        return conversionService.convert(updatedProduct, ProductDTO.class);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            LOGGER.warn("Product with ID {} not found, cannot be deleted.", id);
            throw new StoreException(ApiError.PRODUCT_NOT_FOUND);
        }

        productRepository.deleteById(id);
    }

    private Product updateProductFields(Product existingProduct, ProductDTO productDTO) {
        return Product.builder()
                .id(existingProduct.getId())
                .productName(productDTO.productName())
                .productDescription(productDTO.productDescription())
                .productPrice(productDTO.productPrice())
                .stock(productDTO.stock())
                .salesList(existingProduct.getSalesList())
                .build();
    }

    //actualizar el stock de un producto (descontar) al realizar una venta

    //Obtener todos los productos cuya cantidad_disponible sea menor a 5


}
