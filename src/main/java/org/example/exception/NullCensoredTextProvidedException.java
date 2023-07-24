package org.example.exception;

public class NullCensoredTextProvidedException extends RuntimeException {
    public NullCensoredTextProvidedException(String message) {
        super(message);
    }
}
