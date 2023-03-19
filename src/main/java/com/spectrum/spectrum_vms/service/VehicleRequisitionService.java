package com.spectrum.spectrum_vms.service;

import com.spectrum.spectrum_vms.entity.VehicleRequisition;
import com.spectrum.spectrum_vms.enums.RequestStatus;

import java.util.List;

public interface VehicleRequisitionService extends  BaseService<VehicleRequisition, Long>{

    public List<VehicleRequisition> findByUserId(Long userId);

    List<VehicleRequisition> getDataByRequestStatus(RequestStatus requestStatus);
}
