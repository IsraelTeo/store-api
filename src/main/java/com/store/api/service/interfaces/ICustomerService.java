package com.store.api.service.interfaces;

import com.store.api.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {

    void createUser(CustomerDTO customerDTO);

    void updateUser(Long id, CustomerDTO customerDTO);

    CustomerDTO getUser(Long id);

    List<CustomerDTO> getUsers();

    void deleteUser(Long id);
}
