package com.spectrum.spectrum_vms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name = "model_year", nullable = false)
    private Integer modelYear;


    @Column(name = "vehicle_image")
    private String vehicleImage;

    @Column(name = "reg_number", nullable = false)
    private String regNumber;

    @Column(name = "engine_number")
    private String engineNumber;


    @Column(name = "vin_number")
    private String vinNumber;

    @Column(name = "is_available")
    private Boolean isAvailable=Boolean.TRUE;

    @Column(name = "problem")
    private String problem;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a")
    @Column(name = "availableDate")
    private LocalDateTime availableDate;

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
