package com.project.api_store_java.service.implementations;

import com.project.api_store_java.domain.model.Customer;
import com.project.api_store_java.domain.model.Product;
import com.project.api_store_java.domain.model.Sale;
import com.project.api_store_java.dto.SaleDTO;
import com.project.api_store_java.exception.ApiError;
import com.project.api_store_java.exception.StoreException;
import com.project.api_store_java.repository.ICustomerRepository;
import com.project.api_store_java.repository.IProductRepository;
import com.project.api_store_java.repository.ISaleRepository;
import com.project.api_store_java.service.interfaces.ISaleService;
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
public class SaleService implements ISaleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleService.class);

    private final ISaleRepository saleRepository;

    private final IProductRepository productRepository;

    private final ICustomerRepository customerRepository;

    private final ConversionService conversionService;

    @Transactional(readOnly = true)
    @Override
    public SaleDTO getSaleById(Long id) {
        Optional<Sale> saleOptional = saleRepository.findById(id);
        if (saleOptional.isEmpty()) {
            LOGGER.warn("Sale with ID {} not found", id);
            throw new StoreException(ApiError.SALE_NOT_FOUND);
        }

        return conversionService.convert(saleOptional.get(), SaleDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<SaleDTO> getSales() {
        List<Sale> salesList = saleRepository.findAll();
        if (salesList.isEmpty()) {
            LOGGER.info("Sales list is empty");
            return Collections.emptyList();
        }

        return salesList.stream()
                .map(sale -> conversionService.convert(sale, SaleDTO.class))
                .toList();
    }

    @Transactional
    @Override
    public void registerSale(SaleDTO saleDTO) {
        Sale sale = Optional.ofNullable(conversionService.convert(saleDTO, Sale.class))
                .orElseThrow(() -> new StoreException(ApiError.CONVERSION_FAILED));

        saleRepository.save(sale);
    }

    @Transactional
    @Override
    public SaleDTO updateSale(Long id, SaleDTO saleDTO) {
        Optional<Sale> saleOptional = saleRepository.findById(id);
        if (saleOptional.isEmpty()) {
            LOGGER.warn("Sale with ID {} not found, cannot be updated.", id);
            throw new StoreException(ApiError.SALE_NOT_FOUND);
        }

        Sale sale = Optional.ofNullable( conversionService.convert(saleDTO, Sale.class))
                .orElseThrow(() -> new StoreException(ApiError.CONVERSION_FAILED));

        Customer customer = customerRepository.findById(saleDTO.customerId())
                .orElseThrow(() -> new StoreException(ApiError.CUSTOMER_NOT_FOUND));

        List<Product> products = productRepository.findAllById(saleDTO.productIds());
        if (products.isEmpty()) {
            LOGGER.warn("No products found with provided IDs for update.");
            throw new StoreException(ApiError.PRODUCT_NOT_FOUND);
        }

        sale.setCustomer(customer);
        sale.setProductsList(products);

        Sale updatedSale = saleRepository.save(sale);

        return conversionService.convert(updatedSale, SaleDTO.class);
    }

    @Transactional
    @Override
    public void deleteSale(Long id) {
        Optional<Sale> saleOptional = saleRepository.findById(id);
        if (saleOptional.isEmpty()) {
            LOGGER.warn("Sale with ID {} not found, cannot be deleted.", id);
            throw new StoreException(ApiError.SALE_NOT_FOUND);
        }


        saleRepository.deleteById(id);
    }

    //Obtener la lista de productos de una determinada venta
    //Obtener la sumatoria del monto y también cantidad total de ventas de un determinado día

   // Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el
    //apellido del cliente de la venta con el monto más alto de todas.
}
