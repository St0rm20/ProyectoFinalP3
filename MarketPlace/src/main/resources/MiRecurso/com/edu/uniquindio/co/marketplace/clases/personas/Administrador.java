package com.edu.uniquindio.co.marketplace.clases.personas;

import com.edu.uniquindio.co.marketplace.clases.interfaces.AdministrarVendedores;
import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Administrador extends Persona implements AdministrarVendedores, Serializable {

    MarketPlace marketPlace = MarketPlace.getInstance();
    private static final long serialVersionUID = 1L;

    public Administrador(String nombre, String apellido, String cedula, String correo, String contrasenia, Image imagen) {
        super(nombre, apellido, cedula, correo, contrasenia,imagen);
    }

    public Administrador(){
        super();
    }

    @Override
    public void agregarVendedor(String nombre, String apellido, String cedula, String direccion, String correo, String contrasenia, Image imagen) throws IOException {
        marketPlace.agregarVendedor(nombre, apellido, cedula, direccion, correo, contrasenia,imagen);
    }

    @Override
    public void eliminarVendedor(Vendedor vendedor) {

    }

    @Override
    public void modificarVendedor(Vendedor vendedor, Vendedor vendedorModificado) {

    }

    public MarketPlace getMarketPlace() {
        return marketPlace;
    }

    public void setMarketPlace(MarketPlace marketPlace) {
        this.marketPlace = marketPlace;
    }
}
