/*
 * Copyright (c) 2020. overrideeg.ocm.
 */

package com.musala.dronetask.ui.sys;


import com.musala.dronetask.exceptions.AuthenticationException;
import com.musala.dronetask.exceptions.InvalidJwtAuthenticationException;
import com.musala.dronetask.exceptions.NoRecordFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    @ResponseStatus(UNAUTHORIZED)
    @ResponseBody
    public Object InvalidJwtAuthenticationException(InvalidJwtAuthenticationException ex) throws IOException {
        ApiError apiError =
                new ApiError(UNAUTHORIZED, ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()),UNAUTHORIZED.value());
        return  apiError;
    }

    @ExceptionHandler(NoRecordFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public Object NoRecordFoundException(NoRecordFoundException ex) throws IOException {
        ApiError apiError =
                new ApiError(BAD_REQUEST, ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()),BAD_REQUEST.value());
        return  apiError;
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), Arrays.asList(ex.getMessage()),HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
                List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add( fieldName+": "+ errorMessage);
        });
        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, errors.get(0), errors,HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(apiError, status);
    }



    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(UNAUTHORIZED)
    @ResponseBody
    public ApiError AuthenticationException(AuthenticationException ex) throws IOException {
     ApiError apiError =
             new ApiError(UNAUTHORIZED, ex.getLocalizedMessage(), Arrays.asList(ex.getMessage()),UNAUTHORIZED.value());
        return  apiError;
    }



    @ExceptionHandler(ConstraintViolationException.class )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError handleConstraintViolation(
            ConstraintViolationException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors,HttpStatus.BAD_REQUEST.value());
        return apiError;
    }



}
