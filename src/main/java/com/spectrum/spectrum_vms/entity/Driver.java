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
@Table(name = "drivers")
@Builder
public class Driver extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "driver_image")
    private String driverImage;

    @Column(name = "contact_number", nullable = false, unique = true, length = 12)
    private String contactNumber;

    @Column(name = "address", nullable = false)
    private String address;

    private String licenseNumber;

    private String nid;

    private Boolean isAvailable=Boolean.TRUE;



    private String problem;





    @PreRemove
    private void removeImage() {
        if (driverImage != null) {
            try {
                Path imagePath = Paths.get("path/to/image/directory", driverImage);
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
        Driver driver = (Driver) o;
        return getId() != null && Objects.equals(getId(), driver.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
