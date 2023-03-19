package com.spectrum.spectrum_vms.controller;

import com.spectrum.spectrum_vms.repository.DriverRepository;
import com.spectrum.spectrum_vms.repository.FuelLogRepository;
import com.spectrum.spectrum_vms.repository.VehicleRepository;
import com.spectrum.spectrum_vms.repository.VehicleRequisitionRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class VehicleRequestControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private VehicleRequisitionRepository vehicleRequestRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private FuelLogRepository fuelLogRepository;
    @Autowired
    private DriverRepository driverRepository;


    @Before
    void setUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "firstName":"Mahmudul",
                                "lastName":"Rohim",
                                "email":"mahml@gmail.com",
                                "password":"123"
                                }"""))
                .andExpect(status().isOk());

    }
    @Test
    @WithMockUser(value = "mahml@gmail.com", password = "123")
    void save() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/vehicle_request/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                \t"requestDate": null,
                                \t"startDate": null,
                                \t"endDate": null,
                                "requestStatus": null
                                }"""))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "mahml@gmail.com", password = "123")
    void getData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicle_request/list"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "mahml@gmail.com", password = "123")
    void getDataByIds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicle_request/list/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "mahml@gmail.com", password = "123")
    void updateDataByIds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/vehicle_request/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "id": 1,
                                "requestDate": null,
                                "startDate": null,
                                "endDate": null,
                                "requestStatus": null
                                }"""))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "mahml@gmail.com", password = "123")
    void deleteDataByIds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/vehicle_request/delete/1"))
                .andExpect(status().isOk());
    }
}