package com.project.backend.exception;

public class UserRegisterNotAllowedByBirthException extends RuntimeException {

    public UserRegisterNotAllowedByBirthException(String message) {
        super(message);
    }
}
