package com.kayal.ageapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kayal.ageapi.dto.AgeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AgeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAgeForValidDateOfBirth() throws Exception {
        String dateOfBirth = LocalDate.now().minusYears(25).minusMonths(2).toString();

        mockMvc.perform(get("/api/v1/age")
                        .param("dateOfBirth", dateOfBirth))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dateOfBirth").value(dateOfBirth))
                .andExpect(jsonPath("$.age").isNumber());
    }

    @Test
    void shouldReturnAgeForPostRequest() throws Exception {
        AgeRequest request = new AgeRequest(LocalDate.now().minusYears(30).minusDays(10));

        mockMvc.perform(post("/api/v1/age")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dateOfBirth").value(request.dateOfBirth().toString()))
                .andExpect(jsonPath("$.age").isNumber());
    }

    @Test
    void shouldRejectFutureDateOfBirth() throws Exception {
        String futureDate = LocalDate.now().plusDays(1).toString();

        mockMvc.perform(get("/api/v1/age")
                        .param("dateOfBirth", futureDate))
                .andExpect(status().isBadRequest());
    }
}
