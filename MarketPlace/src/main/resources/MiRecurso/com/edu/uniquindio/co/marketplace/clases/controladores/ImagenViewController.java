package com.edu.uniquindio.co.marketplace.clases.controladores;

import com.edu.uniquindio.co.marketplace.clases.util.ImagenCargando;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ImagenViewController {
    private File imagenSeleccionada;
    private ImagenCargando imagenCargando = ImagenCargando.getInstance();

    @FXML
    private ImageView imagenCargada;

    @FXML
    private Button onAgregarImagen;

    @FXML
    private Button onSeleccionarImagen;


    @FXML
    private Label arrastrarImagenText;


    @FXML
    void onAgregarImagen(ActionEvent event) {
        if (imagenSeleccionada == null) {
            return;
        }
        imagenCargando.cargarImagen(imagenSeleccionada);
        ((Stage) onAgregarImagen.getScene().getWindow()).close();
    }


    @FXML
    void onSeleccionarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        File archivoSeleccionado = fileChooser.showOpenDialog(null);
        if (archivoSeleccionado != null) {
            imagenSeleccionada = archivoSeleccionado;
            Image imagen = new Image(archivoSeleccionado.toURI().toString());
            imagenCargada.setImage(imagen);
            arrastrarImagenText.setText("");

        }
    }

    @FXML
    public void initialize() {

        arrastrarImagenText.setOnDragOver(event -> {
            System.out.println("Evento onDragOver detectado");
            if (event.getGestureSource() != arrastrarImagenText && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        arrastrarImagenText.setOnDragDropped(event -> {
            System.out.println("Evento onDragDropped detectado");
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                File archivoArrastrado = db.getFiles().get(0);
                if (archivoArrastrado.getName().matches(".*\\.(png|jpg|jpeg)")) {
                    imagenSeleccionada = archivoArrastrado;
                    Image imagen = new Image(archivoArrastrado.toURI().toString());
                    imagenCargada.setImage(imagen);
                    arrastrarImagenText.setText("");
                    success = true;
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }


}

