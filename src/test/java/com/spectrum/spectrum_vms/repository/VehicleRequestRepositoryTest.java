package com.spectrum.spectrum_vms.repository;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.entity.VehicleRequest;
import com.spectrum.spectrum_vms.enums.RequestStatus;
import com.spectrum.spectrum_vms.serviceImplimentation.VehicleRequestServiceImpl;
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
class VehicleRequestRepositoryTest {


    @Mock
    private VehicleRequestRepository vehicleRequestRepository;

    @InjectMocks
    private VehicleRequestServiceImpl vehicleRequestServiceImpl;


    @Test
    @DisplayName("Fetch all data")
    public void testFetchData(){

        Vehicle vcOne = Vehicle.builder()
                .make("Toyota")
                .year(2020)
                .engineNumber("125wiws125")
                .regNumber("reksiw125")
                .vinNumber("wi152")
                .build();
        Vehicle vcTwo = Vehicle.builder()
                .make("Toyota")
                .year(2020)
                .engineNumber("125wiws125")
                .regNumber("reksiw125")
                .vinNumber("wi152")
                .build();
        List<Vehicle> vcList = new ArrayList<Vehicle>();
        vcList.add(vcOne);
        vcList.add(vcTwo);




        //Driver Object
        Driver driverOne = Driver.builder()
                .name("Mahmudul Hasan")
                .contactNumber("01704329668")
                .address("Dhaka")
                .build();
        Driver driverTwo = Driver.builder()
                .name("Mahmudul Hasan")
                .contactNumber("01704329668")
                .address("Dhaka")
                .build();

        List<Driver> dvList = new ArrayList<Driver>();
        dvList.add(driverOne);
        dvList.add(driverTwo);


        List<VehicleRequest> expectedData = Arrays.asList(


                VehicleRequest.builder()
                        .vehicles(vcList)
                        .drivers(dvList)
                        .requestStatus(RequestStatus.ACCEPTED)
                        .build(),
                VehicleRequest.builder()
                        .vehicles(vcList)
                        .drivers(dvList)
                        .requestStatus(RequestStatus.ACCEPTED)
                        .build()

        );
        when(vehicleRequestRepository.findAll()).thenReturn(expectedData);
        List<VehicleRequest> actualData = vehicleRequestServiceImpl.getData();
        assertEquals(expectedData, actualData);
    }



    @Test
    @DisplayName("Find VehicleRequest object by Ids")
    public void fetchDataByIds(){
        Vehicle vcOne = Vehicle.builder()
                .make("Toyota")
                .year(2020)
                .engineNumber("125wiws125")
                .regNumber("reksiw125")
                .vinNumber("wi152")
                .build();
        Vehicle vcTwo = Vehicle.builder()
                .make("Toyota")
                .year(2020)
                .engineNumber("125wiws125")
                .regNumber("reksiw125")
                .vinNumber("wi152")
                .build();
        List<Vehicle> vcList = new ArrayList<Vehicle>();
        vcList.add(vcOne);
        vcList.add(vcTwo);


        //Driver Object
        Driver driverOne = Driver.builder()
                .name("Mahmudul Hasan")
                .contactNumber("01704329668")
                .address("Dhaka")
                .build();
        Driver driverTwo = Driver.builder()
                .name("Mahmudul Hasan")
                .contactNumber("01704329668")
                .address("Dhaka")
                .build();

        List<Driver> dvList = new ArrayList<Driver>();
        dvList.add(driverOne);
        dvList.add(driverTwo);





             VehicleRequest expectedData =  VehicleRequest.builder()
                        .vehicles(vcList)
                        .drivers(dvList)
                        .requestStatus(RequestStatus.ACCEPTED)
                        .build();

        when(vehicleRequestRepository.findById(1L).get()).thenReturn(expectedData);
        VehicleRequest data = vehicleRequestServiceImpl.getDataById(1L);
        assertEquals(expectedData, data);
    }


    @Test
    @DisplayName("Delete VehicleRequest object by Ids")
    public void deleteDataByIds(){
        Long[] ids = new Long[]{1L};
        vehicleRequestServiceImpl.deleteByIds(ids);
        verify(vehicleRequestRepository).deleteAllById(List.of(ids));

    }

    @Test
    @DisplayName("Update VehicleRequest Object")
    public void testUpdateData() throws Exception {
        Vehicle vcOne = Vehicle.builder()
                .make("Toyota")
                .year(2020)
                .engineNumber("125wiws125")
                .regNumber("reksiw125")
                .vinNumber("wi152")
                .build();
        Vehicle vcTwo = Vehicle.builder()
                .make("Toyota")
                .year(2020)
                .engineNumber("125wiws125")
                .regNumber("reksiw125")
                .vinNumber("wi152")
                .build();
        List<Vehicle> vcList = new ArrayList<Vehicle>();
        vcList.add(vcOne);
        vcList.add(vcTwo);


        //Driver Object
        Driver driverOne = Driver.builder()
                .name("Mahmudul Hasan")
                .contactNumber("01704329668")
                .address("Dhaka")
                .build();
        Driver driverTwo = Driver.builder()
                .name("Mahmudul Hasan")
                .contactNumber("01704329668")
                .address("Dhaka")
                .build();

        List<Driver> dvList = new ArrayList<Driver>();
        dvList.add(driverOne);
        dvList.add(driverTwo);

        VehicleRequest vehicleRequest = VehicleRequest.builder()
                .vehicles(vcList)
                .drivers(dvList)
                .requestStatus(RequestStatus.ACCEPTED)
                .build();
        vehicleRequest.setId(1L);
        vehicleRequestServiceImpl.update(vehicleRequest);
        verify(vehicleRequestRepository).save(vehicleRequest);
    }

}