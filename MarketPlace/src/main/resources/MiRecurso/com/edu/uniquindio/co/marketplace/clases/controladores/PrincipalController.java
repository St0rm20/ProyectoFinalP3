package com.edu.uniquindio.co.marketplace.clases.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.edu.uniquindio.co.marketplace.clases.ClienteMarketPlace;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PrincipalController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonEnter;

    @FXML
    private ImageView logopng;

    @FXML
    private Label textQuickTrove;

    @FXML
    private Label textTrove;

    @FXML
    void onButtonEnter(ActionEvent event) throws IOException {
        ClienteMarketPlace.setRoot("/com/edu/uniquindio/co/marketplace/LoginAndSignUp.fxml",480,540);
    }

    @FXML
    void initialize() {}

}
