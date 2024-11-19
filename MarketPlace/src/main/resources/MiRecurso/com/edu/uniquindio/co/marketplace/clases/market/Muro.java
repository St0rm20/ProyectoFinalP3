package com.edu.uniquindio.co.marketplace.clases.market;

import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;

import java.io.Serializable;
import java.util.ArrayList;

public class Muro implements Serializable {
    private String mensaje;
    private Vendedor vendedor;
    private static final long serialVersionUID = 1L;

    public Muro(String mensaje, Vendedor vendedor) {
        this.mensaje = mensaje;
        this.vendedor = vendedor;
    }

    public Muro(){

    }


    //----------------------------Gets y Sets-------------------------------------

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public ArrayList<Comentario> obtenerComentarios() {
        return vendedor.getListaComentarios();
    }

    public ArrayList<Producto> obtenerLikes(){
        return vendedor.getLikes();
    }
}
