package com.store.api.controller;

import com.store.api.dto.ProductDTO;
import com.store.api.service.imple.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductDTO productDTO) throws URISyntaxException {
        try{
            productService.createProduct(productDTO);
            return ResponseEntity.created(new URI("/api/v1/product")).build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) throws URISyntaxException {
        try{
            productService.updateProduct(id, productDTO);
            return ResponseEntity.ok().location(new URI("/api/v1/product/" + id)).build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/product")
    public ResponseEntity<?> getProduct(@RequestParam(name = "id") Long id) {
        try{
            ProductDTO productGet = productService.getProduct(id);
            return ResponseEntity.ok(productGet);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAll() {
        try{
            List<ProductDTO> productDTOList = productService.getProducts();
            return ResponseEntity.ok(productDTOList);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>("client deleted successfully", HttpStatus.NO_CONTENT);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
