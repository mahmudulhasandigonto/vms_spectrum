package com.spectrum.spectrum_vms.service;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.repository.DriverRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class DriverServiceTest {

    @MockBean
    DriverRepository driverRepository;
    @Autowired
    DriverService driverService;

    @BeforeEach
    void setUp() {
        Vehicle vc = new Vehicle();

        Driver driver = Driver.builder()
                .address("Rangur")
                .name("Rohim")
                .contactNumber("0170432966")
                .vehicle(vc)
                .build();
        Mockito.when(driverRepository.findByNameIgnoreCase("Rohim"))
                .thenReturn(driver);


    }

    @Test
    @DisplayName("Get data based on valid driver name")
    public void whenValidDriverName_thenDriverShouldFound(){
        String driverName = "Rohim";
        Driver found = driverService.findByDriverNameIgnoreCase(driverName);
        assertEquals(driverName, found.getName());
    }


}