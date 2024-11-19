package com.edu.uniquindio.co.marketplace.clases;

import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.personas.Administrador;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import com.edu.uniquindio.co.marketplace.clases.util.UsuarioActual;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App5 extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App5.class.getResource("/com/edu/uniquindio/co/marketplace/VendedorView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 820, 600);
        stage.setTitle("MarketPlace");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        MarketPlace marketPlace = MarketPlace.getInstance();
        marketPlace.setGuardarPersistencia(false);
        Vendedor vendedor = new Vendedor("Juan", "Perez", "123",
                "Calle 1", "juanperez@gmail.com", "123", (Image) null);

        Vendedor vendedor2 = new Vendedor("Juan", "Perez", "123",
                "Calle 1", "juanperez@gmail.com", "123", (Image) null);

        Vendedor vendedor3 = new Vendedor("Pene Chueco", "Perez", "123",
                "Calle 1", "juanperez@gmail.com", "123", (Image) null);

        Vendedor vendedor4 = new Vendedor("Pacho", "Perez", "123",
                "Calle 1", "juanperez@gmail.com", "123", (Image) null);
        Vendedor vendedor5 = new Vendedor("Gerundio", "Perez", "123",
                "Calle 1", "juanperez@gmail.com", "123", (Image) null);
        Vendedor vendedor6 = new Vendedor("Jerardo", "Perez", "123",
                "Calle 1", "juanperez@gmail.com", "123", (Image) null);
        Vendedor vendedor7 = new Vendedor("Willinton", "Perez", "123",
                "Calle 1", "juanperez@gmail.com", "123", (Image) null);
        Vendedor vendedor8 = new Vendedor("Laura", "Perez", "123",
                "Calle 1", "juanperez@gmail.com", "123", (Image) null);
        Vendedor vendedor9 = new Vendedor("Valeria", "Perez", "123",
                "Calle 1", "juanperez@gmail.com", "123", (Image) null);
        Vendedor vendedor10 = new Vendedor("Quiso", "Perez", "123",
                "Calle 1", "juanperez@gmail.com", "123", (Image) null);
        Vendedor vendedor11 = new Vendedor("Quiso2", "Perez", "123",
                "Calle 1", "juanperez@gmail.com", "123", (Image) null);

        vendedor2.agregarComentario("Muy buen vendedor", vendedor);

        vendedor.enviarSolicitud(vendedor2);
        vendedor2.getSolicitudes().get(0).aceptarSolicitud();
        vendedor.enviarSolicitud(vendedor3);
        vendedor3.getSolicitudes().get(0).aceptarSolicitud();
        vendedor.enviarSolicitud(vendedor4);
        vendedor4.getSolicitudes().get(0).aceptarSolicitud();
        vendedor.enviarSolicitud(vendedor5);
        vendedor5.getSolicitudes().get(0).aceptarSolicitud();
        vendedor.enviarSolicitud(vendedor6);
        vendedor6.getSolicitudes().get(0).aceptarSolicitud();
        vendedor.enviarSolicitud(vendedor7);
        vendedor7.getSolicitudes().get(0).aceptarSolicitud();
        vendedor.enviarSolicitud(vendedor8);
        vendedor8.getSolicitudes().get(0).aceptarSolicitud();
        vendedor.enviarSolicitud(vendedor9);
        vendedor9.getSolicitudes().get(0).aceptarSolicitud();
        vendedor.enviarSolicitud(vendedor10);
        vendedor10.getSolicitudes().get(0).aceptarSolicitud();
        vendedor.enviarSolicitud(vendedor11);
        vendedor11.getSolicitudes().get(0).aceptarSolicitud();
        UsuarioActual usuarioActual = UsuarioActual.getInstance(vendedor);
        launch();
    }
}
