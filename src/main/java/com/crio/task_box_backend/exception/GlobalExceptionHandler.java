package com.crio.task_box_backend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // Marks this class as a global exception handler
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Handles TodoNotFoundException and returns a 404 Not Found response
    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<Object> handleTodoNotFoundException(TodoNotFoundException ex, WebRequest request) {
        // Create a custom response entity with the exception message and status code
        return handleExceptionInternal(ex, ex.getMessage(), 
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // Overrides the default handler for MethodArgumentNotValidException (validation errors)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        // Create a map to store the field errors
        Map<String, String> errors = new HashMap<>();
        // Iterate over the validation errors and add them to the map
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = error.getObjectName(); // Get the field name
            String errorMessage = error.getDefaultMessage(); // Get the error message
            errors.put(fieldName, errorMessage); // Add the error to the map
        });
        // Return a Bad Request response with the error map
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }
}