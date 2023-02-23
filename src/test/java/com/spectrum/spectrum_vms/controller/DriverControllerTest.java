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
                        "\t\t\"name\": \"Mahmudul Hasan\",\n" +
                        "\t\t\"contactNumber\": \"0178\",\n" +
                        "\t\t\"address\": \"Rangpur\",\n" +
                        "\t\t\"vehicle\": {\n" +
                        "\t\t\t\"id\": 1,\n" +
                        "\t\t\t\"make\": null,\n" +
                        "\t\t\t\"model\": null,\n" +
                        "\t\t\t\"year\": null,\n" +
                        "\t\t\t\"regNumber\": null,\n" +
                        "\t\t\t\"engineNumber\": null,\n" +
                        "\t\t\t\"vinNumber\": null\n" +
                        "\t\t}\n" +
                        "\t}"))
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
                                "\t\t\t\"id\": 1,\n" +
                                "\t\t\"name\": \"Mahmudul Hasan\",\n" +
                                "\t\t\"contactNumber\": \"0178\",\n" +
                                "\t\t\"address\": \"Rangpur\",\n" +
                                "\t\t\"vehicle\": {\n" +
                                "\t\t\t\"id\": 1,\n" +
                                "\t\t\t\"make\": \"Toyota\",\n" +
                                "\t\t\t\"model\": \"Toyota Avalon\",\n" +
                                "\t\t\t\"year\": 2020,\n" +
                                "\t\t\t\"regNumber\": \"15wksdjie\",\n" +
                                "\t\t\t\"engineNumber\": \"a23343jdf333\",\n" +
                                "\t\t\t\"vinNumber\": \"2dee\",\n" +
                                "\t\t}\n" +
                                "\t}")
                )
                .andExpect(status().isOk());
    }

    @Test
    void deleteDataByIds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/driver/delete/1"))
                .andExpect(status().isOk());
    }



}