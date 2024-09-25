package com.emazon.stockmicroservice.domain.util;

import java.util.List;

public class PaginatedResponse<T> {
    private List<T> data;
    private int page;
    private int size;
    private String sortBy;
    private boolean ascending;
    private long totalElements;
    private int totalPages;

    public PaginatedResponse(List<T> data, int page, int size, String sortBy, boolean ascending, long totalElements, int totalPages) {
        this.data = data;
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.ascending = ascending;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public List<T> getData() {
        return data;
    }
    public int getPage() {
        return page;
    }
    public int getSize() {
        return size;
    }
    public String getSortBy() {
        return sortBy;
    }
    public boolean isAscending() {
        return ascending;
    }
    public long getTotalElements() {
        return totalElements;
    }
    public int getTotalPages() {
        return totalPages;
    }
}