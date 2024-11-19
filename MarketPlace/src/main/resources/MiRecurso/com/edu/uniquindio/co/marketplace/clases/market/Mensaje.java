package com.edu.uniquindio.co.marketplace.clases.market;

import java.time.LocalDateTime;

public class Mensaje {
    private String contenido;
    private LocalDateTime fecha;
    private static final long serialVersionUID = 1L;

    public Mensaje(String contenido) {
        this.contenido = contenido;
        this.fecha = LocalDateTime.now();
    }

    public Mensaje(){

    }



    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
