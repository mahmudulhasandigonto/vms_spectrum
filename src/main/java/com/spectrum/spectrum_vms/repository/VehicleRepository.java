package com.spectrum.spectrum_vms.repository;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByMake(String testEntity);


    List<Vehicle> findByIsAvailable(boolean isAvailable);

    @Query("select v from Vehicle v where v.problem is not null")
    List<Vehicle> findByProblemIsNotNull();
}
