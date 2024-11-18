package com.edu.uniquindio.co.marketplace.clases.interfaces;

import com.edu.uniquindio.co.marketplace.clases.market.Producto;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;

import java.time.LocalDate;
import java.util.ArrayList;

public interface Estadistica {
    int obtenerCantidadMensajesEnviados(Vendedor vendedor, Vendedor vendedor2);
    int obtenerCantidadProductosPublicadosPorFecha(LocalDate fecha);
    int obtenerCantidadProductosPublicadosPorVendedor(Vendedor vendedor);
    int obtenerCantidadContactosVendedor(Vendedor vendedor);
    ArrayList<Producto> obtenerProductosMasGustados();
}
