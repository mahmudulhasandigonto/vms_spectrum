package com.spectrum.spectrum_vms.serviceImplimentation;

import com.spectrum.spectrum_vms.entity.VehicleRequisition;
import com.spectrum.spectrum_vms.enums.RequestStatus;
import com.spectrum.spectrum_vms.repository.VehicleRequisitionRepository;
import com.spectrum.spectrum_vms.service.VehicleRequisitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleRequisitionServiceImpl implements VehicleRequisitionService {
    private final VehicleRequisitionRepository vehicleRequestRepository;

    @Override
    public VehicleRequisition save(VehicleRequisition vehicleRequest) {
        return vehicleRequestRepository.save(vehicleRequest);
    }

    @Override
    public VehicleRequisition update(VehicleRequisition vehicleRequest) throws Exception {
        if (vehicleRequest.hasId()) {
            return save(vehicleRequest);
        } else {
            throw new InvalidDnDOperationException("Vehicle id has been required for update operation");
        }
    }

    @Override
    public void deleteByIds(Long[] ids) {
        vehicleRequestRepository.deleteAllById(Arrays.asList(ids));
    }

    @Override
    public VehicleRequisition getDataById(Long id) {
        VehicleRequisition vehicleRequest = vehicleRequestRepository.findById(id).get();
        return  vehicleRequest;
    }

    @Override
    public List<VehicleRequisition> getData() {
        return vehicleRequestRepository.findAll();
    }

    @Override
    public List<VehicleRequisition> findByUserId(Long userId) {
        return vehicleRequestRepository.findByUserId(userId);
    }

    @Override
    public List<VehicleRequisition> getDataByRequestStatus(RequestStatus requestStatus) {
        return vehicleRequestRepository.findByRequestStatus(requestStatus);
    }
}
