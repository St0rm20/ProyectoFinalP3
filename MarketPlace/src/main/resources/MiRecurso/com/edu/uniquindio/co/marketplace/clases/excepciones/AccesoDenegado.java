package com.edu.uniquindio.co.marketplace.clases.excepciones;

public class AccesoDenegado extends RuntimeException {
    public AccesoDenegado(String message) {
        super(message);
    }
}
