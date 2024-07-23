package com.store.api.controller;

import com.store.api.dto.CustomerDTO;
import com.store.api.service.imple.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) throws URISyntaxException {
        try{
            customerService.createCustomer(customerDTO);
            return ResponseEntity.created(new URI("/api/v1/customer")).build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDTO customerDTO) throws URISyntaxException {
        try{
            customerService.updateCustomer(id, customerDTO);
            return ResponseEntity.ok().location(new URI("/api/v1/customer/" + id)).build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customer")
    public ResponseEntity<?> getCustomer(@RequestParam(name = "id") Long id) {
        try{
            CustomerDTO customerGet = customerService.getCustomer(id);
            return ResponseEntity.ok(customerGet);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAll() {
        try{
            List<CustomerDTO> customerDTOList = customerService.getCustomers();
            return ResponseEntity.ok(customerDTOList);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>("client deleted successfully", HttpStatus.NO_CONTENT);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
