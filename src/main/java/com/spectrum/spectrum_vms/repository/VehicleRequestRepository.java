package com.spectrum.spectrum_vms.repository;

import com.spectrum.spectrum_vms.entity.VehicleRequest;
import com.spectrum.spectrum_vms.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRequestRepository extends JpaRepository<VehicleRequest, Long> {
    List<VehicleRequest> findByUserId(Long userId);

    List<VehicleRequest> findByRequestStatus(RequestStatus requestStatus);
}
