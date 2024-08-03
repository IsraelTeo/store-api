package com.store.api.service.interfaces;

import com.store.api.dto.SaleDTO;

import java.util.List;

public interface ISaleService {

    void createSale(SaleDTO saleDTO);

    void updateSale(Long id, SaleDTO saleDTO);

    SaleDTO getSale(Long id);

    List<SaleDTO> getSales();

    void deleteSale(Long id);
}
