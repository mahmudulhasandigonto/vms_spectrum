package com.spectrum.spectrum_vms.repository;

import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void setUp(){
      Vehicle vehicleOne =  Vehicle.builder()
              .make("Toyota")
              .engineNumber("ncwepe22")
              .model("Toyota hycy")
              .modelYear(1200)
              .isAvailable(Boolean.FALSE)
              .regNumber("504w25e")
              .problem("Car has a problem")
              .vinNumber("152w2eee")
              .build();

        vehicleRepository.save(vehicleOne);

    }



    @AfterEach
    void tearDown() {
        vehicleRepository.deleteAll();
    }

    @Test
    void findByIsAvailable() {
      List<Vehicle> expected =  vehicleRepository.findByIsAvailable(true);

      assertThat(expected.isEmpty()).isTrue();
    }

    @Test
    void findByProblemIsNotNull() {
        List<Vehicle> expected = vehicleRepository.findByProblemIsNotNull();

        assertThat(expected.isEmpty()).isFalse();
    }
}