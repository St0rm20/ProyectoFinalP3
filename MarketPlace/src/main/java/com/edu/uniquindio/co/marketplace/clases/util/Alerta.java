package com.edu.uniquindio.co.marketplace.clases.util;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;


public class Alerta {


    public static boolean mostrarMensajeConfirmacion(String message) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(Utilities.getIdioma().getString("mensajeConfirmacionAccion"));
        alert.setContentText(message);
        ButtonType yesButton = new ButtonType(Utilities.getIdioma().getString("botonAceptar"), ButtonBar.ButtonData.OK_DONE);
        ButtonType noButton = new ButtonType(Utilities.getIdioma().getString("botonCancelar"), ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> action = alert.showAndWait();
        return action.isPresent() && action.get() == yesButton;
    }

    public static void mostrarAdvertencia(String contenido) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(Utilities.getIdioma().getString("mensajeAdvertencia"));
        alert.setContentText(contenido);
        ButtonType okButton = new ButtonType(Utilities.getIdioma().getString("botonAceptar"), ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }

    public static void mostrarInformacion(String contenido)throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(Utilities.getIdioma().getString("mensajeInformacion"));
        alert.setContentText(contenido);
        ButtonType okButton = new ButtonType(Utilities.getIdioma().getString("botonAceptar"), ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }

    public static void mostrarError(String contenido) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(Utilities.getIdioma().getString("mensajeError"));
        alert.setContentText(contenido);
        ButtonType okButton = new ButtonType(Utilities.getIdioma().getString("botonAceptar"), ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }
    // Muestra una alerta con el título, encabezado, contenido y tipo de alerta especificados
    public static void mostrarAlerta(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        ButtonType okButton = new ButtonType(Utilities.getIdioma().getString("botonAceptar"), ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }
}
