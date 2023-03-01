package com.spectrum.spectrum_vms.serviceImplimentation;

import com.spectrum.spectrum_vms.entity.VehicleRequest;
import com.spectrum.spectrum_vms.repository.VehicleDocumentRepository;
import com.spectrum.spectrum_vms.repository.VehicleRequestRepository;
import com.spectrum.spectrum_vms.service.VehicleRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleRequestServiceImpl implements VehicleRequestService {
    private final VehicleRequestRepository vehicleRequestRepository;
    private final VehicleDocumentRepository vehicleDocumentRepository;

    @Override
    public VehicleRequest save(VehicleRequest vehicleRequest) {
        return vehicleRequestRepository.save(vehicleRequest);
    }

    @Override
    public VehicleRequest update(VehicleRequest vehicleRequest) throws Exception {
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
    public List<VehicleRequest> getDataByIds(Long[] ids) {
        return vehicleRequestRepository.findAllById(Arrays.asList(ids));
    }

    @Override
    public List<VehicleRequest> getData() {
        return vehicleRequestRepository.findAll();
    }

    @Override
    public List<VehicleRequest> findByUserId(Long userId) {
        return vehicleRequestRepository.findByUserId(userId);
    }
}
