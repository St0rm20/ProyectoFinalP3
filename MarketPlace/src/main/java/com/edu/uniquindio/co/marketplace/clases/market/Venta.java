package com.edu.uniquindio.co.marketplace.clases.market;

import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;


import java.io.Serializable;


public class Venta implements Serializable {
    private Producto producto;
    private Vendedor comprador;
    private static final long serialVersionUID = 1L;

    public Venta(Producto producto, Vendedor comprador) {
        this.producto = producto;
        this.comprador = comprador;
        producto.getVendedor().getVentas().add(this);
    }

    public Venta(){

    }

    public Producto getProducto() {
        return producto;
    }

    public Vendedor getVendedor() {
        return producto.getVendedor();
    }

    public Vendedor getComprador() {
        return comprador;
    }

    public void setComprador(Vendedor comprador) {
        this.comprador = comprador;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
