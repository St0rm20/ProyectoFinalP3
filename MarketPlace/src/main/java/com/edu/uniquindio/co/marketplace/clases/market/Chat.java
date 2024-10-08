package com.edu.uniquindio.co.marketplace.clases.market;

import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;

import java.util.ArrayList;

public class Chat {
    private ArrayList<Mensaje> mensajes;
    private Vendedor vendedor;
    private Vendedor vendedor2;

    public Chat(Vendedor vendedor, Vendedor vendedor2) {
        this.mensajes = new ArrayList<>();
        this.vendedor = vendedor;
        this.vendedor2 = vendedor2;
        vendedor.getChats().add(this);
        vendedor2.getChats().add(this);
    }

    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Mensaje> mensajes) {
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

    public void enviarMensaje(String mensaje){
        mensajes.add(new Mensaje(mensaje));
    }
}
