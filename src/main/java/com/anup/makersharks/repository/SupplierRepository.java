package com.anup.makersharks.repository;

import com.anup.makersharks.entity.NatureOfBusiness;
import com.anup.makersharks.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT s FROM Supplier s JOIN ManufacturingProcess mp ON s.supplierId = mp.supplierId " +
            "WHERE s.location = :location AND s.natureOfBusiness = :natureOfBusiness AND mp.process = :process")
    Page<Supplier> findByCriteria(@Param("location") String location,
                                  @Param("natureOfBusiness") NatureOfBusiness natureOfBusiness,
                                  @Param("process") String process,
                                  Pageable pageable);
}