package com.edu.uniquindio.co.marketplace.clases.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.edu.uniquindio.co.marketplace.clases.ClienteMarketPlace;
import com.edu.uniquindio.co.marketplace.clases.enums.EstadoProducto;
import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.market.Producto;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import com.edu.uniquindio.co.marketplace.clases.util.UsuarioActual;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class AddProductController {
    UsuarioActual usuarioActual = UsuarioActual.getInstance();
    private ObservableList<Producto> productos;
    private Producto productoSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> ChoiceBoxEstado;

    @FXML
    private AnchorPane background;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonAddImage;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonUpdate;

    @FXML
    private TableColumn <Producto, String> columCode;

    @FXML
    private TableColumn <Producto, String> columPrecio;

    @FXML
    private TableColumn <Producto, String>  columnComments;

    @FXML
    private TableColumn <Producto, String> columnFecha;

    @FXML
    private TableColumn <Producto, String>  columnName;

    @FXML
    private TableColumn <Producto, String>  columStatus;

    @FXML
    private TableColumn <Producto, String>  columCategory;

    @FXML
    private ImageView pngcomment12;

    @FXML
    private TableView<Producto> tablaProducts;

    @FXML
    private TextField textFieldCODE1;

    @FXML
    private TextField textFieldCategory;

    @FXML
    private TextField textFieldLastName1;

    @FXML
    private TextField textFieldName1;

    @FXML
    private TextField textFieldDescription;

    @FXML
    void onButtonBack(ActionEvent event) throws IOException {
        ClienteMarketPlace.setRoot("/com/edu/uniquindio/co/marketplace/VendedorView.fxml");
    }

    @FXML
    void onbuttonAdd(ActionEvent event) throws IOException {
        agregarProducto();
    }

    @FXML
    void onbuttonAddImage(ActionEvent event) {

    }

    @FXML
    void onbuttonUpdate(ActionEvent event) throws IOException {
        actualizarProducto();
    }

    @FXML
    void initialize() {
        MarketPlace marketPlace = MarketPlace.getInstance();
        Vendedor vendedor = ((Vendedor) usuarioActual.getPersona());

        productos = FXCollections.observableArrayList(vendedor.getProductos());
        actualizarOpcionesChoiceBox();
        inicializarTabla();
    }

    public void actualizarOpcionesChoiceBox() {
        ChoiceBoxEstado.getItems().clear();

        ChoiceBoxEstado.getItems().addAll("Publicado", "Vendido", "Cancelado");
    }

    private void inicializarTabla() {
        columnName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columCode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        columPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
        columnFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaPublicacion().toString()));
        columnComments.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getComentarios().size())));
        columStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado().toString()));
        columCategory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria()));
        tablaProducts.setItems(productos);
    }

    private void verSeleccion() {
        tablaProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
            mostrarInformacion(productoSeleccionado);
        });
    }

    private Producto agregarProducto() throws IOException{
        if (validarFormato()) {
            //String nombre, String descripcion, Image imagen,
            // String categoria, double precio, EstadoProducto estado
            EstadoProducto estado;
            if(ChoiceBoxEstado.getValue().equals("Publicado")) {
                estado = EstadoProducto.PUBLICADO;
            }else if(ChoiceBoxEstado.getValue().equals("Vendido")) {
                estado = EstadoProducto.VENDIDO;
            }else{
                estado = EstadoProducto.CANCELADO;
            }

            Producto producto = new Producto (textFieldName1.getText(),textFieldDescription.getText(),null,textFieldCategory.getText(),
                    Double.parseDouble(textFieldLastName1.getText()),estado,((Vendedor)usuarioActual.getPersona()));

            ((Vendedor)usuarioActual.getPersona()).agregarProducto(
                    textFieldName1.getText(),textFieldDescription.getText(),
                    pngcomment12.getImage(),textFieldCategory.getText(),
                    Double.parseDouble(textFieldLastName1.getText()),estado
            );
            ClienteMarketPlace.anadirProducto(textFieldName1.getText(),textFieldDescription.getText()
                   ,textFieldCategory.getText(),
                    Double.parseDouble(textFieldLastName1.getText()),ChoiceBoxEstado.getValue(), (Vendedor)usuarioActual.getPersona());


        }

        return null;
    }
    private boolean validarFormato(){

return !textFieldCategory.getText().isEmpty() && !textFieldName1.getText().isEmpty() && !textFieldLastName1.getText().isEmpty() && !textFieldCODE1.getText().isEmpty();

    }

    private void eliminarProducto() throws IOException {

        Producto producto = tablaProducts.getSelectionModel().getSelectedItem();
        if (producto != null ) {
            ((Vendedor) usuarioActual.getPersona()).getProductos().remove(producto);
        }
        limpiarDatos();
    }

    private void mostrarInformacion(Producto producto) {
        if (producto != null) {
            textFieldName1.setText(producto.getNombre());
            textFieldCategory.setText(producto.getCategoria());
            textFieldLastName1.setText(String.valueOf(producto.getPrecio()));
            pngcomment12.setImage(producto.getImagen());
            textFieldDescription.setText(producto.getDescripcion());


        }
    }

    private void limpiarDatos() {
        textFieldName1.setText("");
        textFieldDescription.setText("");
        textFieldLastName1.setText("");
        ChoiceBoxEstado.setValue(null);
    }

    private void actualizarProducto() throws IOException{
        verSeleccion();
        if (productos != null) {
            Producto productoActualizado = agregarProducto();
            if(productoSeleccionado != null) {
                actualizarLista(productoSeleccionado, productoActualizado);
            }
        }
    }

    private void actualizarLista(Producto productoActual, Producto productoActualizado) {
        int i = productos.indexOf(productoActual);
        productos.set(i, productoActualizado);
    }


}
