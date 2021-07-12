package com.example.demo.controller.advice;

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
public class SpringControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity(body, HttpStatus.OK);
    }

    @ExceptionHandler()
    protected ResponseEntity handleConflict(RuntimeException ex, WebRequest request) {
        if ((request.getHeader("Accept") + "").contains("text/html")) {
            return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.OK, request);
        } else {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/json");
            return handleExceptionInternal(ex, ApiController.json(false, null, ex.getMessage()), httpHeaders, HttpStatus.OK, request);
        }

    }
}

