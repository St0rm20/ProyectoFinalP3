package com.edu.uniquindio.co.marketplace.clases.excepciones;

public class ContactoInexistente extends RuntimeException {
    public ContactoInexistente(String message) {
        super(message);
    }
}
