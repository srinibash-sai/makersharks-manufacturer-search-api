package com.anup.makersharks.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "manufacturing_processes")
public class ManufacturingProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long supplierId;
    private String process;
}

