package com.skpijtk.springboot_boilerplate.exception;

public class NimAlreadyExistsException extends RuntimeException {
    public NimAlreadyExistsException(String message) {
        super(message);
    }
}