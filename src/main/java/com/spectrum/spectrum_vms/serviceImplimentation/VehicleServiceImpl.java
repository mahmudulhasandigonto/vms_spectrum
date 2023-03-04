package com.spectrum.spectrum_vms.serviceImplimentation;

import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.entity.VehicleRequest;
import com.spectrum.spectrum_vms.error.VehicleNotFoundException;
import com.spectrum.spectrum_vms.error.VehicleRequestNotFoundException;
import com.spectrum.spectrum_vms.repository.VehicleRepository;
import com.spectrum.spectrum_vms.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle update(Vehicle vehicle) throws Exception {
        if (vehicle.hasId()) {
            return save(vehicle);
        } else {
            throw new InvalidDnDOperationException("Vehicle id has been required for update operation");
        }
    }

    @Override
    public void deleteByIds(Long[] ids) {
        vehicleRepository.deleteAllById(Arrays.asList(ids));
    }

    @Override
    public Vehicle getDataById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isEmpty()){
            new VehicleNotFoundException("Vehicle Not Found");
        }
        return vehicle.get();

    }

    @Override
    public List<Vehicle> getData() {
        return vehicleRepository.findAll();
    }

}
