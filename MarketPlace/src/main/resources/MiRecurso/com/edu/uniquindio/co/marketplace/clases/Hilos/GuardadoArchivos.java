package com.edu.uniquindio.co.marketplace.clases.Hilos;

import com.edu.uniquindio.co.marketplace.clases.util.Utilities;

import java.io.IOException;

public class GuardadoArchivos extends Thread{

    private String ruta;
    private  Object objeto;


    public GuardadoArchivos(String ruta, Object objeto){
        this.ruta =ruta;
        this.objeto = objeto;
    }

    @Override
    public void run(){
        try {
            Utilities.serializarObjetoXML(ruta + ".xml", objeto);
            Utilities.serializarObjetoBinario(ruta+ ".dat", objeto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
