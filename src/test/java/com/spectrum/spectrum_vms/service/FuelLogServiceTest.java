package com.spectrum.spectrum_vms.service;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.entity.FuelLog;
import com.spectrum.spectrum_vms.enums.FuelType;
import com.spectrum.spectrum_vms.repository.FuelLogRepository;
import com.spectrum.spectrum_vms.repository.FuelLogRepository;
import com.spectrum.spectrum_vms.serviceImplimentation.DriverServiceImpl;
import com.spectrum.spectrum_vms.serviceImplimentation.FuelLogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FuelLogServiceTest {

    @Mock
    FuelLogRepository fuelLogRepository;

    FuelLogServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest  = new FuelLogServiceImpl(fuelLogRepository);

    }




    @Test
    public void canGetAllFuelLogs() {
        //when
        underTest.getData();
        //then
        verify(fuelLogRepository).findAll();

    }

    @Test
    public void canAddFuelLog(){


        //given
        FuelLog fuelLog = FuelLog.builder()
                .cost(500.0)
                .liters(20.5)
                .fuelType(FuelType.DIESEL)
                .refueling(LocalDateTime.now())
                .build();
        //when
        underTest.save(fuelLog);
        //then
        ArgumentCaptor<FuelLog> fuelLogArgumentCaptor = ArgumentCaptor.forClass(FuelLog.class);
        verify(fuelLogRepository).save(fuelLogArgumentCaptor.capture());

        FuelLog capturedFuelLog = fuelLogArgumentCaptor.getValue();
        assertThat(capturedFuelLog).isEqualTo(capturedFuelLog);
    }


}