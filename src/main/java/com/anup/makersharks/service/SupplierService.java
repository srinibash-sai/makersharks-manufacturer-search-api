package com.anup.makersharks.service;

import com.anup.makersharks.entity.NatureOfBusiness;
import com.anup.makersharks.entity.Supplier;
import com.anup.makersharks.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public Page<Supplier> searchSuppliers(String location, NatureOfBusiness natureOfBusiness, String process, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return supplierRepository.findByCriteria(location, natureOfBusiness, process, pageable);
    }
}

