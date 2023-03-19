package com.spectrum.spectrum_vms.controller;

import com.spectrum.spectrum_vms.entity.VehicleRequisition;
import com.spectrum.spectrum_vms.enums.RequestStatus;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.error.VehicleRequestNotFoundException;
import com.spectrum.spectrum_vms.service.VehicleRequisitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle_request/")
public class VehicleRequisitionController implements BaseController<VehicleRequisition, Long> {

    final private VehicleRequisitionService vehicleRequestService;


    //all information save purpose
    @Override
    public ResponseEntity<VehicleRequisition> save(@RequestBody VehicleRequisition vehicleRequest) {
        vehicleRequestService.save(vehicleRequest);
        return ResponseEntity.ok(vehicleRequest);
    }


    //all information update purpose
    @Override
    public ResponseEntity<String> update(@RequestBody VehicleRequisition vehicleRequest) throws Exception {
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
            throw new DeleteRequestException("Error deleting vehicle requisition with id " + ids);
        }

    }

    //single or multiple information delete purpose
    @Override
    public ResponseEntity<VehicleRequisition> getDataById(@PathVariable("id") Long id) throws VehicleRequestNotFoundException {
        try {
            VehicleRequisition vehicleRequest = vehicleRequestService.getDataById(id);
            return ResponseEntity.ok(vehicleRequest);
        }
        catch (Exception e){
            throw new VehicleRequestNotFoundException("Vehicle Requisition is not found");
        }
    }


    @GetMapping("user/{id}")
    public ResponseEntity<List<VehicleRequisition>> getDataByUserId(@PathVariable("id") Long id) {
        List<VehicleRequisition> vehicleRequest = vehicleRequestService.findByUserId(id);
        return ResponseEntity.ok(vehicleRequest);
    }

    //all information get purpose
    @Override
    public List<VehicleRequisition> getData() {
        return vehicleRequestService.getData();
    }


    @GetMapping("vehicle_request_status/{request_status}")
    public ResponseEntity<List<VehicleRequisition>> getDataByRequestStatus(@PathVariable("request_status") RequestStatus request_status){
        List<VehicleRequisition> vehicleRequests = vehicleRequestService.getDataByRequestStatus(request_status);
        return ResponseEntity.ok(vehicleRequests);
    }
}
