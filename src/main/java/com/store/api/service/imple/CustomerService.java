package com.store.api.service.imple;

import com.store.api.dto.CustomerDTO;
import com.store.api.persistence.model.entities.Customer;
import com.store.api.persistence.repository.ICustomerRepository;
import com.store.api.service.interfaces.ICustomerService;
import com.store.api.util.mappers.CustomerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Transactional
    @Override
    public void createCustomer(CustomerDTO customerDTO) {

        Customer customerSave = customerMapper.customerDTOToCustomer(customerDTO);

        if(customerRepository.findByDni(customerSave.getDni()).isPresent()){
            throw new EntityNotFoundException("The ID entered already exists in the data base");
        }else{
            customerRepository.save(customerSave);
        }
    }

    @Transactional
    @Override
    public void updateCustomer(Long id, CustomerDTO customerDTO) {

        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isPresent()){
            customerOptional.get().setId(customerDTO.id());
            customerOptional.get().setFirstName(customerDTO.firstName());
            customerOptional.get().setLastName(customerDTO.lastName());
            customerOptional.get().setDni(customerDTO.dni());
            customerOptional.get().setPhoneNumber(customerDTO.phoneNumber());
            customerRepository.save(customerOptional.get());
        }else{
            throw new EntityNotFoundException("The ID entered does not exist in the data base");
        }

    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDTO getCustomer(Long id) {

        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isPresent()){

            Customer customer = customerOptional.get();

            return customerMapper.customerToCustomerDTO(customer);

        }else{
            throw new  EntityNotFoundException("The ID entered does not exist in the data base");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> getCustomers() {

        List<Customer> customerList = customerRepository.findAll();

        if (customerList.isEmpty()) {
            throw new EntityNotFoundException("List is empty.");
        }else{
            return customerList.stream()
                    .map(customer -> new CustomerDTO(
                            customer.getId(),
                            customer.getFirstName(),
                            customer.getLastName(),
                            customer.getDni(),
                            customer.getPhoneNumber()))
                    .collect(Collectors.toList());
        }
    }

    @Transactional
    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()) {
            customerRepository.delete(customer.get());
        }else{
            throw new EntityNotFoundException("Customer not found.");
        }
    }
}
