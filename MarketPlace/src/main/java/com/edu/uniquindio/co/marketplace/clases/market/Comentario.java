package com.edu.uniquindio.co.marketplace.clases.market;

import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;

import java.time.LocalDateTime;

public class Comentario {

    private String contenido;
    private LocalDateTime fecha;
    private Vendedor autor;

    public Comentario(String contenido, Vendedor autor) {
        this.contenido = contenido;
        this.autor = autor;
        this.fecha = LocalDateTime.now();
    }


    //----------------------------Gets y Sets-------------------------------------

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

    public Vendedor getAutor() {
        return autor;
    }

    public void setAutor(Vendedor autor) {
        this.autor = autor;
    }
}
