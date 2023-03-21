package com.spectrum.spectrum_vms.service;

import com.spectrum.spectrum_vms.entity.Driver;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public interface DriverService extends BaseService<Driver, Long> {

   List<Driver> findByIsAvailable(Boolean isTrue);

   List<Driver> findByProblemIsNotNull();

    Driver findByContactNumber(String contactNumber);

    List<Driver> findByIsAvailableAndAvailableDateAfter(Boolean isAvailable, LocalDateTime localDateTime);
}
