package com.peaktech.pnp.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Senha Inválida");
    }
}