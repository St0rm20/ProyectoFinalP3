package com.edu.uniquindio.co.marketplace.clases;

import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.personas.Administrador;
import com.edu.uniquindio.co.marketplace.clases.util.UsuarioActual;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App6 extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App6.class.getResource("/com/edu/uniquindio/co/marketplace/Principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("MarketPlace");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        MarketPlace marketPlace = MarketPlace.getInstance();
        marketPlace.setGuardarPersistencia(false);
        launch();
    }
}
