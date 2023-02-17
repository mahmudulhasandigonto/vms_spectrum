package com.spectrum.spectrum_vms.entity;

import com.spectrum.spectrum_vms.enums.FuelType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fuel_logs")
@Builder
public class FuelLog extends BaseEntity {


    @Column(name = "refueling", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime refueling;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", length = 20)
    private FuelType fuelType;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "liters")
    private Double liters;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    // Getters and Setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FuelLog fuelLog = (FuelLog) o;
        return getId() != null && Objects.equals(getId(), fuelLog.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
