package com.project.api_store_java.service.implementations;

import com.project.api_store_java.dto.CustomerDTO;
import com.project.api_store_java.exception.ApiError;
import com.project.api_store_java.exception.StoreException;
import com.project.api_store_java.persistence.model.Customer;
import com.project.api_store_java.persistence.repository.ICustomerRepository;
import com.project.api_store_java.service.interfaces.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private final ICustomerRepository customerRepository;

    private final ConversionService conversionService;

    @Override
    public CustomerDTO getCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            LOGGER.warn("Customer with ID {} not found", id);
            throw new StoreException(ApiError.CUSTOMER_NOT_FOUND);
        }
        return conversionService.convert(customer, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        if (customerList.isEmpty()) {
            LOGGER.info("Customer list is empty");
            return Collections.emptyList();
        }
        return customerList.stream().map(customer -> conversionService.convert(customer, CustomerDTO.class)).toList();
    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        Customer customer = conversionService.convert(customerDTO, Customer.class);
        assert customer != null;
        customerRepository.save(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            LOGGER.warn("Customer with ID {} already exists, cannot be updated.", id);
            throw new StoreException(ApiError.CUSTOMER_ALREADY_EXISTS);
        }
        Customer customer = conversionService.convert(customerDTO, Customer.class);
        assert customer != null;
        Customer customerUpdated = updateCustomerFields(customer);
        customerRepository.save(customerUpdated);
        return conversionService.convert(customerUpdated, CustomerDTO.class);
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            LOGGER.warn("Customer with ID {} not found, cannot be deleted.", id);
            throw new StoreException(ApiError.CUSTOMER_NOT_FOUND);
        }
        customerRepository.deleteById(id);
    }

    private Customer updateCustomerFields(Customer customer) {
        return Customer.builder().firstName(customer.getFirstName()).lastName(customer.getLastName())
                .dni(customer.getDni()).phoneNumber(customer.getPhoneNumber()).build();
    }
}
