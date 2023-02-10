package com.spectrum.spectrum_vms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fuel_logs")
public class FuelLog extends BaseEntity {

    @Column(name = "date")
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", length = 20)
    private String fuelType;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "liters")
    private Double liters;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    // Getters and Setters
}
