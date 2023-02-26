package com.spectrum.spectrum_vms.controller;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.entity.VehicleRequest;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.service.DriverService;
import com.spectrum.spectrum_vms.service.VehicleRequestService;
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
@RequestMapping("/vehicle_request/")
public class VehicleRequestController implements BaseController<VehicleRequest, Long> {

    final private VehicleRequestService vehicleRequestService;


    //all information save purpose
    @Override
    public ResponseEntity<VehicleRequest> save(@RequestBody VehicleRequest vehicleRequest) {
        vehicleRequestService.save(vehicleRequest);
        return ResponseEntity.ok(vehicleRequest);
    }


    //all information update purpose
    @Override
    public ResponseEntity<String> update(@RequestBody VehicleRequest vehicleRequest) throws Exception {
        try {
            vehicleRequestService.update(vehicleRequest);
            return ResponseEntity.ok("Vehicle Request information has been updated successfully");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }


    //single or multiple information delete purpose
    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) throws DeleteRequestException {
        try {
            vehicleRequestService.deleteByIds(ids);
            return ResponseEntity.ok("ID " + Arrays.toString(ids) + " has been deleted successfully");
        }
        catch (Exception ex){
            throw new DeleteRequestException("Error deleting driver with id " + ids);
        }

    }

    //single or multiple information delete purpose
    @Override
    public ResponseEntity<List<VehicleRequest>> getDataByIds(@PathVariable("ids") Long... ids) {
        List<VehicleRequest> vehicleRequest = vehicleRequestService.getDataByIds(ids);
        return ResponseEntity.ok(vehicleRequest);
    }

    //all information get purpose
    @Override
    public List<VehicleRequest> getData() {
        return vehicleRequestService.getData();
    }
}
