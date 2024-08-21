package com.anup.makersharks.controller;

import com.anup.makersharks.entity.NatureOfBusiness;
import com.anup.makersharks.entity.Supplier;
import com.anup.makersharks.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping("/query")
    public ResponseEntity<Page<Supplier>> querySuppliers(
            @RequestParam String location,
            @RequestParam NatureOfBusiness natureOfBusiness,
            @RequestParam String process,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be less than one.");
        }

        Page<Supplier> suppliers = supplierService.searchSuppliers(location, natureOfBusiness, process, pageNumber, pageSize);
        return ResponseEntity.ok(suppliers);
    }
}