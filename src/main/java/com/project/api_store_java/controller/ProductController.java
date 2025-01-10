package com.project.api_store_java.controller;

import com.project.api_store_java.dto.ProductDTO;
import com.project.api_store_java.payload.response.Response;
import com.project.api_store_java.service.implementations.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProductById(@Min(1) @NotNull @PathVariable("id") Long id) {
        LOGGER.info("Getting product by id {}.", id);
        ProductDTO product = productService.getProductById(id);
        Response response = Response.builder()
                .message("Product found:")
                .data(product)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response> getAllProducts() {
        LOGGER.info("Getting all products.");
        Response response = Response.builder()
                .message("Products found:")
                .data(productService.getProducts())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response> saveProduct(@RequestBody @Valid ProductDTO productDTO) {
        LOGGER.info("Saving product.");
        productService.saveProduct(productDTO);
        Response response = Response.builder()
                .message("Product saved successfully:")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateProduct(@Min(1) @NotNull @PathVariable("id") Long id, @RequestBody @Valid ProductDTO productDTO) {
        LOGGER.info("Updating product.");
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        Response response = Response.builder()
                .message("Product updated successfully:")
                .data(updatedProduct)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteProduct(@Min(1) @NotNull @PathVariable("id") Long id) {
        LOGGER.info("Deleting product.");
        productService.deleteProduct(id);
        Response response = Response.builder()
                .message("Product deleted successfully:")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }
}