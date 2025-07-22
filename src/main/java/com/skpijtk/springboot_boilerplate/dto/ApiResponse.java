// File: src/main/java/com/skpijtk/springboot_boilerplate/dto/ApiResponse.java
package com.skpijtk.springboot_boilerplate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private T data;
    private String message;
    private int statusCode;
    private String status;

    public ApiResponse(T data, String message, int statusCode, String status) {
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
        this.status = status;
    }

    // Getters and Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}