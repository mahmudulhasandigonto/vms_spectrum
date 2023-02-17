package com.spectrum.spectrum_vms.entity;

import com.spectrum.spectrum_vms.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_document")
@Builder
public class VehicleDocument extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "issuing_authority")
    private String issuingAuthority;

    @Column(name = "issuing_date")
    private LocalDate issuingDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "scan_copy")
    private String scanCopy;

    // getters and setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VehicleDocument that = (VehicleDocument) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
