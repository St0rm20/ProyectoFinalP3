package com.edu.uniquindio.co.marketplace.clases;

import com.edu.uniquindio.co.marketplace.clases.enums.EstadoProducto;
import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.market.Producto;
import com.edu.uniquindio.co.marketplace.clases.personas.Administrador;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.ArrayList;

public class MarketPlaceTest {
    public static void main(String[] args) throws IOException {
        MarketPlace marketPlace = MarketPlace.getInstance();
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Administrador> administradores = new ArrayList<>();

        //crear admin
        Administrador admin1 = new Administrador("Carlos", "Gomez", "123456789", "Bogota", "carlos@gmail.com", null);
        Administrador admin2 = new Administrador("Ana", "Martinez", "987654321", "Medellin", "ana@gmail.com", null);

        administradores.add(admin1);
        administradores.add(admin2);

        Vendedor vendedor1 = new Vendedor("Juan", "Lopez", "1094896557", "Armenia", "Juan@gmail.com", "1234", (Image) null);
        vendedor1.agregarProducto("Camiseta", "Camiseta deportiva comoda", null, "Ropa", 1000, EstadoProducto.PUBLICADO);
        vendedor1.agregarProducto("Zapatos", "Zapatos deportivos", null, "Calzado", 2000, EstadoProducto.PUBLICADO);

        Vendedor vendedor2 = new Vendedor("Maria", "Perez", "1094896554", "Filandia", "maria@gmail.com", "1235", (Image) null);
        vendedor2.agregarProducto("Balon", "Balon de futbol", null, "Deportes", 500, EstadoProducto.PUBLICADO);
        vendedor2.agregarProducto("Raqueta", "Raqueta de tenis", null, "Deportes", 1000, EstadoProducto.PUBLICADO);

        productos.addAll(vendedor1.getProductos());
        productos.addAll(vendedor2.getProductos());


        try {
            marketPlace.serializarAdministradores("administradores.dat", administradores);
            System.out.println("Administradores serializados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            marketPlace.serializarProductos("productos.dat", productos);
            System.out.println("Productos serializados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            ArrayList<Producto> productosDeserializados = marketPlace.deserializarProductos("productos.dat");
            System.out.println("Productos deserializados correctamente.");
            for (Producto producto : productosDeserializados) {
                System.out.println("Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio() + ", Descripción: " + producto.getDescripcion());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            MarketPlace.serializarObjetoXML("productos.xml", productos);
            System.out.println("Productos serializados correctamente en XML.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ArrayList<Producto> productosDeserializadosXML = (ArrayList<Producto>) MarketPlace.deserializarObjetoXML("productos.xml");
            System.out.println("Productos deserializados correctamente desde XML.");
            for (Producto producto : productosDeserializadosXML) {
                System.out.println("Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio() + ", Descripción: " + producto.getDescripcion());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            MarketPlace.serializarObjetoXML("administradores.xml", administradores);
            System.out.println("Administradores serializados correctamente en XML.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ArrayList<Administrador> administradoresDeserializados = (ArrayList<Administrador>) MarketPlace.deserializarObjetoXML("administradores.xml");
            System.out.println("Administradores deserializados correctamente desde XML.");
            for (Administrador admin : administradoresDeserializados) {
                System.out.println("Nombre: " + admin.getNombre() + ", Email: " + admin.getCorreo() +
                        ", Apellido: " + admin.getApellido() + ", Contraseña: " + admin.getContrasenia() + ", Cedula: " + admin.getCedula());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}