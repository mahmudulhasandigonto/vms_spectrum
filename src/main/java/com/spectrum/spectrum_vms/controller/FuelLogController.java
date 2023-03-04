package com.spectrum.spectrum_vms.controller;


import com.spectrum.spectrum_vms.entity.FuelLog;
import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.repository.FuelLogRepository;
import com.spectrum.spectrum_vms.service.FuelLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/fuellog/")
@RequiredArgsConstructor
public class FuelLogController implements BaseController<FuelLog, Long> {

    final private FuelLogService fuelLogService;

    @Override
    public ResponseEntity<FuelLog> save(@RequestBody FuelLog fuelLog) {
        fuelLogService.save(fuelLog);
        return  ResponseEntity.ok(fuelLog);
    }

    @Override
    public ResponseEntity<String> update(@RequestBody FuelLog fuelLog) throws Exception {
        try {
            fuelLogService.update(fuelLog);
            return ResponseEntity.ok("FuelLog information has been updated successfully");
        }catch (Exception ex){
            return ResponseEntity.unprocessableEntity().body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteByIds(@PathVariable("ids") Long... ids) throws DeleteRequestException {
        try {
            fuelLogService.deleteByIds(ids);
            return ResponseEntity.ok("ID " + Arrays.toString(ids) + " has been deleted successfully");
        }catch (Exception ex){
            throw new DeleteRequestException("Error deleting fuelLog with id " + ids);
        }
    }

    //single information get purpose
    @Override
    public ResponseEntity<FuelLog> getDataById(@PathVariable("id") Long id) {
        FuelLog fuelLongList = fuelLogService.getDataById(id);
        return ResponseEntity.ok(fuelLongList);
    }

    @Override
    public List<FuelLog> getData() {
        return fuelLogService.getData();
    }
}
