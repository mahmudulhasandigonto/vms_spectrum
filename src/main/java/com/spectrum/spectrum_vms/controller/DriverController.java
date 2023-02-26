package com.spectrum.spectrum_vms.controller;


import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.error.DriverNotFoundException;
import com.spectrum.spectrum_vms.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/driver/")
@RequiredArgsConstructor
public class DriverController implements BaseController<Driver, Long> {



   final private DriverService driverService;


    //all information save purpose
    @Override
    public ResponseEntity<Driver> save(@RequestBody Driver driver) {
        driverService.save(driver);
        return ResponseEntity.ok(driver);
    }


    //all information update purpose
    @Override
    public ResponseEntity<String> update(@RequestBody Driver driver) throws Exception {
        try {
            driverService.update(driver);
            return ResponseEntity.ok("Driver information has been updated successfully");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }


    //single or multiple user information delete purpose
    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) throws DeleteRequestException {
       try {
           driverService.deleteByIds(ids);
           return ResponseEntity.ok("ID " + Arrays.toString(ids) + " has been deleted successfully");
       }
       catch (Exception ex){
           throw new DeleteRequestException("Error deleting driver with id " + ids);
        }

    }


    //single or multiple information get purpose
    @Override
    public ResponseEntity<List<Driver>> getDataByIds(@PathVariable("ids") Long... ids) {
        List<Driver> driverList = driverService.getDataByIds(ids);
        return ResponseEntity.ok(driverList);
    }

    //all information get purpose
    @Override
    public List<Driver> getData() {
        return driverService.getData();
    }
}
