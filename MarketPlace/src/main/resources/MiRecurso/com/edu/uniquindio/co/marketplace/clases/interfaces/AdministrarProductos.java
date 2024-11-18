package com.edu.uniquindio.co.marketplace.clases.interfaces;

import com.edu.uniquindio.co.marketplace.clases.market.Comentario;
import com.edu.uniquindio.co.marketplace.clases.market.Producto;

import java.util.ArrayList;

public interface AdministrarProductos {
    void eliminarProducto(Producto producto);
    ArrayList<Producto> obtenerProducto();
}
