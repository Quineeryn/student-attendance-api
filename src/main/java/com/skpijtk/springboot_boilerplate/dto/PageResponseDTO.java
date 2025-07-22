package com.skpijtk.springboot_boilerplate.dto;

import java.util.List;

public class PageResponseDTO<T> {

    private List<T> data;
    private long totalData;
    private int totalPage;
    private int currentPage;
    private int pageSize;

    // Constructor
    public PageResponseDTO(List<T> data, long totalData, int totalPage, int currentPage, int pageSize) {
        this.data = data;
        this.totalData = totalData;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public PageResponseDTO() {
    }

    // Getters and Setters
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotalData() {
        return totalData;
    }

    public void setTotalData(long totalData) {
        this.totalData = totalData;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}