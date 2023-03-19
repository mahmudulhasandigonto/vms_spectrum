package com.spectrum.spectrum_vms.service;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.repository.DriverRepository;
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
        String contactNumber = "01704329668";
        Driver driver = Driver.builder()
                .address("Rangur")
                .name("Rohim")
                .contactNumber(contactNumber)
                .build();
        Mockito.when(driverRepository.findByContactNumber(contactNumber))
                .thenReturn(driver);

    }

    @Test
    @DisplayName("Get data based on valid driver contact number")
    public void whenValidContactNumberNumber_thenDriverShouldFound() {
        String contactNumber = "01704329668";
        Driver found = driverService.findByContactNumber(contactNumber);
        assertEquals(contactNumber, found.getContactNumber());
    }

}