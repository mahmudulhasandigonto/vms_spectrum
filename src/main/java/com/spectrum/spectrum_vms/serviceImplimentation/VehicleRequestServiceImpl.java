package com.spectrum.spectrum_vms.serviceImplimentation;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.entity.VehicleRequest;
import com.spectrum.spectrum_vms.enums.RequestStatus;
import com.spectrum.spectrum_vms.error.DriverNotFoundException;
import com.spectrum.spectrum_vms.error.VehicleRequestNotFoundException;
import com.spectrum.spectrum_vms.repository.VehicleDocumentRepository;
import com.spectrum.spectrum_vms.repository.VehicleRequestRepository;
import com.spectrum.spectrum_vms.service.VehicleRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleRequestServiceImpl implements VehicleRequestService {
    private final VehicleRequestRepository vehicleRequestRepository;

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
    public VehicleRequest getDataById(Long id) {
        Optional<VehicleRequest> vehicleRequest = vehicleRequestRepository.findById(id);
        if(vehicleRequest.isEmpty()){
            new VehicleRequestNotFoundException("VehicleRequest Not Found");
        }
        return vehicleRequest.get();
    }

    @Override
    public List<VehicleRequest> getData() {
        return vehicleRequestRepository.findAll();
    }

    @Override
    public List<VehicleRequest> findByUserId(Long userId) {
        return vehicleRequestRepository.findByUserId(userId);
    }

    @Override
    public List<VehicleRequest> getDataByRequestStatus(RequestStatus requestStatus) {
        return vehicleRequestRepository.findByRequestStatus(requestStatus);
    }
}
