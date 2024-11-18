package com.edu.uniquindio.co.marketplace.clases.Hilos;

import com.edu.uniquindio.co.marketplace.clases.market.Producto;
import com.edu.uniquindio.co.marketplace.clases.util.Persistencia;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class ActualizadorDatos extends Thread {
    private String rutaArchivo;
    private List<Producto> datos;
    private ResourceBundle rutas = ResourceBundle.getBundle("rutas");

    public ActualizadorDatos(String rutaArchivo, List<Producto> datos) {
        this.rutaArchivo = rutaArchivo;
        this.datos = datos;
    }

    @Override
    public void run() {
            try {
                Persistencia.guardarDatos(rutas.getString("carpetaArchivos") + rutaArchivo, datos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }
}