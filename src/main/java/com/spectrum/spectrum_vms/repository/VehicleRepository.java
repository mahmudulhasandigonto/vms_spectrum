package com.spectrum.spectrum_vms.repository;

import com.spectrum.spectrum_vms.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
