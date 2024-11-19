package com.edu.uniquindio.co.marketplace.clases.excepciones;

public class ProductoNoEncontrado extends RuntimeException {
    public ProductoNoEncontrado(String message) {
        super(message);
    }
}
