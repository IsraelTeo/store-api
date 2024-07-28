package com.store.api.util.mappers;

import com.store.api.dto.SaleDTO;
import com.store.api.persistence.model.entities.Sale;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SaleMapper {

    private CustomerMapper customerMapper;

    private ProductMapper productMapper;

    public SaleDTO saleToSaleDTO(Sale sale) {
        return SaleDTO.builder()
                .id(sale.getId())
                .totalAmount(sale.getTotalAmount())
                .productsList(sale.getProductsList().stream()
                        .map(productMapper::productToProductDTO)
                        .collect(Collectors.toList()))
                .customer(customerMapper.customerToCustomerDTO(sale.getCustomer()))
                .build();
    }

    public Sale saleDTOToSale(SaleDTO saleDTO) {
        return Sale.builder()
                .id(saleDTO.id())
                .totalAmount(saleDTO.totalAmount())
                .productsList(saleDTO.productsList().stream()
                        .map(productMapper::productDTOToProduct)
                        .collect(Collectors.toList()))
                .customer(customerMapper.customerDTOToCustomer(saleDTO.customer()))
                .build();
    }
}
