package com.edu.uniquindio.co.marketplace.clases;

import com.edu.uniquindio.co.marketplace.clases.enums.EstadoProducto;
import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import com.edu.uniquindio.co.marketplace.clases.util.UsuarioActual;
import com.edu.uniquindio.co.marketplace.clases.util.Utilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;


/**
 * Clase principal de la aplicacion
 * Pruebas con el main del funcionamiento de la aplicación sin GUI
 * @version 1.0
 *  @author Helen Xiomara Giraldo Libreros y Miguel Angel Vargas Pelaez
 */
public class App7Prueba extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App4.class.getResource("/com/edu/uniquindio/co/marketplace/Productos1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 920, 550);
        stage.setTitle("MarketPlace");
        stage.setScene(scene);
        stage.show();

    }



}
