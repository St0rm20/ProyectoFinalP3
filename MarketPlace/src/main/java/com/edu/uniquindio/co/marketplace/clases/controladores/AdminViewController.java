package com.edu.uniquindio.co.marketplace.clases.controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.edu.uniquindio.co.marketplace.clases.interfaces.ObserverImage;
import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.personas.Administrador;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import com.edu.uniquindio.co.marketplace.clases.util.Alerta;
import com.edu.uniquindio.co.marketplace.clases.util.ImagenCargando;
import com.edu.uniquindio.co.marketplace.clases.util.UsuarioActual;
import com.edu.uniquindio.co.marketplace.clases.util.Utilities;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AdminViewController implements ObserverImage {

    MarketPlace marketPlace = MarketPlace.getInstance();
    Administrador usuarioActual = (Administrador) UsuarioActual.getInstance().getPersona();
    ImagenCargando imagenCargando = ImagenCargando.getInstance();
    private ObservableList<Vendedor> listaVendedores;
    private Vendedor vendedorSeleccionado;




    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label AdminLabel;

    @FXML
    private Pane BackgroundAdmin;

    @FXML
    private TableColumn<Vendedor, String> ColumnAddressEditSalesPerson;

    @FXML
    private TableColumn<Vendedor, String> ColumnCodeEditSalesPerson;

    @FXML
    private TableColumn<Vendedor, String> ColumnEmailEditSalesPerson;

    @FXML
    private TableColumn<Vendedor, String> ColumnIDEditSalesPerson;

    @FXML
    private TableColumn<Vendedor, String> ColumnLastNameEditSalesPerson;

    @FXML
    private TableColumn<Vendedor, String> ColumnNameEditSalesPerson;

    @FXML
    private Label EmailAdminLabel;

    @FXML
    private Label IDadminLabel;

    @FXML
    private Label apellidoAdminLabel;

    @FXML
    private AnchorPane background;

    @FXML
    private Label nombreadminLabel;

    @FXML
    private Button onButtonAddEditSalesperson;

    @FXML
    private Button onButtonBack;

    @FXML
    private Button onButtonDeleteEditSalesperson;

    @FXML
    private Button onButtonUpdatePageAdmin;

    @FXML
    private Button onButtonUptade;

    @FXML
    private Button onButtonUptadeEditSalesperson;

    @FXML
    private Tab onEditProfileInformationAdmin;

    @FXML
    private Tab onEditSalesPersonInformationAdmin;

    @FXML
    private ImageView pngcomment12;

    @FXML
    private TextField textFieldAddresEditSalesperson;

    @FXML
    private TextField textFieldEmailAdmin;

    @FXML
    private TextField textFieldEmailEditSalesperson;

    @FXML
    private TextField textFieldIDAdmin;

    @FXML
    private TextField textFieldIDEditSalesperson;

    @FXML
    private TextField textFieldLastNameAdmin;

    @FXML
    private TextField textFieldLastNameAdminEditSalesperson;

    @FXML
    private TextField textFieldNameAdmin;

    @FXML
    private TextField textFieldNameAdminEditSalesperson;

    @FXML
    private PasswordField textFieldPasswordAdmin;

    @FXML
    private PasswordField textFieldPasswordEditSalesperson;

    @FXML
    private TableView<Vendedor> vendedoresTabla;

    @FXML
    private ImageView changeImageAdmin;

    @FXML
    private ImageView salesPersonImage;

    @FXML
    void OnButtonBack(ActionEvent event) {
        System.out.println("OnButtonBack");
    }

    @FXML
    void OnButtonUptadeEditSalesperson(ActionEvent event) throws IOException {
        actualizarVendor();
    }

    @FXML
    void onButtonAddEditSalesperson(ActionEvent event) throws IOException {
        agregarVendedor();
    }

    @FXML
    void onButtonDeleteEditSalesperson(ActionEvent event) throws IOException {
        eliminarVendedor();
    }

    @FXML
    void onButtonUpdatePageAdmin(ActionEvent event) {
        System.out.println("onButtonUpdatePageAdmin");
    }

    @FXML
    void onButtonUptade(ActionEvent event) {

        String nombre = textFieldNameAdmin.getText();
        String apellido = textFieldLastNameAdmin.getText();
        String contrasenia = textFieldPasswordAdmin.getText();
        String email = textFieldEmailAdmin.getText();
        String id = textFieldIDAdmin.getText();

        usuarioActual.setNombre(nombre);
        usuarioActual.setApellido(apellido);
        usuarioActual.setContrasenia(contrasenia);
        usuarioActual.setCorreo(email);
        usuarioActual.setCedula(id);
        cambiarAtributos();

    }

    @FXML
    void initialize() {

        cambiarAtributos();
        listaVendedores = FXCollections.observableArrayList(marketPlace.getListaVendedores());
        inicializarTabla();
        inicializarValores();
        verSeleccion();
    }

    private void cambiarAtributos() {

        nombreadminLabel.setText(usuarioActual.getNombre());
        apellidoAdminLabel.setText(usuarioActual.getApellido());
        EmailAdminLabel.setText(usuarioActual.getCorreo());
        IDadminLabel.setText(usuarioActual.getCedula());
        pngcomment12.setImage(changeImageAdmin.getImage());
    }


    private void eliminarVendedor() throws IOException {
        boolean confirmacion = Alerta.mostrarMensajeConfirmacion(Utilities.getIdioma().getString("alertaEliminarVendedor"));
        if (!confirmacion) {
            return;
        }
        Vendedor vendedor = vendedoresTabla.getSelectionModel().getSelectedItem();
        if (vendedor != null) {
            listaVendedores.remove(vendedor);
            marketPlace.eliminarVendedor(vendedor);
        }
        limpiarDatos();
    }

    private void inicializarTabla() {
        ColumnAddressEditSalesPerson.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
        ColumnCodeEditSalesPerson.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        ColumnEmailEditSalesPerson.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        ColumnIDEditSalesPerson.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
        ColumnLastNameEditSalesPerson.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        ColumnNameEditSalesPerson.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        vendedoresTabla.setItems(listaVendedores);
    }

    private void inicializarValores() {
        textFieldNameAdmin.setText(usuarioActual.getNombre());
        textFieldLastNameAdmin.setText(usuarioActual.getApellido());
        textFieldEmailAdmin.setText(usuarioActual.getCorreo());
        textFieldIDAdmin.setText(usuarioActual.getCedula());
        textFieldPasswordAdmin.setText(usuarioActual.getContrasenia());

    }

    @FXML
    public void onChangeImageAdmin(MouseEvent mouseEvent) throws IOException {
        imagenCargando.cargarEscena(this);
    }

    @FXML
    public void onSalesPersonImage(MouseEvent mouseEvent) throws IOException {
        imagenCargando.cargarEscena(this);
    }

    @Override
    public void update() {
        File imageFile = imagenCargando.getImagenSeleccionada();
        if (imageFile != null) {
            changeImageAdmin.setImage(new Image(imageFile.toURI().toString()));
        }
    }

    private void actualizarVendor() throws IOException {
        verSeleccion();
        if (vendedorSeleccionado != null) {
            Vendedor vendedor = crearVendedor();
            if (vendedor != null) {
                actualizarLista(vendedorSeleccionado, vendedor);
            }
        }
    }

    private void actualizarLista(Vendedor vendedorActual, Vendedor vendedorActualizado) {
        int i = listaVendedores.indexOf(vendedorActual);
        listaVendedores.set(i, vendedorActualizado);
    }

    private void verSeleccion() {
        vendedoresTabla.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            vendedorSeleccionado = newSelection;
            mostrarInformacionSesion(vendedorSeleccionado);
        });
    }

    private void mostrarInformacionSesion(Vendedor vendedor) {
        if (vendedor != null) {
            textFieldNameAdminEditSalesperson.setText(vendedor.getNombre());
            textFieldLastNameAdminEditSalesperson.setText(vendedor.getApellido());
            textFieldIDEditSalesperson.setText(vendedor.getCedula());
            textFieldAddresEditSalesperson.setText(vendedor.getDireccion());
            textFieldEmailAdmin.setText(vendedor.getCorreo());
            textFieldPasswordAdmin.setText(vendedor.getContrasenia());

        }
    }

    private Vendedor crearVendedor() throws IOException {
        return new Vendedor(
                textFieldNameAdminEditSalesperson.getText(),
                textFieldLastNameAdminEditSalesperson.getText(), textFieldIDEditSalesperson.getText(),
                textFieldAddresEditSalesperson.getText(), textFieldEmailEditSalesperson.getText(),
                textFieldPasswordEditSalesperson.getText(), salesPersonImage.getImage());

    }

    private void agregarVendedor() throws IOException {
        if (validarFormato()) {
            Vendedor vendedor = crearVendedor();
            if (vendedor != null) {
                listaVendedores.add(vendedor);
                marketPlace.agregarVendedor(vendedor);
            }
        } else {
            Alerta.mostrarMensajeConfirmacion(Utilities.getIdioma().getString("alertaRelleneTodosLosCampos"));
        }
    }

    private boolean validarFormato() {
        return !textFieldNameAdminEditSalesperson.getText().isEmpty()
                && !textFieldLastNameAdminEditSalesperson.getText().isEmpty()
                && !textFieldIDEditSalesperson.getText().isEmpty()
                && !textFieldAddresEditSalesperson.getText().isEmpty()
                && !textFieldEmailEditSalesperson.getText().isEmpty()
                && !textFieldPasswordEditSalesperson.getText().isEmpty()
                && salesPersonImage.getImage() != null;

    }

    private void limpiarDatos() {
        textFieldNameAdminEditSalesperson.clear();
        textFieldLastNameAdminEditSalesperson.clear();
        textFieldIDEditSalesperson.clear();
        textFieldAddresEditSalesperson.clear();
        textFieldEmailEditSalesperson.clear();
        textFieldPasswordEditSalesperson.clear();
        salesPersonImage.setImage(null);
    }

}
