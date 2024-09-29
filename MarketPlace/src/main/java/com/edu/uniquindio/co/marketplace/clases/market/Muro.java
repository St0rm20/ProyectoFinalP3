package com.edu.uniquindio.co.marketplace.clases.market;

import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;

import java.util.ArrayList;

public class Muro {
    private String mensaje;
    private Vendedor vendedor;

    public Muro(String mensaje, Vendedor vendedor) {
        this.mensaje = mensaje;
        this.vendedor = vendedor;
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
