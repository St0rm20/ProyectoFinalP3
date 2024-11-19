package com.edu.uniquindio.co.marketplace.clases.excepciones;

public class TransacccionFallida extends RuntimeException {
    public TransacccionFallida(String message) {
        super(message);
    }
}
