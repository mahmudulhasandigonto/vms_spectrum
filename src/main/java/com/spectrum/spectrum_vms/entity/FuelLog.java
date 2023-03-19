package com.spectrum.spectrum_vms.entity;

import com.spectrum.spectrum_vms.enums.FuelType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fuel_logs")
@Builder
public class FuelLog extends BaseEntity {


    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", length = 20)
    private FuelType fuelType;

    @Column(name = "liters", nullable = false)
    private Double liters;

    @Column(name = "cost", nullable = false)
    private Double cost;



    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @Column(name = "refueling", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime refueling;


    private String receiptImage;

    // Getters and Setters


    @PreRemove
    private void removeImage() {
        if (receiptImage != null) {
            try {
                Path imagePath = Paths.get("path/to/image/directory", receiptImage);
                Files.delete(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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
