package com.spectrum.spectrum_vms.repository;

import com.spectrum.spectrum_vms.entity.VehicleDocument;
import com.spectrum.spectrum_vms.entity.VehicleRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleDocumentRepository extends JpaRepository<VehicleDocument, Long> {
}
