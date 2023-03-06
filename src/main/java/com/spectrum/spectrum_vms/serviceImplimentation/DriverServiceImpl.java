package com.spectrum.spectrum_vms.serviceImplimentation;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.error.DriverNotFoundException;
import com.spectrum.spectrum_vms.repository.DriverRepository;
import com.spectrum.spectrum_vms.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Driver update(Driver driver) throws Exception {
        if (driver.hasId()) {
            return save(driver);
        } else {
            throw new InvalidDnDOperationException("Vehicle id has been required for update operation");
        }
    }

    @Override
    public void deleteByIds(Long[] ids) {
        driverRepository.deleteAllById(Arrays.asList(ids));
    }

    @Override
    public Driver getDataById(Long id) {
        Driver driver = driverRepository.findById(id).get();
        return driver;
    }

    @Override
    public List<Driver> getData() {
        return driverRepository.findAll();
    }

    @Override
    public Driver findByDriverNameIgnoreCase(String driverName) {
       return driverRepository.findByNameIgnoreCase(driverName);
    }
}
