package com.spectrum.spectrum_vms.repository;

import com.spectrum.spectrum_vms.entity.FuelLog;
import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.enums.FuelType;
import com.spectrum.spectrum_vms.serviceImplimentation.FuelLogServiceImpl;
import com.spectrum.spectrum_vms.serviceImplimentation.VehicleServiceImpl;
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
class VehicleRepositoryTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleServiceImpl vehicleServiceImpl;




    @Test
    @DisplayName("Fetch all data")
    public void testFetchData(){

        List<Vehicle> expectedData = Arrays.asList(
                Vehicle.builder()
                        .make("Toyota")
                        .engineNumber("ncwepe22")
                        .model("Toyota hycy")
                        .year(2000)
                        .build(),
                Vehicle.builder()
                        .make("Toyota")
                        .engineNumber("ncwepe22")
                        .model("Toyota hycy")
                        .year(2000)
                        .build()

        );
        when(vehicleRepository.findAll()).thenReturn(expectedData);
        List<Vehicle> actualData = vehicleServiceImpl.getData();
        assertEquals(expectedData, actualData);
    }



    @Test
    @DisplayName("Find Vehicle object by Ids")
    public void fetchDataByIds(){
        List<Vehicle> expectedData = Arrays.asList(
                Vehicle.builder()
                        .make("Toyota")
                        .engineNumber("ncwepe22")
                        .model("Toyota hycy")
                        .year(2000)
                        .build(),
                Vehicle.builder()
                        .make("Toyota")
                        .engineNumber("ncwepe22")
                        .model("Toyota hycy")
                        .year(2000)
                        .build());
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        when(vehicleRepository.findAllById(list)).thenReturn(expectedData);
        List<Vehicle> data = vehicleServiceImpl.getDataByIds(new Long[]{1L,2L});
        assertEquals(expectedData, data);
    }


    @Test
    @DisplayName("Delete Vehicle object by Ids")
    public void deleteDataByIds(){
        Long[] ids = new Long[]{1L};
        vehicleServiceImpl.deleteByIds(ids);
        verify(vehicleRepository).deleteAllById(List.of(ids));

    }

    @Test
    @DisplayName("Update Vehicle Object")
    public void testUpdateData() throws Exception {
        Vehicle vehicle = Vehicle.builder()
                .make("Toyota")
                .engineNumber("ncwepe22")
                .model("Toyota hycy")
                .year(2000)
                .build();
        vehicle.setId(1L);
        vehicleServiceImpl.update(vehicle);
        verify(vehicleRepository).save(vehicle);
    }

}