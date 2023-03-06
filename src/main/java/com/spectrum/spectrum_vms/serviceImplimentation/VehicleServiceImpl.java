package com.spectrum.spectrum_vms.serviceImplimentation;

import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.repository.VehicleRepository;
import com.spectrum.spectrum_vms.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;

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
        Vehicle vehicle = vehicleRepository.findById(id).get();
        return  vehicle;
    }

    @Override
    public List<Vehicle> getData() {
        return vehicleRepository.findAll();
    }


    @Override
    public List<Vehicle> findByIsAvailable(boolean isAvailable) {
        return vehicleRepository.findByIsAvailable(isAvailable);
    }

    @Override
    public List<Vehicle> findByProblemIsNotNull() {
        return vehicleRepository.findByProblemIsNotNull();
    }

}
