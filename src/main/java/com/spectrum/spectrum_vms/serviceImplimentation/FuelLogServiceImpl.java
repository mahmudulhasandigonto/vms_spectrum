package com.spectrum.spectrum_vms.serviceImplimentation;

import com.spectrum.spectrum_vms.entity.FuelLog;
import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.error.FuelLogNotFoundException;
import com.spectrum.spectrum_vms.error.VehicleRequestNotFoundException;
import com.spectrum.spectrum_vms.repository.FuelLogRepository;
import com.spectrum.spectrum_vms.service.FuelLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuelLogServiceImpl implements FuelLogService {

    private final FuelLogRepository fuelLogRepository;

    @Override
    public FuelLog save(FuelLog fuelLog) {
        return fuelLogRepository.save(fuelLog);
    }

    @Override
    public FuelLog update(FuelLog fuelLog) throws Exception {
        if (fuelLog.hasId()) {
            return save(fuelLog);
        } else {
            throw new InvalidDnDOperationException("Vehicle id has been required for update operation");
        }
    }

    @Override
    public void deleteByIds(Long[] ids) {
        fuelLogRepository.deleteAllById(Arrays.asList(ids));
    }

    @Override
    public FuelLog getDataById(Long id) {
        Optional<FuelLog> fuelLog = fuelLogRepository.findById(id);
        if(fuelLog.isEmpty()){
            new FuelLogNotFoundException("FuelLog Not Found");
        }
        return fuelLog.get();

    }

    @Override
    public List<FuelLog> getData() {
        return fuelLogRepository.findAll();
    }
}
