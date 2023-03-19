package com.spectrum.spectrum_vms.repository;

import com.spectrum.spectrum_vms.entity.VehicleRequisition;
import com.spectrum.spectrum_vms.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRequisitionRepository extends JpaRepository<VehicleRequisition, Long> {
    List<VehicleRequisition> findByUserId(Long userId);

    List<VehicleRequisition> findByRequestStatus(RequestStatus requestStatus);
}
