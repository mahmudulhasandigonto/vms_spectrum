package com.spectrum.spectrum_vms.service;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.entity.FuelLog;
import com.spectrum.spectrum_vms.enums.FuelType;
import com.spectrum.spectrum_vms.repository.DriverRepository;
import com.spectrum.spectrum_vms.repository.FuelLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FuelLogServiceTest {


    @MockBean
    FuelLogRepository fuelLogRepository;
    @Autowired
    FuelLogService fuelLogService;

    @BeforeEach
    void setUp() {
        FuelLog fuelLogTwo = FuelLog.builder()
                .cost(5.20)
                .fuelType(FuelType.CNG)
                .liters(5.0)
                .build();
        FuelLog fuelLogOne = FuelLog.builder()
                .cost(5.20)
                .fuelType(FuelType.CNG)
                .liters(5.0)
                .build();
        List<FuelLog> fulelog = new ArrayList<>();
        fulelog.add(fuelLogOne);
        fulelog.add(fuelLogTwo);
        List<Long> ids = new ArrayList<Long>();
        ids.add(1L);
        ids.add(2L);
        Mockito.when(fuelLogRepository.findAllById(ids))
                .thenReturn(fulelog);

    }

    @Test
    @DisplayName("Get data based on ids")
    public void getDataByIds() {
        double cost = 5.20;

       FuelLog found = fuelLogService.getDataById(1L);
        assertEquals(cost, found.getCost());
    }

}