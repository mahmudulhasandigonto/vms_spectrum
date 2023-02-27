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
class VehicleRequestControllerTest {


    @Autowired
    private MockMvc mockMvc;



    @Before
    void setUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                \t"firstName":"Mahmudul",
                                \t"lastName":"Rohim",
                                \t"email":"mahml@gmail.com",
                                \t"password":"123"
                                }"""))
                .andExpect(status().isOk());


        mockMvc.perform(MockMvcRequestBuilders.post("/vehicle/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        \t"make": "Toyota",
                        \t"model": "Toyota Mas",
                        \t"year": 2020,
                        \t"regNumber": "1520145",
                        \t"engineNumber": "something",
                        \t"vinNumber": "something"
                        }"""))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/vehicle/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                \t"make": "Toyota",
                                \t"model": "Toyota Mas",
                                \t"year": 2020,
                                \t"regNumber": "1520145",
                                \t"engineNumber": "something",
                                \t"vinNumber": "something"
                                }"""))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/driver/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                \t"name":"Nobin",
                                \t"contactNumber":"01704329668",
                                \t"address":"Dhaka"
                                }"""))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/driver/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                \t"name":"Nobin",
                                \t"contactNumber":"01704329668",
                                \t"address":"Dhaka"
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
                                \t"vehicles":[ {
                                \t\t"id":1,
                                \t\t"make": "Toyota",
                                \t\t"model": "Toyota Mas",
                                \t\t"year": 2020,
                                \t\t"regNumber": "1520145",
                                \t\t"engineNumber": "something",
                                \t\t"vinNumber": "something"
                                \t},
                                \t{
                                \t\t"id":2,
                                \t\t"make": "Toyota",
                                \t\t"model": "Toyota Mas",
                                \t\t"year": 2020,
                                \t\t"regNumber": "1520145",
                                \t\t"engineNumber": "something",
                                \t\t"vinNumber": "something"
                                \t}
                                \t\t\t\t\t\t\s
                                \t\t\t\t\t\t ],
                                \t"drivers": [
                                \t{
                                \t\t"id": 1,
                                \t\t"name": "Mah",
                                \t\t"contactNumber": "01704329668",
                                \t\t"address": "Dhaka Bangladesh"
                                \t},
                                \t{
                                \t\t"id": 2,
                                \t\t"name": "Mah",
                                \t\t"contactNumber": "01704329668",
                                \t\t"address": "Dhaka Bangladesh"
                                \t}
                                ],
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
                                "vehicles":[{
                                "id":1,
                                "make": "Toyota",
                                "model": "Toyota Mas",
                                "year": 2020,
                                "regNumber": "1520145",
                                "engineNumber": "something",
                                "vinNumber": "something"
                                },
                                {
                                "id":2,
                                "make": "Toyota",
                                "model": "Toyota Mas",
                                "year": 2020,
                                "regNumber": "1520145",
                                "engineNumber": "something",
                                "vinNumber": "something"
                                }],
                                "drivers": [
                                {
                                "id": 1,
                                "name": "Mah",
                                "contactNumber": "01704329668",
                                "address": "Dhaka Bangladesh"
                                },
                                {
                                "id": 2,
                                "name": "Mah",
                                "contactNumber": "01704329668",
                                "address": "Dhaka Bangladesh"
                                }
                                ],
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