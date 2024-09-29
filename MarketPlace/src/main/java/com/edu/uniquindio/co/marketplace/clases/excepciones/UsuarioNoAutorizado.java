package com.edu.uniquindio.co.marketplace.clases.excepciones;

public class UsuarioNoAutorizado extends RuntimeException {
    public UsuarioNoAutorizado(String message) {
        super(message);
    }
}
