package com.spectrum.spectrum_vms.controller;

import com.spectrum.spectrum_vms.entity.FuelLog;
import com.spectrum.spectrum_vms.entity.Vehicle;
import com.spectrum.spectrum_vms.enums.FuelType;
import com.spectrum.spectrum_vms.service.FuelLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FuelLogControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void save(){
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