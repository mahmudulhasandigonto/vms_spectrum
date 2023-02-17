package com.spectrum.spectrum_vms.entity;

import com.spectrum.spectrum_vms.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VehicleRequest extends BaseEntity{

    @ManyToMany
    private List<Vehicle> vehicles;


    @ManyToMany
    private List<Driver> drivers;


    @Column(name = "request_date")
    private LocalDateTime requestDate;


    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private Date endDate;


    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus requestStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VehicleRequest that = (VehicleRequest) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
