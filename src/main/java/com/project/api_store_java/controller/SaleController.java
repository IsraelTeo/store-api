package com.project.api_store_java.controller;

import com.project.api_store_java.dto.SaleDTO;
import com.project.api_store_java.payload.response.Response;
import com.project.api_store_java.service.implementations.SaleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
@Validated
public class SaleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleController.class);

    private final SaleService saleService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> getSaleById(@Min(1) @NotNull @PathVariable("id") Long id) {
        LOGGER.info("Getting sale by id {}.", id);
        SaleDTO sale = saleService.getSaleById(id);
        Response response = Response.builder()
                .message("Sale found:")
                .data(sale)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response> getAllSales() {
        LOGGER.info("Getting all sales.");
        Response response = Response.builder()
                .message("Sales found:")
                .data(saleService.getSales())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response> registerSale(@RequestBody @Valid SaleDTO saleDTO) {
        LOGGER.info("Registering new sale.");
        saleService.registerSale(saleDTO);
        Response response = Response.builder()
                .message("Sale registered successfully:")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateSale(@Min(1) @NotNull @PathVariable("id") Long id, @RequestBody @Valid SaleDTO saleDTO) {
        LOGGER.info("Updating sale.");
        SaleDTO updatedSale = saleService.updateSale(id, saleDTO);
        Response response = Response.builder()
                .message("Sale updated successfully:")
                .data(updatedSale)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteSale(@Min(1) @NotNull @PathVariable("id") Long id) {
        LOGGER.info("Deleting sale.");
        saleService.deleteSale(id);
        Response response = Response.builder()
                .message("Sale deleted successfully:")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }
}