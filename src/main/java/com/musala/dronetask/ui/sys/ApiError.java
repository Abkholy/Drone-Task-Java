package com.musala.dronetask.ui.sys;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;
    private int statusCode;

    public ApiError(HttpStatus status, String message, List<String> errors,int statusCode) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.statusCode= statusCode;
    }

    public ApiError(HttpStatus status, String message, String error,int statusCode) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
        this.statusCode= statusCode;

    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}