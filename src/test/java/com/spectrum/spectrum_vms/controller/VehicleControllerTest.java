package com.spectrum.spectrum_vms.controller;


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
class VehicleControllerTest {



    @Autowired
    private MockMvc mockMvc;



    @Before
    void setUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"firstName\":\"Mahmudul\",\n" +
                                "\t\"lastName\":\"Rohim\",\n" +
                                "\t\"email\":\"mahm@gmail.com\",\n" +
                                "\t\"password\":\"123\"\n" +
                                "}"))
                .andExpect(status().isOk());

    }
    @Test
    @WithMockUser(value = "mahm@gmail.com", password = "123")
    void save() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/vehicle/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\t\"id\": 1,\n" +
                                "\t\t\"make\": \"Toyota\",\n" +
                                "\t\t\"model\": \"Toyota Mas\",\n" +
                                "\t\t\"year\": 2020,\n" +
                                "\t\t\"regNumber\": \"1520145\",\n" +
                                "\t\t\"engineNumber\": null,\n" +
                                "\t\t\"vinNumber\": null\n" +
                                "\t}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "mahm@gmail.com", password = "123")
    void getData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicle/list"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "mahm@gmail.com", password = "123")
    void getDataByIds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicle/list/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "mahm@gmail.com", password = "123")
    void updateDataByIds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/vehicle/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\t\"id\": 1,\n" +
                                "\t\t\"make\": \"Toyota\",\n" +
                                "\t\t\"model\": \"Toyota Something\",\n" +
                                "\t\t\"year\": 2020,\n" +
                                "\t\t\"regNumber\": \"1520145\",\n" +
                                "\t\t\"engineNumber\": null,\n" +
                                "\t\t\"vinNumber\": null\n" +
                                "\t}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "mahm@gmail.com", password = "123")
    void deleteDataByIds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/vehicle/delete/1"))
                .andExpect(status().isOk());
    }
}