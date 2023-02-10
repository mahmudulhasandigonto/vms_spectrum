package com.spectrum.spectrum_vms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor 

@Table(name = "vehicles")
public class Vehicle extends BaseEntity{

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "reg_number")
    private String regNumber;

    @Column(name = "engine_number")
    private String engineNumber;

    @Column(name = "vin_number")
    private String vinNumber;

    // Getters and Setters
}
