package com.example.demo.controller;

import com.example.demo.core.iservice.ApiController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity(body, HttpStatus.OK);
    }

    @ExceptionHandler()
    protected ResponseEntity handleConflict(RuntimeException ex, WebRequest request) {
        if (request.getContextPath().startsWith("/api")) {
            return handleExceptionInternal(ex, ApiController.json(false, null, ex.getMessage()), new HttpHeaders(), HttpStatus.OK, request);
        }
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.OK, request);
    }
}

