package com.emazon.stockmicroservice.infrastructure.input.rest;

import com.emazon.stockmicroservice.application.dto.BrandRequest;
import com.emazon.stockmicroservice.application.handler.IBrandHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BrandRestControllerTest {
    private MockMvc mockMvc;
    @Mock
    private IBrandHandler brandHandler;
    @InjectMocks
    private BrandRestController brandRestController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(brandRestController).build();
    }

    @Test
    void when_BrandJsonIsCorrect_Expect_SaveBrandSuccesfully() throws Exception {
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Oster");
        brandRequest.setDescription("Electronic Devices");

        mockMvc.perform(post("/brands")
                        .contentType("application/json")
                        .content("{\"name\": \"Oster\", \"description\": \"Electronic Devices\"}"))
                .andExpect(status().isCreated());

        verify(brandHandler, times(1)).saveBrandInStock(any(BrandRequest.class));
    }
}