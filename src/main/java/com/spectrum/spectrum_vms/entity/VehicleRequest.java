package com.spectrum.spectrum_vms.entity;

import com.spectrum.spectrum_vms.enums.RequestStatus;
import com.spectrum.spectrum_vms.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class VehicleRequest extends BaseEntity{


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    private List<Vehicle> vehicles;


    @ManyToMany
    private List<Driver> drivers;


    @Column(name = "request_date", nullable = false)
    private LocalDateTime requestDate= LocalDateTime.now();


    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "departure")
    private String departure;

    @Column(name = "destination")
    private String destination;


   @Transient
    private Integer duration;

    public Integer getDuration() {
        return duration;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status", nullable = false)
    private RequestStatus requestStatus=RequestStatus.PENDING;

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
