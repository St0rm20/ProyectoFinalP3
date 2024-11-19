package com.edu.uniquindio.co.marketplace.clases.sockets;

import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ContactosApp extends Application {
    private TableView<Vendedor> table = new TableView<>();

    @Override
    public void start(Stage primaryStage) {
        TableColumn<Vendedor, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Vendedor, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getColumns().addAll(nombreCol, emailCol);

        VBox vbox = new VBox(table);
        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void mostrarContactos(List<Vendedor> contactos) {
        table.getItems().setAll(contactos);
    }

    public static void main(String[] args) {
        launch(args);
    }
}