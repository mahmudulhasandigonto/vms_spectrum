package com.spectrum.spectrum_vms.service;

import com.spectrum.spectrum_vms.entity.Vehicle;

import java.util.List;

public interface VehicleService extends BaseService<Vehicle, Long> {

    List<Vehicle> findByIsAvailable(boolean isAvailable);

    List<Vehicle> findByProblemIsNotNull();
}
