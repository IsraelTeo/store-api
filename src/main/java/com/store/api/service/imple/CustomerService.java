package com.store.api.service.imple;

import com.store.api.dto.CustomerDTO;
import com.store.api.persistence.model.entities.Customer;
import com.store.api.persistence.repository.ICustomerRepository;
import com.store.api.service.interfaces.ICustomerService;
import com.store.api.util.mappers.ICustomerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    private final ICustomerMapper customerMapper;

    @Transactional
    @Override
    public void createUser(CustomerDTO customerDTO) {

    }

    @Transactional
    @Override
    public void updateUser(Long id, CustomerDTO customerDTO) {

    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDTO getUser(Long id) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> getUsers() {
        return List.of();
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {

    }
}
