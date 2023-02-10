package com.spectrum.spectrum_vms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_document")
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
}
