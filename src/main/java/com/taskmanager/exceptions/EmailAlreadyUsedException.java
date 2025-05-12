package com.taskmanager.exceptions;

public class EmailAlreadyUsedException extends Exception {
    public EmailAlreadyUsedException() {
    }

    public EmailAlreadyUsedException(String message) {
        super(message);
    }

    public EmailAlreadyUsedException(String message, Throwable cause) {
        super(message, cause);
    }
}
