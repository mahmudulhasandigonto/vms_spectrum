package com.spectrum.spectrum_vms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spectrum.spectrum_vms.enums.RequestStatus;
import com.spectrum.spectrum_vms.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.Duration;
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
public class VehicleRequisition extends BaseEntity{


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vehicle_request_vehicles",
            joinColumns = @JoinColumn(name = "vehicle_request_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Vehicle> vehicles;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vehicle_request_drivers",
            joinColumns = @JoinColumn(name = "vehicle_request_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id"))
    private List<Driver> drivers;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a")
    @Column(name = "request_date", nullable = false)
    private LocalDateTime requestDate = LocalDateTime.now();


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a")
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a")
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "departure")
    private String departure;

    @Column(name = "destination")
    private String destination;

    private String remark;
   @Transient
    private Long duration;


    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus requestStatus=RequestStatus.PENDING;

    public Long getDuration() {
        if (duration == null) {
            duration = Duration.between(startDate, endDate).getSeconds();
        }
        return duration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VehicleRequisition that = (VehicleRequisition) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
