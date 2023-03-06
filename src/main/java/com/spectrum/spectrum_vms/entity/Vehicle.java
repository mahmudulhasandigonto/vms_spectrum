package com.spectrum.spectrum_vms.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicles")
@Builder
public class Vehicle extends BaseEntity {

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private Integer year;


    @Column(name = "vehicle_image")
    private String vehicleImage;

    @Column(name = "reg_number", nullable = false)
    private String regNumber;

    @Column(name = "engine_number")
    private String engineNumber;


    @Column(name = "vin_number")
    private String vinNumber;

    private Boolean isAvailable=Boolean.TRUE;
    private String problem;

    @PreRemove
    private void removeImage() {
        if (vehicleImage != null) {
            try {
                Path imagePath = Paths.get("path/to/image/directory", vehicleImage);
                Files.delete(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Vehicle vehicle = (Vehicle) o;
        return getId() != null && Objects.equals(getId(), vehicle.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
