package com.edu.uniquindio.co.marketplace.clases;

import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import com.edu.uniquindio.co.marketplace.clases.util.Utilities;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.edu.uniquindio.co.marketplace.clases.enums.EstadoProducto;


/**
 * Clase principal de la aplicacion
 * Pruebas con el main del funcionamiento de la aplicación sin GUI
 * @version 1.0
 *  @author Helen Xiomara Giraldo Libreros y Miguel Angel Vargas Pelaez
 */
public class App2Prueba extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Properties propiedades = Utilities.cargarPropiedades("src/main/resources/rutas.properties");
        String rutaArchivoPublicados = propiedades.getProperty("RUTA_ARCHIVO_PUBLICADOS");
        String rutaArchivoVendidos = propiedades.getProperty("RUTA_ARCHIVO_VENDIDOS");
        String rutaArchivoCancelados = propiedades.getProperty("RUTA_ARCHIVO_CANCELADOS");
        String carpetaRespaldo = propiedades.getProperty("RUTA_ARCHIVO_RESPALDO");




        MarketPlace marketPlace = MarketPlace.getInstance();
        marketPlace.agregarVendedor("Juan", "Perez", "123",
                "Calle 1", "juanperez@gmail.com", "123",null);
        marketPlace.agregarVendedor("Maria", "Lopez", "456",
                "Calle 2", "marialopez@gmail.com", "456",null);
        marketPlace.agregarVendedor("Pedro", "Gomez", "789",
                "Calle 3", "pedrogomez@gmail.com", "789",null);
        marketPlace.agregarVendedor("Luis", "Garcia", "101",
                "Calle 4", "luisgarcia@gmail.com", "101",null);

        //ceración vendedor uno con sus productos
        Vendedor vendedor = marketPlace.getListaVendedores().get(0);
        vendedor.agregarProducto("Camiseta", "Camiseta deportiva comoda", null, "Ropa",1000, EstadoProducto.PUBLICADO);
        vendedor.agregarProducto("Zapatos", "Zapatos deportivos", null, "Calzado",2000, EstadoProducto.PUBLICADO);
        vendedor.agregarProducto("Pantalon", "Pantalon deportivo", null, "Ropa",1500, EstadoProducto.PUBLICADO);

        //creación vendedor dos con sus productos
        Vendedor vendedor2 = marketPlace.getListaVendedores().get(1);
        vendedor2.agregarProducto("Balon", "Balon de futbol", null, "Deportes",500, EstadoProducto.PUBLICADO);
        vendedor2.agregarProducto("Raqueta", "Raqueta de tenis", null, "Deportes",1000, EstadoProducto.PUBLICADO);
        vendedor2.agregarProducto("Mancuernas", "Mancuernas de 5kg", null, "Deportes",1500, EstadoProducto.PUBLICADO);

        //creación vendedor tres con sus productos
        Vendedor vendedor3 = marketPlace.getListaVendedores().get(2);
        vendedor3.agregarProducto("Celular", "Celular gama media", null, "Tecnologia",1000, EstadoProducto.PUBLICADO);
        vendedor3.agregarProducto("Tablet", "Tablet gama media", null, "Tecnologia",2000, EstadoProducto.PUBLICADO);
        vendedor3.agregarProducto("Portatil", "Portatil gama media", null, "Tecnologia",1500, EstadoProducto.PUBLICADO);

        //creación vendedor cuatro con sus productos
        Vendedor vendedor4 = marketPlace.getListaVendedores().get(3);
        vendedor4.agregarProducto("Mesa", "Mesa de madera", null, "Muebles",1000, EstadoProducto.PUBLICADO);
        vendedor4.agregarProducto("Silla", "Silla de madera", null, "Muebles",2000, EstadoProducto.PUBLICADO);
        vendedor4.agregarProducto("Sofa", "Sofa de madera", null, "Muebles",1500, EstadoProducto.PUBLICADO);

        //creación de un administrador
        marketPlace.agregarAdministrador("Admin", "Admin", "12345",
                "admin@gmail.com", "admin","Admin123",null);


        vendedor.enviarSolicitud(vendedor2);
        vendedor2.getSolicitudes().get(0).aceptarSolicitud();


        vendedor2.agregarProductoAlCarrito(vendedor.getProductos().get(0));
        vendedor2.cancelarProducto(vendedor2.getProductos().get(0));
        System.out.println(vendedor.getProductos().get(0));
        vendedor2.comprarProducto(vendedor.getProductos().get(0));
        System.out.println(vendedor.getProductos().get(0));

        vendedor2.comprarProducto(vendedor.getProductos().get(0));
        vendedor3.comprarProducto(vendedor.getProductos().get(1));

        Utilities.crearCopiaRespaldoXML(marketPlace.getListaVendedores() );
    }

}
