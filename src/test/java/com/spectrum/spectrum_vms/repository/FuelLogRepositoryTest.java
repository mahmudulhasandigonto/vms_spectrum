package com.spectrum.spectrum_vms.repository;

import com.spectrum.spectrum_vms.entity.FuelLog;
import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.enums.FuelType;
import com.spectrum.spectrum_vms.serviceImplimentation.FuelLogServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FuelLogRepositoryTest {

    @Mock
    private FuelLogRepository fuelLogRepository;

    @InjectMocks
    private FuelLogServiceImpl fuelLogServiceImpl;


    @Test
    @DisplayName("Fetch all data")
    public void testFetchData(){
        Vehicle vehicle = Vehicle.builder()
                .make("Toyota")
                .engineNumber("ncwepe22")
                .model("Toyota hycy")
                .year(2000)
                .build();

        List<FuelLog> expectedData = Arrays.asList(
                FuelLog.builder()
                        .liters(20.0)
                        .fuelType(FuelType.DIESEL)
                        .cost(5000.0)
                        .vehicle(vehicle)
                        .build(),
                FuelLog.builder()
                        .liters(20.0)
                        .fuelType(FuelType.DIESEL)
                        .cost(5000.0)
                        .vehicle(vehicle)
                        .build()

        );
        when(fuelLogRepository.findAll()).thenReturn(expectedData);
        List<FuelLog> actualData = fuelLogServiceImpl.getData();
        assertEquals(expectedData, actualData);
    }



    @Test
    @DisplayName("Find Driver object by Ids")
    public void fetchDataByIds(){
        Vehicle vehicle = Vehicle.builder()
                .make("Toyota")
                .engineNumber("ncwepe22")
                .model("Toyota hycy")
                .year(2000)
                .build();
        List<FuelLog> expectedData = Arrays.asList(
                FuelLog.builder()
                        .liters(20.0)
                        .fuelType(FuelType.DIESEL)
                        .cost(5000.0)
                        .vehicle(vehicle)
                        .build(),
                FuelLog.builder()
                        .liters(20.0)
                        .fuelType(FuelType.DIESEL)
                        .cost(5000.0)
                        .vehicle(vehicle)
                        .build());
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        when(fuelLogRepository.findAllById(list)).thenReturn(expectedData);
        List<FuelLog> data = fuelLogServiceImpl.getDataByIds(new Long[]{1L,2L});
        assertEquals(expectedData, data);
    }


    @Test
    @DisplayName("Delete FuelLog object by Ids")
    public void deleteDataByIds(){
        Long[] ids = new Long[]{1L};
        fuelLogServiceImpl.deleteByIds(ids);
        verify(fuelLogRepository).deleteAllById(List.of(ids));

    }

    @Test
    @DisplayName("Update Fuellog Object")
    public void testUpdateData() throws Exception {
        Vehicle vehicle = Vehicle.builder()
                .make("Toyota")
                .engineNumber("ncwepe22")
                .model("Toyota hycy")
                .year(2000)
                .build();
        FuelLog fuelLog = FuelLog.builder()
                .liters(20.0)
                .fuelType(FuelType.DIESEL)
                .cost(5000.0)
                .vehicle(vehicle)
                .build();
        fuelLog.setId(1L);
        fuelLogServiceImpl.update(fuelLog);
        verify(fuelLogRepository).save(fuelLog);
    }

}