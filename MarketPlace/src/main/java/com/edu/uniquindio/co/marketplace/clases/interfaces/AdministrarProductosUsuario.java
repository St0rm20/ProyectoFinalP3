package com.edu.uniquindio.co.marketplace.clases.interfaces;

import com.edu.uniquindio.co.marketplace.clases.enums.EstadoProducto;
import com.edu.uniquindio.co.marketplace.clases.market.Producto;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;

import java.io.IOException;

public interface AdministrarProductosUsuario {
    void agregarProducto( String nombre, String descripcion, String imagen, String categoria, double precio, EstadoProducto estado);
    void eliminarProducto(Producto producto) throws IOException;
    void modificarProducto(Producto producto, Producto nuevoProducto);
}
