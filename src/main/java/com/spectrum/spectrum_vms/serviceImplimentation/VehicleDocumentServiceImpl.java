package com.spectrum.spectrum_vms.serviceImplimentation;

import com.spectrum.spectrum_vms.entity.VehicleDocument;
import com.spectrum.spectrum_vms.repository.VehicleDocumentRepository;
import com.spectrum.spectrum_vms.service.VehicleDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;


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
    public List<VehicleDocument> getDataByIds(Long[] ids) {
        return vehicleDocumentRepository.findAllById(Arrays.asList(ids));
    }

    @Override
    public List<VehicleDocument> getData() {
        return vehicleDocumentRepository.findAll();
    }
}
