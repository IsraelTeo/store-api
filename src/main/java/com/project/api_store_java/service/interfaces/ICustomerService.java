package com.project.api_store_java.service.interfaces;

import com.project.api_store_java.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {

    CustomerDTO getCustomer(Long id);

    List<CustomerDTO> getAllCustomers();

    void saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);

}
