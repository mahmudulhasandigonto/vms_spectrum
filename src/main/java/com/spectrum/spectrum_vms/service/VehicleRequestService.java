package com.spectrum.spectrum_vms.service;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.entity.VehicleRequest;

import java.util.List;

public interface VehicleRequestService extends  BaseService<VehicleRequest, Long>{

    public List<VehicleRequest>  findByUserId(Long userId);
}
