package com.emazon.stockmicroservice.infrastructure.input.rest;

import com.emazon.stockmicroservice.application.dto.BrandRequest;
import com.emazon.stockmicroservice.application.dto.BrandResponse;
import com.emazon.stockmicroservice.domain.util.PaginatedResponse;
import com.emazon.stockmicroservice.application.handler.IBrandHandler;

import com.emazon.stockmicroservice.domain.util.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    void when_BrandJsonIsCorrect_Expect_SaveBrandSuccessfully() throws Exception {
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.setName("Oster");
        brandRequest.setDescription("Electronic Devices");

        mockMvc.perform(post("/brands")
                        .contentType("application/json")
                        .content("{\"name\": \"Oster\", \"description\": \"Electronic Devices\"}"))
                .andExpect(status().isCreated());

        verify(brandHandler, times(1)).saveBrandInStock(any(BrandRequest.class));
    }

    @Test
    void when_ParametersAreCorrect_Expect_GetAllBrandsFromStockSuccessfully() throws Exception {
        BrandResponse brandResponse1 = new BrandResponse();
        brandResponse1.setName("Oster");
        brandResponse1.setDescription("Electronic Devices");

        PaginatedResponse<BrandResponse> paginatedResponse = new PaginatedResponse<>(
                Collections.singletonList(brandResponse1),
                0,
                5,
                "name",
                true,
                1,
                1
        );

        when(brandHandler.getAllBrandsFromStock(any(Pagination.class))).thenReturn(paginatedResponse);

        mockMvc.perform(get("/brands")
                        .param("page", "0")
                        .param("size", "5")
                        .param("sortBy", "name")
                        .param("ascending", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("Oster"))
                .andExpect(jsonPath("$.data[0].description").value("Electronic Devices"))
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(5))
                .andExpect(jsonPath("$.sortBy").value("name"))
                .andExpect(jsonPath("$.ascending").value(true))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.totalPages").value(1));
    }
}