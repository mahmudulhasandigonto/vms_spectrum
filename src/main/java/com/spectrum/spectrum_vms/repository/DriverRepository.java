package com.spectrum.spectrum_vms.repository;

import com.spectrum.spectrum_vms.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DriverRepository  extends JpaRepository<Driver, Long> {
    Driver findByContactNumber(String contactNumber);

    List<Driver> findByIsAvailable(Boolean isTrue);

    List<Driver> findByIsAvailableAndAvailableDateAfter(boolean isAvailable, LocalDateTime localDateTime);

    @Query("select d from Driver d where d.problem is not null")
    List<Driver> findByProblemIsNotNull();
}
