package com.store.api.service.imple;

import com.store.api.dto.SaleDTO;
import com.store.api.persistence.model.entities.Sale;
import com.store.api.persistence.repository.ISaleRepository;
import com.store.api.service.interfaces.ISaleService;
import com.store.api.util.mappers.CustomerMapper;
import com.store.api.util.mappers.ProductMapper;
import com.store.api.util.mappers.SaleMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService implements ISaleService {

    private final ISaleRepository saleRepository;

    private final SaleMapper saleMapper;

    private final CustomerMapper customerMapper;

    private final ProductMapper productMapper;

    @Transactional
    @Override
    public void createSale(SaleDTO saleDTO) {
        Sale saleSave = saleMapper.saleDTOToSale(saleDTO);

        if(saleRepository.existsById(saleSave.getId())) {
            throw new EntityExistsException("The sale already exists");
        }else{
            saleRepository.save(saleSave);
        }
    }

    @Transactional
    @Override
    public void updateSale(Long id, SaleDTO saleDTO) {
        Optional<Sale>  saleOptional = saleRepository.findById(id);

        if(saleOptional.isPresent()){
           saleOptional.get().setId(saleDTO.id());
           saleOptional.get().setCustomer(customerMapper.customerDTOToCustomer(saleDTO.customer()));
           saleOptional.get().setTotalAmount(saleDTO.totalAmount());
           saleOptional.get().setProductsList(saleDTO.productsList().stream()
                   .map(productMapper::productDTOToProduct)
                   .collect(Collectors.toList()));
           saleRepository.save(saleOptional.get());
        }else{
            throw new EntityNotFoundException("The ID entered does not exist in the data base");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public SaleDTO getSale(Long id) {
        Optional<Sale> saleOptional = saleRepository.findById(id);
        if(saleOptional.isPresent()){

            Sale sale = saleOptional.get();

            return saleMapper.saleToSaleDTO(sale);
        }else{
            throw new  EntityNotFoundException("The ID entered does not exist in the data base");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<SaleDTO> getSales() {
        List<Sale> saleList = saleRepository.findAll();

        if (saleList.isEmpty()) {
            throw new EntityNotFoundException("List is empty.");
        } else {
            return saleList.stream()
                    .map(saleMapper::saleToSaleDTO)
                    .collect(Collectors.toList());
        }
    }

    @Transactional
    @Override
    public void deleteSale(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);

        if(sale.isPresent()) {
            saleRepository.delete(sale.get());
        }else{
            throw new EntityNotFoundException("Product not found.");
        }
    }
}
