package com.edu.uniquindio.co.marketplace.clases.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.edu.uniquindio.co.marketplace.clases.ClienteMarketPlace;
import com.edu.uniquindio.co.marketplace.clases.interfaces.ObserverInicioSesion;
import com.edu.uniquindio.co.marketplace.clases.interfaces.ObserverRegistro;
import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.util.Alerta;
import com.edu.uniquindio.co.marketplace.clases.util.UsuarioActual;
import com.edu.uniquindio.co.marketplace.clases.util.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javax.swing.*;

public class LoginController implements ObserverInicioSesion, ObserverRegistro {

    MarketPlace marketPlace = MarketPlace.getInstance();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab TexrSignUp;

    @FXML
    private Button buttonSingIN;

    @FXML
    private Button buttonSingUp;

    @FXML
    private Hyperlink link;

    @FXML
    private ImageView logopng;

    @FXML
    private Button onAdminButton;

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldEmailAddress;

    @FXML
    private TextField textFieldID;

    @FXML
    private TextField textFieldLastName;

    @FXML
    private TextField textFieldName;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private PasswordField textFieldPassword2;

    @FXML
    private Tab textLoginIn;

    @FXML
    void onAdminButton(ActionEvent event) throws IOException {
        if (validarCamposSignUp()) {
            Alerta.mostrarAdvertencia(Utilities.getIdioma().getString("alertaRelleneTodosLosCampos"));
            return;
        }
        String name = textFieldName.getText();
        String lastName = textFieldLastName.getText();
        String id = textFieldID.getText();
        String email = textFieldEmail.getText();
        String password = textFieldPassword2.getText();
        //JOPtion que recoja la direccion
        String passwordAd = JOptionPane.showInputDialog(null, "Enter your address:", "Address Input", JOptionPane.QUESTION_MESSAGE);

        if (passwordAd == null || passwordAd.trim().isEmpty()) {
            Alerta.mostrarAdvertencia(Utilities.getIdioma().getString("alertaDireccionRequerida"));
            return;
        }
        if(!passwordAd.equals(marketPlace.getClaveSeguridad())){
            Alerta.mostrarAdvertencia(Utilities.getIdioma().getString("alertaClaveAdminIncorrecta"));
            return;
        }
        ClienteMarketPlace.enviarRegistroAdmin(name, lastName, id, email, password);
    }

    @FXML
    void onLink(ActionEvent event) {
        tabPane.getSelectionModel().select(textLoginIn);
    }

    @FXML
    void onSignIn(ActionEvent event) throws IOException {
        if (validarCamposLoginIn()) {
            Alerta.mostrarAdvertencia(Utilities.getIdioma().getString("alertaRelleneTodosLosCampos"));
            return;
        }
        String email = textFieldEmailAddress.getText();
        String password = textFieldPassword.getText();
        ClienteMarketPlace.enviarInicioSesion(email, password);
    }

    @FXML
    void onSignUp(ActionEvent event) throws IOException {
        if (validarCamposSignUp()) {
            Alerta.mostrarAdvertencia(Utilities.getIdioma().getString("alertaRelleneTodosLosCampos"));
            return;
        }
        String name = textFieldName.getText();
        String lastName = textFieldLastName.getText();
        String id = textFieldID.getText();
        String email = textFieldEmail.getText();
        String password = textFieldPassword2.getText();
        //JOPtion que recoja la direccion
        String address = JOptionPane.showInputDialog(null, "Enter your address:", "Address Input", JOptionPane.QUESTION_MESSAGE);

        if (address == null || address.trim().isEmpty()) {
            Alerta.mostrarAdvertencia(Utilities.getIdioma().getString("alertaDireccionRequerida"));
            return;
        }
        ClienteMarketPlace.enviarRegistro(name, lastName, id, email, password, address);
    }

    @FXML
    void initialize() {
        ClienteMarketPlace.setObserverInicioSesion(this);
        ClienteMarketPlace.setObserverRegistro(this);
    }

    private boolean validarCamposLoginIn() {
       return textFieldEmailAddress.getText().isEmpty() || textFieldPassword.getText().isEmpty();
    }

    private boolean validarCamposSignUp() {
        return textFieldName.getText().isEmpty() || textFieldLastName.getText().isEmpty() || textFieldID.getText().isEmpty() || textFieldEmail.getText().isEmpty() || textFieldPassword2.getText().isEmpty();
    }

    @Override
    public void update(UsuarioActual persona) throws IOException {
        //runlater

        if (persona.getPersona() != null) {
            if (UsuarioActual.getTipo().equals("admin")) {
                ClienteMarketPlace.setRoot("/com/edu/uniquindio/co/marketplace/AdminiView.fxml", 800, 600);
            } else {
                ClienteMarketPlace.setRoot("/com/edu/uniquindio/co/marketplace/Productos1.fxml", 800, 600);
            }
        }
    }

    @Override
    public void update() throws IOException {

        tabPane.getSelectionModel().select(textLoginIn);
    }
}

