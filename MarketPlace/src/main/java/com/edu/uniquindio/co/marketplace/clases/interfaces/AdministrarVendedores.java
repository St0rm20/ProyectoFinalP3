package com.edu.uniquindio.co.marketplace.clases.interfaces;

import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;

public interface AdministrarVendedores {
    void agregarVendedor(String nombre, String apellido, String cedula, String direccion, String correo, String contrasenia, Image imagen) throws IOException;
    void eliminarVendedor(Vendedor vendedor) throws IOException;
    void modificarVendedor(Vendedor vendedor, Vendedor vendedorModificado) throws IOException;
}
