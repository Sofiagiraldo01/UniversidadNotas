package com.universidad.exception;

public class NotaDuplicadaException extends RuntimeException {

    public NotaDuplicadaException(String mensaje) {
        super(mensaje);
    }
}