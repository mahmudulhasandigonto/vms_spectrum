package com.spectrum.spectrum_vms.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.repository.VehicleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class VehicleControllerTest {



    @Test
    void save() {

    }

    @Test
    void update() {
    }

    @Test
    void deleteByIds() {
    }

    @Test
    void getDataByIds() {
    }

    @Test
    void getData() {
    }
}