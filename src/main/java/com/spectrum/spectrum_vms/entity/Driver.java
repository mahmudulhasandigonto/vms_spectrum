package com.spectrum.spectrum_vms.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
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

    @Column(name = "name")
    private String name;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "address")
    private String address;


//    @ManyToMany(mappedBy = "drivers", fetch = FetchType.LAZY)
//    private List<VehicleRequest> vehicleRequests;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Driver driver = (Driver) o;
        return getId() != null && Objects.equals(getId(), driver.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
