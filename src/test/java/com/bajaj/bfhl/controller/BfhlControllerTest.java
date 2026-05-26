package com.bajaj.bfhl.controller;

import com.bajaj.bfhl.dto.BfhlRequestDTO;
import com.bajaj.bfhl.dto.BfhlResponseDTO;
import com.bajaj.bfhl.service.BfhlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for BfhlController.
 * Tests HTTP endpoints and response structure.
 */
@WebMvcTest(BfhlController.class)
class BfhlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BfhlService bfhlService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /bfhl returns operation_code = 1")
    void testGetOperationCode() throws Exception {
        mockMvc.perform(get("/bfhl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation_code").value(1));
    }

    @Test
    @DisplayName("POST /bfhl returns 200 with correct response structure")
    void testPostProcessData() throws Exception {
        BfhlResponseDTO mockResponse = new BfhlResponseDTO();
        mockResponse.setSuccess(true);
        mockResponse.setUserId("hemlata_kumawat_31052006");
        mockResponse.setEmail("hemlatakumawat231001@acropolis.in");
        mockResponse.setRollNumber("0827IT231052");
        mockResponse.setOddNumbers(List.of("1"));
        mockResponse.setEvenNumbers(List.of("334", "4"));
        mockResponse.setAlphabets(List.of("A", "R"));
        mockResponse.setSpecialCharacters(List.of("$"));
        mockResponse.setSum("339");
        mockResponse.setConcatString("Ra");

        when(bfhlService.processData(any(BfhlRequestDTO.class))).thenReturn(mockResponse);

        String requestBody = objectMapper.writeValueAsString(
                new BfhlRequestDTO(Arrays.asList("a", "1", "334", "4", "R", "$")));

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.user_id").value("hemlata_kumawat_31052006"))
                .andExpect(jsonPath("$.email").value("hemlatakumawat231001@acropolis.in"))
                .andExpect(jsonPath("$.roll_number").value("0827IT231052"))
                .andExpect(jsonPath("$.odd_numbers[0]").value("1"))
                .andExpect(jsonPath("$.even_numbers[0]").value("334"))
                .andExpect(jsonPath("$.even_numbers[1]").value("4"))
                .andExpect(jsonPath("$.alphabets[0]").value("A"))
                .andExpect(jsonPath("$.alphabets[1]").value("R"))
                .andExpect(jsonPath("$.special_characters[0]").value("$"))
                .andExpect(jsonPath("$.sum").value("339"))
                .andExpect(jsonPath("$.concat_string").value("Ra"));
    }

    @Test
    @DisplayName("POST /bfhl with null data returns 400")
    void testPostWithNullData() throws Exception {
        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /bfhl with empty body returns 400")
    void testPostWithEmptyBody() throws Exception {
        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }
}
