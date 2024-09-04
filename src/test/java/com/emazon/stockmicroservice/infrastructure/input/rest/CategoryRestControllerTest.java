package com.emazon.stockmicroservice.infrastructure.input.rest;

import com.emazon.stockmicroservice.application.dto.CategoryRequest;
import com.emazon.stockmicroservice.application.exception.GlobalExceptionHandler;
import com.emazon.stockmicroservice.application.handler.categoryhandler.ICategoryHandler;
import com.emazon.stockmicroservice.domain.exception.NameEmptyException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ICategoryHandler saveCategoryHandler;

    @InjectMocks
    private CategoryRestController categoryRestController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryRestController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void testSaveCategoryInStock_Success() throws Exception {
        // Given
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Electronics");
        categoryRequest.setDescription("Devices");

        doNothing().when(saveCategoryHandler).saveCategoryInStock(categoryRequest);
        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/category/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(categoryRequest)))
                .andExpect(status().isCreated())
                .andReturn();
        // Then
        verify(saveCategoryHandler, times(1)).saveCategoryInStock(categoryRequest);
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    void testSaveCategoryInStock_Failure() throws Exception {
        // Given
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Electronics");
        categoryRequest.setDescription("Devices");

        doThrow(new NameEmptyException()).when(saveCategoryHandler).saveCategoryInStock(categoryRequest);
        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/category/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(categoryRequest)))
                .andExpect(status().isBadRequest())
                .andReturn();
        // Then
        verify(saveCategoryHandler, times(1)).saveCategoryInStock(categoryRequest);
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());

        String responseBody = result.getResponse().getContentAsString();
        assertTrue(responseBody.contains("Validation Error"));
        assertTrue(responseBody.contains("Name cannot be empty"));
    }

    @Test
    void when_JSONIsEmpty_Expect_ThrowsInvalidRequest() throws Exception {
        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/category/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // Empty JSON
                .andExpect(status().isBadRequest())
                .andReturn();
        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    // Helper method to convert objects to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}