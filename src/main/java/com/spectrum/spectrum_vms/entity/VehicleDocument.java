package com.spectrum.spectrum_vms.entity;

import com.spectrum.spectrum_vms.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    @Column(name = "issuing_authority", nullable = false)
    private String issuingAuthority;

    @Column(name = "issuing_date", nullable = false)
    private LocalDate issuingDate;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "scan_copy")
    private String scanCopy;

    // getters and setters

    @PreRemove
    private void removeImage() {
        if (scanCopy != null) {
            try {
                Path imagePath = Paths.get("path/to/image/directory", scanCopy);
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
        VehicleDocument that = (VehicleDocument) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
