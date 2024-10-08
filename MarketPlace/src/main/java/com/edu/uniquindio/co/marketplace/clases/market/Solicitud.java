package com.edu.uniquindio.co.marketplace.clases.market;

import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;

import java.io.IOException;

public class Solicitud {
    Vendedor vendedor;
    Vendedor solicitado;

    public Solicitud(Vendedor vendedor, Vendedor solicitado) {
        this.vendedor = vendedor;
        this.solicitado = solicitado;
        solicitado.getSolicitudes().add(this);
    }

    public void aceptarSolicitud() throws IOException {
        vendedor.agregarContacto(solicitado);
        solicitado.agregarContacto(vendedor);
        solicitado.getSolicitudes().remove(this);
    }

    public void rechazarSolicitud() {
        solicitado.getContactos().remove(vendedor);
    }
    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Vendedor getSolicitado() {
        return solicitado;
    }

    public void setSolicitado(Vendedor solicitado) {
        this.solicitado = solicitado;
    }
}
