package com.emazon.stockmicroservice.infrastructure.input.rest;

import com.emazon.stockmicroservice.application.dto.CategoryRequest;
import com.emazon.stockmicroservice.application.dto.CategoryResponse;
import com.emazon.stockmicroservice.domain.util.PaginatedResponse;
import com.emazon.stockmicroservice.application.handler.ICategoryHandler;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryRestControllerTest {

    private MockMvc mockMvc;
    @Mock
    private ICategoryHandler categoryHandler;
    @InjectMocks
    private CategoryRestController categoryRestController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryRestController).build();
    }

    @Test
    void when_CategoryJsonIsCorrect_Expect_SaveCategorySuccesfully() throws Exception {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Electronics");
        categoryRequest.setDescription("Devices and gadgets");

        mockMvc.perform(post("/categories")
                        .contentType("application/json")
                        .content("{\"name\": \"Electronics\", \"description\": \"Devices and gadgets\"}"))
                .andExpect(status().isCreated());

        verify(categoryHandler, times(1)).saveCategoryInStock(any(CategoryRequest.class));
    }

    @Test
    void when_ParametersAreCorrect_Expect_GetAllCategoriesFromStockSuccesfully() throws Exception {
        CategoryResponse categoryResponse1 = new CategoryResponse();
        categoryResponse1.setName("Electronics");
        categoryResponse1.setDescription("Devices and gadgets");

        PaginatedResponse<CategoryResponse> paginatedResponse = new PaginatedResponse<>(
                Collections.singletonList(categoryResponse1),
                0,
                5,
                "name",
                true,
                1,
                1
        );

        when(categoryHandler.getAllCategoriesFromStock(any(Pagination.class))).thenReturn(paginatedResponse);

        mockMvc.perform(get("/categories")
                        .param("page", "0")
                        .param("size", "5")
                        .param("sortBy", "name")
                        .param("ascending", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("Electronics"))
                .andExpect(jsonPath("$.data[0].description").value("Devices and gadgets"))
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(5))
                .andExpect(jsonPath("$.sortBy").value("name"))
                .andExpect(jsonPath("$.ascending").value(true))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.totalPages").value(1));
    }
}