package com.organicautonomy.reviewservice.controller;

import com.organicautonomy.reviewservice.exception.ErrorDetails;
import com.organicautonomy.reviewservice.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleValidationError(MethodArgumentNotValidException e, WebRequest request) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorDetails> errors = new ArrayList<>();

        Date date = new Date();
        for (FieldError fieldError: fieldErrors) {
            String message = fieldError.getField() + ": " + fieldError.getDefaultMessage();
            ErrorDetails error = new ErrorDetails(date, message, request.getDescription(false));
            errors.add(error);
        }

        return new ResponseEntity(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
