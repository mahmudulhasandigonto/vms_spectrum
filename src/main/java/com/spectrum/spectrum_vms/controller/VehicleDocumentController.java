package com.spectrum.spectrum_vms.controller;

import com.spectrum.spectrum_vms.entity.VehicleDocument;
import com.spectrum.spectrum_vms.error.DeleteRequestException;
import com.spectrum.spectrum_vms.service.VehicleDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/vehicle_document/")
@RequiredArgsConstructor
public class VehicleDocumentController implements BaseController<VehicleDocument, Long>{

    final private VehicleDocumentService vehicleDocumentService;

    @Override
    public ResponseEntity<VehicleDocument> save(@RequestBody VehicleDocument vehicleDocument) {
        vehicleDocumentService.save(vehicleDocument);
        return ResponseEntity.ok(vehicleDocument);
    }

    // all information update purpose
    @Override
    public ResponseEntity<String> update(@RequestBody VehicleDocument vehicleDocument) throws Exception {
        try {
            vehicleDocumentService.update(vehicleDocument);
            return ResponseEntity.ok("Vehicle document information has been updated successfully");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    // single or multiple information delete purpose
    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) throws DeleteRequestException {
        try {
            vehicleDocumentService.deleteByIds(ids);
            return ResponseEntity.ok("ID " + Arrays.toString(ids) + " has been deleted successfully");
        }catch (Exception ex){
            throw new DeleteRequestException("Error deleting vehicle document with id " + ids);
        }
    }

    //single or multiple information get purpose
    @Override
    public ResponseEntity<List<VehicleDocument>> getDataByIds(@PathVariable("ids") Long... ids) {
        List<VehicleDocument> vehicleList = vehicleDocumentService.getDataByIds(ids);
        return ResponseEntity.ok(vehicleList);
    }

    // all information get purpose
    @Override
    public List<VehicleDocument> getData() {
        return vehicleDocumentService.getData();
    }
}
