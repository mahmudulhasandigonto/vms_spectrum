package com.spectrum.spectrum_vms.controller;


import com.spectrum.spectrum_vms.entity.FuelLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fuellog/")
public class FuelLogController implements BaseController<FuelLog, Long> {
    @Override
    public ResponseEntity<FuelLog> save(FuelLog fuelLog) {
        return null;
    }

    @Override
    public ResponseEntity<String> update(FuelLog fuelLog) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteByIds(Long... longs) {
        return null;
    }

    @Override
    public ResponseEntity<List<FuelLog>> getDataByIds(Long... longs) {
        return null;
    }

    @Override
    public List<FuelLog> getData() {
        return null;
    }
}
