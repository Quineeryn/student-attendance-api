// File: src/main/java/com/proyekmu/attendanceapp/exception/EmailAlreadyExistsException.java
package com.skpijtk.springboot_boilerplate.exception; // Sesuaikan package

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}