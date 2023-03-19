package com.spectrum.spectrum_vms.repository;

import com.spectrum.spectrum_vms.entity.Driver;
import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.entity.VehicleRequisition;
import com.spectrum.spectrum_vms.enums.RequestStatus;
import com.spectrum.spectrum_vms.user.User;
import com.spectrum.spectrum_vms.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class VehicleRequisitionRepositoryTest {


    @Autowired
    VehicleRequisitionRepository vehicleRequisitionRepository;
    @Autowired
    private UserRepository userRepository;

    User userDemo;

    @BeforeEach
    void setUp() {


        User user = User.builder()
                .firstName("Mahmudul Hasan")
                .lastName("Hasan")
                .email("mah@gmail.com")
                .password("1256")
                .build();
        userDemo = userRepository.save(user);


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
                .name("Nobin")
                .address("Dhaka")
                .contactNumber("01704329668")
                .nid("955666569569")
                .age(26)
                .isAvailable(false)
                .problem("Driver is Not Available")
                .build();
        Driver driverTwo = Driver.builder()
                .name("Nobin")
                .address("Dhaka")
                .contactNumber("01704329555")
                .nid("955666569569")
                .age(26)
                .isAvailable(false)
                .problem("Driver is Not Available")
                .build();

        List<Driver> dvList = new ArrayList<Driver>();
        dvList.add(driverOne);
        dvList.add(driverTwo);


        VehicleRequisition vehicleRequisition = VehicleRequisition.builder()
                .user(userDemo)
                .vehicles(vcList)
                .drivers(dvList)
                .destination("Dhaka")
                .departure("Rangpur")
                .requestStatus(RequestStatus.ACCEPTED)
                .remark("I want two car")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .requestDate(LocalDateTime.now())
                .requestStatus(RequestStatus.ACCEPTED)
                .build();

        vehicleRequisitionRepository.save(vehicleRequisition);


    }

    @AfterEach
    void tearDown() {
        vehicleRequisitionRepository.deleteAll();
    }

    @Test
    void findByUserId() {
        List<VehicleRequisition> expected = vehicleRequisitionRepository.findByUserId(userDemo.getId());

        assertThat(expected.isEmpty()).isFalse();
    }

    @Test
    void findByRequestStatus() {
        List<VehicleRequisition> expected = vehicleRequisitionRepository.findByRequestStatus(RequestStatus.ACCEPTED);
        assertThat(expected.isEmpty()).isFalse();
    }
}