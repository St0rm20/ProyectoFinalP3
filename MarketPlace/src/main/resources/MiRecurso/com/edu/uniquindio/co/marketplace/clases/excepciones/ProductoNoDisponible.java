package com.edu.uniquindio.co.marketplace.clases.excepciones;

public class ProductoNoDisponible extends RuntimeException {
    public ProductoNoDisponible(String message) {
        super(message);
    }
}
