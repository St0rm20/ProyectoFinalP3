package com.edu.uniquindio.co.marketplace.clases.util;

import com.edu.uniquindio.co.marketplace.clases.interfaces.ObserverImage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class ImagenCargando extends Application {
    public static ImagenCargando instance;
    static ResourceBundle rutas = ResourceBundle.getBundle("rutas");
    private static final String IMAGE_SAVE_DIRECTORY = rutas.getString("RUTA_IMAGENES");
    private File imagenSeleccionada;
    private ObserverImage observerImage;

    public static ImagenCargando getInstance() {
        if (instance == null) {
            instance = new ImagenCargando();
        }
        return instance;
    }

    public void cargarImagen(File imagenSeleccionada) {
        this.imagenSeleccionada = imagenSeleccionada;
        observerImage.update();

    }

    public File getImagenSeleccionada() {
        return imagenSeleccionada;
    }

    public void guardarImagen(File imagenSeleccionada, String nombreImagen) throws Exception {
        // Crear carpeta si no existe
        File directory = new File(IMAGE_SAVE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }


        File destino = new File(IMAGE_SAVE_DIRECTORY + imagenSeleccionada.getName());
        Files.copy(imagenSeleccionada.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

    }

    public void cargarEscena(ObserverImage observerImage) throws IOException {
        this.observerImage = observerImage;
        start(new Stage());

    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ImagenCargando.class.getResource("/com/edu/uniquindio/co/marketplace/ImagenView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("MarketPlace");
        stage.setScene(scene);
        stage.show();
    }

}
