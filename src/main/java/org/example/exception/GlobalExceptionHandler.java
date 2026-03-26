package org.example.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuantityMeasurementException.class)
    public ResponseEntity<String> handleException(QuantityMeasurementException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}