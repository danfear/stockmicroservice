package com.emazon.stockmicroservice.domain.util;

public class Pagination {
    private final int page;
    private final int size;
    private final String sortBy;
    private final boolean ascending;

    public Pagination(int page, int size, String sortBy, boolean ascending) {
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.ascending = ascending;
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
}
