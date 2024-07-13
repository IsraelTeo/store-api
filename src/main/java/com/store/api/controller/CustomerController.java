package com.store.api.controller;

import com.store.api.dto.CustomerDTO;
import com.store.api.service.imple.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody CustomerDTO customerDTO) {
        try {

            customerService.createUser(customerDTO);
            return new ResponseEntity<>("Customer created successfully", HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception e) {

            return new ResponseEntity<>("An error occurred while creating the customer", HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
