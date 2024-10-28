// TodoNotFoundException.java

package com.crio.task_box_backend.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(String message) {
        super(message);
    }
}