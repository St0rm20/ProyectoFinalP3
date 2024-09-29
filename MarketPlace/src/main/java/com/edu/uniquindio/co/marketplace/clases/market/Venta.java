package com.edu.uniquindio.co.marketplace.clases.market;

import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;

import java.util.ArrayList;

public class Venta {
    private Producto producto;
    private Vendedor comprador;

    public Venta(Producto producto, Vendedor comprador) {
        this.producto = producto;
        this.comprador = comprador;
        producto.getVendedor().getVentas().add(this);
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
