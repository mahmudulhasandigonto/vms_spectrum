package com.spectrum.spectrum_vms.repository;

import com.spectrum.spectrum_vms.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverRepository  extends JpaRepository<Driver, Long> {
    Driver findByNameIgnoreCase(String driverName);

    List<Driver> findByIsAvailable(boolean isAvailable);

    @Query("select d from Driver d where d.problem is not null")
    List<Driver> findByProblemIsNotNull();
}
