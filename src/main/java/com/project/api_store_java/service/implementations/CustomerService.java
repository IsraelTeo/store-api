package com.project.api_store_java.service.implementations;

import com.project.api_store_java.dto.CustomerDTO;
import com.project.api_store_java.exception.ApiError;
import com.project.api_store_java.exception.StoreException;
import com.project.api_store_java.domain.model.Customer;
import com.project.api_store_java.repository.ICustomerRepository;
import com.project.api_store_java.service.interfaces.ICustomerService;
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
public class CustomerService implements ICustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private final ICustomerRepository customerRepository;

    private final ConversionService conversionService;

    @Transactional(readOnly = true)
    @Override
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            LOGGER.warn("Customer with ID {} not found", id);
            throw new StoreException(ApiError.CUSTOMER_NOT_FOUND);
        }

        return conversionService.convert(customer, CustomerDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> getCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        if (customerList.isEmpty()) {
            LOGGER.info("Customer list is empty");
            return Collections.emptyList();
        }

        return customerList.stream()
                .map(customer -> conversionService.convert(customer, CustomerDTO.class))
                .toList();
    }

    @Transactional
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        Customer customer =  Optional.ofNullable(conversionService.convert(customerDTO, Customer.class))
                .orElseThrow(() -> new StoreException(ApiError.CONVERSION_FAILED));
        customerRepository.save(customer);
    }

    @Transactional
    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            LOGGER.warn("Customer with ID {} already exists, cannot be updated.", id);
            throw new StoreException(ApiError.CUSTOMER_ALREADY_EXISTS);
        }

        Customer customer = Optional.ofNullable(conversionService.convert(customerDTO, Customer.class))
                .orElseThrow(() -> new StoreException(ApiError.CONVERSION_FAILED));

        Customer customerUpdated = updateCustomerFields(customer);
        customerRepository.save(customerUpdated);

        return conversionService.convert(customerUpdated, CustomerDTO.class);
    }

    @Transactional
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
        return Customer.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dni(customer.getDni())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
