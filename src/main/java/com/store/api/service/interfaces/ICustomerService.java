package com.store.api.service.interfaces;

import com.store.api.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {

    void createCustomer(CustomerDTO customerDTO);

    void updateCustomer(Long id, CustomerDTO customerDTO);

    CustomerDTO getCustomer(Long id);

    List<CustomerDTO> getCustomers();

    void deleteCustomer(Long id);
}
