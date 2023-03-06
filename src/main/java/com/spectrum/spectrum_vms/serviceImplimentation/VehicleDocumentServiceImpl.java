package com.spectrum.spectrum_vms.serviceImplimentation;

import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.entity.VehicleDocument;
import com.spectrum.spectrum_vms.entity.VehicleRequest;
import com.spectrum.spectrum_vms.error.VehicleDocumentNotFoundException;
import com.spectrum.spectrum_vms.error.VehicleRequestNotFoundException;
import com.spectrum.spectrum_vms.repository.VehicleDocumentRepository;
import com.spectrum.spectrum_vms.service.VehicleDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class VehicleDocumentServiceImpl implements VehicleDocumentService {

    final private VehicleDocumentRepository vehicleDocumentRepository;


    @Override
    public VehicleDocument save(VehicleDocument vehicleDocument) {
        return vehicleDocumentRepository.save(vehicleDocument);
    }

    @Override
    public VehicleDocument update(VehicleDocument vehicleDocument) throws Exception {
        if (vehicleDocument.hasId()) {
            return save(vehicleDocument);
        } else {
            throw new InvalidDnDOperationException("Vehicle Document id has been required for update operation");
        }
    }

    @Override
    public void deleteByIds(Long[] ids) {
        vehicleDocumentRepository.deleteAllById(Arrays.asList(ids));
    }

    @Override
    public VehicleDocument getDataById(Long id) {
        VehicleDocument vehicleDocument = vehicleDocumentRepository.findById(id).get();
        return vehicleDocument;

    }

    @Override
    public List<VehicleDocument> getData() {
        return vehicleDocumentRepository.findAll();
    }
}
