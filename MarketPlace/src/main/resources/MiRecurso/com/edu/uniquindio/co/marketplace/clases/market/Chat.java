package com.edu.uniquindio.co.marketplace.clases.market;

import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;

import java.io.Serializable;
import java.util.ArrayList;

public class Chat implements Serializable {
    private String mensajes ="";
    private Vendedor vendedor;
    private Vendedor vendedor2;
    private static final long serialVersionUID = 1L;

    public Chat(Vendedor vendedor, Vendedor vendedor2) {
        this.vendedor = vendedor;
        this.vendedor2 = vendedor2;
        vendedor.getChats().add(this);
        vendedor2.getChats().add(this);
    }

    public Chat(){

    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Vendedor getVendedor2() {
        return vendedor2;
    }

    public void setVendedor2(Vendedor vendedor2) {
        this.vendedor2 = vendedor2;
    }


}
