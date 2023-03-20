package com.spectrum.spectrum_vms.service;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.repository.DriverRepository;
import com.spectrum.spectrum_vms.serviceImplimentation.DriverServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {

    @Mock
    DriverRepository driverRepository;

    DriverServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest  = new DriverServiceImpl(driverRepository);

    }




    @Test
    public void canGetAllDrivers() {
        //when
       underTest.getData();
       //then
        verify(driverRepository).findAll();

    }

    @Test
    public void canAddDriver(){
        //given
        Driver driver = Driver.builder()
                .name("Nobin")
                .address("Dhaka")
                .contactNumber("01704329668")
                .nid("955666569569")
                .age(26)
                .isAvailable(false)
                .problem("Driver is Not Available")
                .build();
        //when
        underTest.save(driver);
        //then
        ArgumentCaptor<Driver> driverArgumentCaptor = ArgumentCaptor.forClass(Driver.class);
        verify(driverRepository).save(driverArgumentCaptor.capture());

        Driver capturedDriver = driverArgumentCaptor.getValue();
        assertThat(capturedDriver).isEqualTo(capturedDriver);
    }



}