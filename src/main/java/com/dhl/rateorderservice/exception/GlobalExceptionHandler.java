package com.dhl.rateorderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(OrderNotFoundException ex){
        ApiError error = new ApiError(404, ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex){
        String msg = ex.getBindingResult().getFieldErrors().stream().
                map(fe -> fe.getField() + ": " + fe.getDefaultMessage()).
                collect(Collectors.joining("; "));
        ApiError error = new ApiError(400, "Validation failed - " + msg);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex){
        ApiError error = new ApiError(500, "Internal error try again later.");
        return ResponseEntity.internalServerError().body(error);
    }



}
