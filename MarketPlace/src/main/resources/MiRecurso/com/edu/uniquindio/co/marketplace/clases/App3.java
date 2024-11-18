package com.edu.uniquindio.co.marketplace.clases;

import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.personas.Administrador;
import com.edu.uniquindio.co.marketplace.clases.util.UsuarioActual;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App3 extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App3.class.getResource("/com/edu/uniquindio/co/marketplace/AdminView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 820, 600);
        stage.setTitle("MarketPlace");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        MarketPlace marketPlace = MarketPlace.getInstance();
        marketPlace.setGuardarPersistencia(false);
        Administrador administrador = new Administrador("Juan", "Perez", "123",
                "Correo@correo.com", "123", null);
        UsuarioActual usuarioActual = UsuarioActual.getInstance(administrador);
        launch();
    }
}
