package com.emazon.stockmicroservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
@Getter
@AllArgsConstructor
public class PaginatedResponse<T> {
    private List<T> data;
    private int page;
    private int size;
    private String sortBy;
    private boolean ascending;
    private long totalElements;
    private int totalPages;
}