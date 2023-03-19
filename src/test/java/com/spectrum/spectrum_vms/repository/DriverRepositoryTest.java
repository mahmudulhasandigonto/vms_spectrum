package com.spectrum.spectrum_vms.repository;

import com.spectrum.spectrum_vms.entity.Driver;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class DriverRepositoryTest {

    @Autowired
    private DriverRepository driverRepository;





    @BeforeEach
    void setUp() {
        Driver driver = Driver.builder()
                .name("Nobin")
                .address("Dhaka")
                .contactNumber("01704329668")
                .nid("955666569569")
                .age(26)
                .isAvailable(false)
                .problem("Driver is Not Available")
                .build();

        driverRepository.save(driver);

    }

    @AfterEach
    void tearDown(){
        driverRepository.deleteAll();
    }


    @Test
    void findByContactNumber() {
        //given
        String contactNumber = "01704329668";

        //when
        Driver expected =  driverRepository.findByContactNumber(contactNumber);

        //then
        assertThat(expected.getContactNumber().equals(contactNumber));

    }


    @Test
    void findByIsAvailable() {
        //given
        Boolean isAvailable = true;

        //when
        List<Driver> expected =  driverRepository.findByIsAvailable(isAvailable);

        //then
        assertThat(expected.isEmpty()).isTrue();
    }

    @Test
    void findByProblemIsNotNull() {
        //given
        String contactNumber = "01704329668";

        //when
        List<Driver> expected =  driverRepository.findByProblemIsNotNull();

        //then
        assertThat(expected.isEmpty()).isFalse();
    }
}