package com.project.api_store_java.service.interfaces;

import com.project.api_store_java.dto.SaleDTO;

import java.util.List;

public interface ISaleService {

    SaleDTO getSaleById(Long id);

    List<SaleDTO> getSales();

    void registerSale(SaleDTO saleDTO);

    SaleDTO updateSale(Long id, SaleDTO saleDTO);

    void deleteSale(Long id);
}
