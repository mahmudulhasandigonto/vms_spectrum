package com.spectrum.spectrum_vms.controller;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
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
class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @BeforeEach
    void setUp() throws Exception {
     mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "firstName":"Mahmudul",
                        "lastName":"Rohim",
                        "email":"mahm@gmail.com",
                        "password":"123"
                        }"""))
                .andExpect(status().isOk());

    }
    @Test
    @WithMockUser(value = "mahml@gmail.com", password = "123")
    void save() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/driver/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "Mahmudul Hasan",
                        "contactNumber": "017043299",
                        "address": "Dhaka"
                        }"""))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(value = "mahml@gmail.com", password = "123")
    void getData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/driver/list"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "mahml@gmail.com", password = "123")
    void getDataByIds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/driver/list/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "mahml@gmail.com", password = "123")
    void updateDataByIds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/driver/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "id": 1,
                        "name": "Mahmudul Hasan",
                        "contactNumber": "017043299",
                        "address": "Dhaka"
                        }"""))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "mahml@gmail.com", password = "123")
    void deleteDataByIds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/driver/delete/1"))
                .andExpect(status().isOk());
    }




}