package com.spectrum.spectrum_vms.controller;

import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle/")
public class VehicleController implements BaseController<Vehicle, Long> {
   final private VehicleService vehicleService;

    // all information save purpose
    @Override
    public ResponseEntity<Vehicle> save(@RequestBody Vehicle vehicle) {
        vehicleService.save(vehicle);
        return ResponseEntity.ok(vehicle);
    }

    // all information update purpose
    @Override
    public ResponseEntity<String> update(@RequestBody Vehicle vehicle) throws Exception {
        try {
            vehicleService.update(vehicle);
            return ResponseEntity.ok("Vehicle information has been updated successfully");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    // single or multiple user information delete purpose
    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) throws DeleteRequestException {
        try {
            vehicleService.deleteByIds(ids);
            return ResponseEntity.ok("ID " + Arrays.toString(ids) + " has been deleted successfully");
        }catch (Exception ex){
            throw new DeleteRequestException("Error deleting vehicle with id " + ids);
        }
    }

    //single or multiple information get purpose
    @Override
    public ResponseEntity<List<Vehicle>> getDataByIds(@PathVariable("ids") Long... ids) {
        List<Vehicle> vehicleList = vehicleService.getDataByIds(ids);
        return ResponseEntity.ok(vehicleList);
    }

    // all information get purpose
    @Override
    public List<Vehicle> getData() {
        return vehicleService.getData();
    }
}
