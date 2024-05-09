package com.example.challenge.controller;

import com.example.challenge.dto.ParentDTO;
import com.example.challenge.service.impl.ParentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class ParentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ParentServiceImpl parentService;

    @Test
    @WithMockUser(username = "user")
    void givenValidParentDTO_whenAddParent_thenStatusOk() throws Exception {
        // Given
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setName("Test Parent");

        // Mock service method
        ParentDTO savedParentDTO = new ParentDTO();
        savedParentDTO.setId(1L);
        savedParentDTO.setName("Test Parent");

        when(parentService.createParent(any(ParentDTO.class))).thenReturn(savedParentDTO);

        // When
        MvcResult mvcResult = mockMvc.perform(post("/parents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parentDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        // Then
        String responseBody = mvcResult.getResponse().getContentAsString();
        ParentDTO responseParentDTO = objectMapper.readValue(responseBody, ParentDTO.class);

        // Assert response body matches savedParentDTO
        Assertions.assertEquals(savedParentDTO.getId(), responseParentDTO.getId());
        Assertions.assertEquals(savedParentDTO.getName(), responseParentDTO.getName());
    }


    @Test
    @WithMockUser(username = "user")
    @DirtiesContext
    void givenExceptionThrownByService_whenAddParent_thenStatusInternalServerError() throws Exception {
        // Given
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setName("Test Parent");

        // Mock service method to throw exception
        when(parentService.createParent(any(ParentDTO.class))).thenThrow(new RuntimeException("Error message"));

        // When/Then
        mockMvc.perform(post("/parents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parentDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMessage").exists())
                .andExpect(jsonPath("$.errorMessage").value("Internal server error: Error message"));
    }
}
