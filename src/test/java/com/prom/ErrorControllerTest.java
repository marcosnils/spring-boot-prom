package com.prom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testInternalError() throws Exception {
        this.mockMvc.perform(get("/500")).andExpect(status().isInternalServerError());
        this.mockMvc.perform(get("/prometheus")).andExpect(content().string(containsString("status=\"500\"")));
    }

    @Test
    public void testException() throws Exception {
        try {
            this.mockMvc.perform(get("/exc")).andExpect(status().isInternalServerError());
        } catch (Exception re) {}
        this.mockMvc.perform(get("/metrics")).andExpect(content().string(containsString("500.exc")));
        this.mockMvc.perform(get("/prometheus")).andExpect(content().string(containsString("status=\"500\"")));
    }


}