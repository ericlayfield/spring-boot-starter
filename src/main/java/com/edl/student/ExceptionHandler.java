package com.edl.student;

import com.edl.student.model.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleException(
            Exception ex, WebRequest request) throws Exception {
        if (ex instanceof StudentNotFoundException) {
            return  new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
