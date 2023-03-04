package com.spectrum.spectrum_vms.repository;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.serviceImplimentation.DriverServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DriverRepositoryTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverServiceImpl driverService;




    @BeforeEach
    void setUp() {
        Driver driver = Driver.builder()
                .address("Rangur")
                .name("Rohim")
                .contactNumber("0170432966")
                .build();
        driverRepository.save(driver);
    }


    @Test
    public void testFetchData(){

        List<Driver> expectedData = Arrays.asList(
                Driver.builder()
                        .name("Nobin")
                        .contactNumber("01704329668")
                        .address("Dhaka")
                        .build(),
                Driver.builder()
                        .name("Nobin")
                        .contactNumber("01704329668")
                        .address("Rangpur")
                        .build()

        );
        when(driverRepository.findAll()).thenReturn(expectedData);
        List<Driver> actualData = driverService.getData();
        assertEquals(expectedData, actualData);
    }



    @Test
    @DisplayName("Find Driver object by Ids")
    public void fetchDataByIds(){

              Driver expectedData = Driver.builder()
                        .name("Nobin")
                        .contactNumber("01704329668")
                        .address("Rangpur")
                        .build();

        when(driverRepository.findById(1L).get()).thenReturn(expectedData);
        Driver data = driverService.getDataById(1L);
        assertEquals(expectedData, data);
    }


    @Test
    @DisplayName("Delete Driver object by Ids")
    public void deleteDataByIds(){
        Long[] ids = new Long[]{1L};
        driverService.deleteByIds(ids);
        verify(driverRepository).deleteAllById(List.of(ids));

    }

    @Test
    public void testUpdateData() throws Exception {
        Driver driver = Driver.builder()
                .name("Sakira")
                .contactNumber("01704329668")
                .address("Dhaka")
                .build();
        driver.setId(1L);
        driverService.update(driver);
        verify(driverRepository).save(driver);
    }
}