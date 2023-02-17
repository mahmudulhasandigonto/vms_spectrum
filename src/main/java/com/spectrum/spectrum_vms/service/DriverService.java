package com.spectrum.spectrum_vms.service;

import com.spectrum.spectrum_vms.entity.Driver;
import org.springframework.stereotype.Service;


@Service
public interface DriverService extends BaseService<Driver, Long> {

   public Driver findByDriverNameIgnoreCase(String driverName);
}
