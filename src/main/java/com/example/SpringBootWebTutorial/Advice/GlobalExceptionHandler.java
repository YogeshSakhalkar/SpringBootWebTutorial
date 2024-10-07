package com.example.SpringBootWebTutorial.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<String> handleResourceNotFound(NoSuchElementException exception){
        return new ResponseEntity<>("Resource Not Found", HttpStatus.NOT_FOUND);
    }
}
