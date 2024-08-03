package com.store.api.controller;

import com.store.api.dto.SaleDTO;
import com.store.api.service.imple.SaleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping("/sale")
    public ResponseEntity<?> createSale(@RequestBody @Valid SaleDTO saleDTO) throws URISyntaxException {
        try{
            saleService.createSale(saleDTO);
            return ResponseEntity.created(new URI("/api/v1/sale")).build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/sale/{id}")
    public ResponseEntity<?> updateSale(@PathVariable Long id, @RequestBody @Valid SaleDTO saleDTO) throws URISyntaxException {
        try{
            saleService.updateSale(id, saleDTO);
            return ResponseEntity.ok().location(new URI("/api/v1/sale/" + id)).build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sale")
    public ResponseEntity<?> getSale(@RequestParam(name = "id") Long id) {
        try{
            SaleDTO saleGet = saleService.getSale(id);
            return ResponseEntity.ok(saleGet);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sales")
    public ResponseEntity<?> getAll() {
        try{
            List<SaleDTO> saleDTOList = saleService.getSales();
            return ResponseEntity.ok(saleDTOList);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/sale/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable Long id) {
        try {
            saleService.deleteSale(id);
            return new ResponseEntity<>("sale deleted successfully", HttpStatus.NO_CONTENT);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
