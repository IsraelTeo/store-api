package com.store.api.service.imple;

import com.store.api.dto.ProductDTO;
import com.store.api.persistence.model.entities.Product;
import com.store.api.persistence.repository.IProductRepository;
import com.store.api.service.interfaces.IProductService;
import com.store.api.util.mappers.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    private final ProductMapper productMapper;

    @Transactional
    @Override
    public void createProduct(ProductDTO productDTO) {

        Product productSave = productMapper.productDTOToProduct(productDTO);

        Optional<Product> productOptional = productRepository.findById(productSave.getId());
        if(productOptional.isPresent()){
            throw new EntityNotFoundException("The ID entered already exists in the data base");
        }else{
            productRepository.save(productSave);
        }
    }

    @Transactional
    @Override
    public void updateProduct(Long id,  ProductDTO productDTO) {

        Optional<Product>  productOptional = productRepository.findById(id);

        if(productOptional.isPresent()){
            productOptional.get().setId(productDTO.id());
            productOptional.get().setProductName(productDTO.productName());
            productOptional.get().setProductDescription(productDTO.productDescription());
            productOptional.get().setProductPrice(productDTO.productPrice());
            productRepository.save(productOptional.get());
        }else{
            throw new EntityNotFoundException("The ID entered does not exist in the data base");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDTO getProduct(Long id) {

        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){

            Product product = productOptional.get();

            return productMapper.productToProductDTO(product);

        }else{
            throw new  EntityNotFoundException("The ID entered does not exist in the data base");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getProducts() {

        List<Product> productList = productRepository.findAll();

        if (productList.isEmpty()) {
            throw new EntityNotFoundException("List is empty.");
        } else {
            return productList.stream()
                    .map(product -> ProductDTO.builder()
                            .id(product.getId())
                            .productName(product.getProductName())
                            .productDescription(product.getProductDescription())
                            .productPrice(product.getProductPrice())
                            .build())
                    .collect(Collectors.toList());
        }
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()) {
            productRepository.delete(product.get());
        }else{
            throw new EntityNotFoundException("Product not found.");
        }
    }
}
