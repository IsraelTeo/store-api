package com.store.api.util.mappers;

import com.store.api.dto.CustomerDTO;
import com.store.api.persistence.model.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO customerToCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dni(customer.getDni())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }

    public Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .id(customerDTO.id())
                .firstName(customerDTO.firstName())
                .lastName(customerDTO.lastName())
                .dni(customerDTO.dni())
                .phoneNumber(customerDTO.phoneNumber())
                .build();
    }
}
