package com.spectrum.spectrum_vms.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void save() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/driver/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"name\": \"Mahmudul Hasan\",\n" +
                        "\t\"contactNumber\": \"017043299\",\n" +
                        "\t\"address\": \"Dhaka\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void getData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/driver/list"))
                .andExpect(status().isOk());
    }

    @Test
    void getDataByIds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/driver/list/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateDataByIds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/driver/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\t\"id\": 1,\n" +
                        "\t\t\"name\": \"Mahmudul Hasan\",\n" +
                        "\t\t\"contactNumber\": \"017043299\",\n" +
                        "\t\t\"address\": \"Dhaka\"\n" +
                        "\t}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteDataByIds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/driver/delete/1"))
                .andExpect(status().isOk());
    }

}