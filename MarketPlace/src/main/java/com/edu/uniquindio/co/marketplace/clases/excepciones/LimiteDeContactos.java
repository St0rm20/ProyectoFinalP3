package com.edu.uniquindio.co.marketplace.clases.excepciones;

public class LimiteDeContactos extends RuntimeException {
    public LimiteDeContactos(String message) {
        super(message);
    }
}
