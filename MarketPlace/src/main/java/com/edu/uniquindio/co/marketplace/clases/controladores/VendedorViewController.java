package com.edu.uniquindio.co.marketplace.clases.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.edu.uniquindio.co.marketplace.clases.ClienteMarketPlace;
import com.edu.uniquindio.co.marketplace.clases.market.*;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import com.edu.uniquindio.co.marketplace.clases.util.ImagenCargando;
import com.edu.uniquindio.co.marketplace.clases.util.ReporteExportacion;
import com.edu.uniquindio.co.marketplace.clases.util.UsuarioActual;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

public class VendedorViewController {


    MarketPlace marketPlace = MarketPlace.getInstance();
    Vendedor usuarioActual = (Vendedor) UsuarioActual.getInstance().getPersona();
    ImagenCargando imagenCargando = ImagenCargando.getInstance();
    private ObservableList<Comentario> listaComentarios;
    private Comentario comentario;
    private FilteredList<Comentario> filteredListComentarios;
    private boolean posicionChat = false;
    private ObservableList<Vendedor> listaAddContactos;
    private FilteredList<Vendedor> filteredListaAddContactos;
    private ObservableList<Vendedor> listaRateContactos;
    private FilteredList<Vendedor> filteredListaRateContactos;
    private ObservableList<Producto> listaPayProducts;
    private FilteredList<Producto> filteredListaPayProducts;
    private Producto productoSeleccionado;
    private Vendedor vendedorSeleccionadoAdd;
    private Solicitud solicitudSeleccionada;
    private Chat chat;
    private ObservableList<Solicitud> listaRequest;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label AddressSalespersonLabel;

    @FXML
    private Label BackgroundCartLabel;

    @FXML
    private Label BackgroundMessageIcon;

    @FXML
    private VBox BackgroundPartners;

    @FXML
    private Pane BackgroundSalesperson;

    @FXML
    private Label CODESalespersonLabel;

    @FXML
    private Label CalificacionLabel;

    @FXML
    private SVGPath CartLabel;

    @FXML
    private Label EmailSalespersonLabel;

    @FXML
    private HBox HboxButtomChangeChats;

    @FXML
    private Label IDSalespersonLabel;

    @FXML
    private ImageView ImagePartner1;

    @FXML
    private ImageView ImagePartner2;

    @FXML
    private ImageView ImagePartner3;

    @FXML
    private ImageView ImagePartner4;

    @FXML
    private ImageView ImagePartner5;

    @FXML
    private ImageView ImageProfilePartner;

    @FXML
    private ImageView ImageStartRate;

    @FXML
    private Label LastNameSalespersonLabel;

    @FXML
    private Text LikesLabel;

    @FXML
    private ImageView LoveIcon;

    @FXML
    private SVGPath MessageIcon;

    @FXML
    private Text MessagesLabel;

    @FXML
    private Label NamePartner1Label;

    @FXML
    private Label NamePartner2Label;

    @FXML
    private Label NamePartner3Label;

    @FXML
    private Label NamePartner4Label;

    @FXML
    private Label NamePartner5Label;

    @FXML
    private Label NamePartnerChat;

    @FXML
    private Label NameSalespersonLabel;

    @FXML
    private Button NotifyButton;

    @FXML
    private Text NumberLikes;

    @FXML
    private Text NumberMessages;

    @FXML
    private Text NumberSales;

    @FXML
    private Button OnButtonRate;

    @FXML
    private Button OnButtonSearch;

    @FXML
    private Pane PanePartner1;

    @FXML
    private Pane PanePartner2;

    @FXML
    private Pane PanePartner3;

    @FXML
    private Pane PanePartner4;

    @FXML
    private Pane PanePartner5;

    @FXML
    private Text RateLabel;

    @FXML
    private Tab Request;

    @FXML
    private Text SalesLabel;

    @FXML
    private Tab TabAddContact;

    @FXML
    private Tab TabEditProfileInformation;

    @FXML
    private TableView<Producto> TableCart;

    @FXML
    private TableView<Solicitud> TableCart1;

    @FXML
    private TableView<Vendedor> TableRate;


    @FXML
    private AnchorPane background;

    @FXML
    private Pane backgroundChat;

    @FXML
    private VBox body;

    @FXML
    private VBox body1;

    @FXML
    private VBox body11;

    @FXML
    private VBox body12;

    @FXML
    private VBox body121;

    @FXML
    private VBox chosenFruitCard1;

    @FXML
    private TableColumn<Comentario, String> columnCommentStatistics;

    @FXML
    private TableColumn<Vendedor, String> columnContactsInCommon;

    @FXML
    private TableColumn<Vendedor, String> columnLastName;

    @FXML
    private TableColumn<Producto, String> columnNameCart;

    @FXML
    private TableColumn<Solicitud, String> columnNameCart1;

    @FXML
    private TableColumn<Comentario, String> columnNameUserStatistics;

    @FXML
    private TableColumn<Producto, String> columnPriceCart;

    @FXML
    private TableView<Vendedor> tableContacts;

    @FXML
    private TableColumn<Vendedor, String> columnRate;

    @FXML
    private TableColumn<Producto, String> ColumnCategoryCart;

    @FXML
    private TableColumn<Producto, String> ColumnCodeCart;

    @FXML
    private TableColumn<Comentario, String> ColumnDateStatistics;

    @FXML
    private TableColumn<Vendedor, String> ColumnName;

    @FXML
    private TableColumn<Vendedor, String> ColumnNameRate;

    @FXML
    private TableColumn<Vendedor, String> ColumnRate;
    @FXML
    private TableView<Comentario> TableStatistics;

    @FXML
    private ScrollPane customTab;

    @FXML
    private ScrollPane customTab1;

    @FXML
    private ScrollPane customTab11;

    @FXML
    private ScrollPane customTab12;

    @FXML
    private ScrollPane customTab121;

    @FXML
    private CheckBox fiveStartCheckbox;

    @FXML
    private GridPane footer;

    @FXML
    private CheckBox fourStartCheckbox;

    @FXML
    private GridPane gridTiles;

    @FXML
    private Button onButtonAceppt;

    @FXML
    private Button onButtonAdd;

    @FXML
    private Button onButtonBack;

    @FXML
    private Button onButtonBuy;

    @FXML
    private Button onButtonChangeChat;

    @FXML
    private Button onButtonDelete;

    @FXML
    private Button onButtonPay;

    @FXML
    private Button onButtonSale;

    @FXML
    private Button onButtonSearch;

    @FXML
    private Button onButtonSearchCart;

    @FXML
    private Button onButtonSearchStatistics;

    @FXML
    private Button onButtonSend;

    @FXML
    private Button onButtonUptade;

    @FXML
    private Button onButtonUptadePage;

    @FXML
    private CheckBox oneStartCheckbox;

    @FXML
    private ImageView pngcomment12;

    @FXML
    private ImageView pngcomment121;

    @FXML
    private ImageView changeImageVendedor;

    @FXML
    private StackPane root;

    @FXML
    private StackPane root1;

    @FXML
    private StackPane root11;

    @FXML
    private StackPane root12;

    @FXML
    private StackPane root121;

    @FXML
    private TextField spaceChatMensaje;

    @FXML
    private Tab tabCart;

    @FXML
    private Tab tabChats;

    @FXML
    private Tab tabRate;

    @FXML
    private Tab tabStatistics;

    @FXML
    private Pane tableAddContact;

    @FXML
    private Label labelChat;


    @FXML
    private TextField textFieldAddress;

    @FXML
    private TextField textFieldBuscar;

    @FXML
    private TextField textFieldBuscarPorNombre;

    @FXML
    private TextField textFieldBuscarPorNombre2;

    @FXML
    private TextField textFieldBuscarPorNombreCart;

    @FXML
    private TextField textFieldCode;

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
    private CheckBox threeStartCheckbox;

    @FXML
    private CheckBox twoStartCheckbox;

    @FXML
    void OnButtonAdd(ActionEvent event) {
        Vendedor vendedor = vendedorSeleccionadoAdd;
        if (vendedor != null) {
            ClienteMarketPlace.enviarSolicitud(usuarioActual, vendedor);
        }
    }

    @FXML
    void OnButtonBack(ActionEvent event) throws IOException {
        ClienteMarketPlace.setRoot("/com/edu/uniquindio/co/marketplace/Productos1.fxml");

    }

    @FXML
    void OnButtonBuy(ActionEvent event) throws IOException {
        ClienteMarketPlace.setRoot("/com/edu/uniquindio/co/marketplace/Productos1.fxml");
    }

    @FXML
    void OnButtonDelete(ActionEvent event) {

    }

    @FXML
    void OnButtonPay(ActionEvent event) throws IOException {
        usuarioActual.comprarProducto(productoSeleccionado);
    }

    @FXML
    void OnButtonSale(ActionEvent event) throws IOException {
        ClienteMarketPlace.setRoot("/com/edu/uniquindio/co/marketplace/AddProduct.fxml");
    }

    @FXML
    public void onButtonSearch(ActionEvent actionEvent) {
        String consulta = textFieldBuscarPorNombre.getText().toLowerCase();
        filteredListaAddContactos.setPredicate(vendedor ->
                vendedor.getNombre().toLowerCase().contains(consulta));
    }

    @FXML
    void OnButtonSearch(ActionEvent event) {
        String consulta = textFieldBuscarPorNombre2.getText().toLowerCase();
        filteredListaRateContactos.setPredicate(vendedor ->
                vendedor.getNombre().toLowerCase().contains(consulta));
    }

    @FXML
    void OnButtonSearchCart(ActionEvent event) {
        String consulta = textFieldBuscarPorNombreCart.getText().toLowerCase();
        filteredListaPayProducts.setPredicate(producto ->
                producto.getNombre().toLowerCase().contains(consulta));
    }


    @FXML
    void OnButtonSearchStatistics(ActionEvent event) {
        String consulta = textFieldBuscar.getText().toLowerCase();
        filteredListComentarios.setPredicate(comentario ->
                comentario.getContenido().toLowerCase().contains(consulta));
    }

    @FXML
    void OnButtonSend(ActionEvent event) {
        String mensaje = spaceChatMensaje.getText();
        chat.setMensajes(chat.getMensajes() + "\n" + usuarioActual.getNombre() + ": " + mensaje);
        labelChat.setText(chat.getMensajes());
        ClienteMarketPlace.enviarMensaje(chat, chat.getMensajes());
    }

    @FXML
    void OnButtonUptade(ActionEvent event) {
        String nombre = textFieldName.getText();
        String apellido = textFieldLastName.getText();
        String contrasenia = textFieldPassword.getText();
        String email = textFieldEmailAddress.getText();
        String id = textFieldID.getText();
        String direccion = textFieldAddress.getText();

        usuarioActual.setNombre(nombre);
        usuarioActual.setApellido(apellido);
        usuarioActual.setContrasenia(contrasenia);
        usuarioActual.setCorreo(email);
        usuarioActual.setCedula(id);
        usuarioActual.setDireccion(direccion);
        cambiarAtributos();
    }
    @FXML
    private Button onButtonImprimirRecibo;



    @FXML
    void OnNotifyButton(ActionEvent event) {
        initialize();
    }

    @FXML
    void onButtonAceppt(ActionEvent event) {
        ClienteMarketPlace.aceptarSolicitud(solicitudSeleccionada);

    }

    @FXML
    void onButtonChangeChat(ActionEvent event) {
        if (posicionChat) {
            for (int i = 0; i < 5; i++) {
                if (i < usuarioActual.getContactos().size()) {
                    getNamePartnerLabel(i + 1).setText(usuarioActual.getContactos().get(i).getNombre());
                    getImagePartner(i + 1).setImage(usuarioActual.getContactos().get(i).getImagen());
                    getNamePartnerLabel(i + 1).setVisible(true);
                    getPanePartner(i + 1).setVisible(true);
                    getImagePartner(i + 1).setVisible(true);
                } else {
                    // Oculta los componentes visuales si no hay suficientes contactos
                    getNamePartnerLabel(i + 1).setVisible(false);
                    getPanePartner(i + 1).setVisible(false);
                    getImagePartner(i + 1).setVisible(false);
                }
            }
            NamePartnerChat.setText(usuarioActual.getContactos().get(0).getNombre());
            ImageProfilePartner.setImage(usuarioActual.getContactos().get(0).getImagen());
        } else {
            for (int i = 5; i < 10; i++) {
                int friendIndex = i;
                if (friendIndex < usuarioActual.getContactos().size()) {
                    getNamePartnerLabel(i - 4).setText(usuarioActual.getContactos().get(friendIndex).getNombre());
                    getImagePartner(i - 4).setImage(usuarioActual.getContactos().get(friendIndex).getImagen());
                    getNamePartnerLabel(i - 4).setVisible(true);
                    getPanePartner(i - 4).setVisible(true);
                    getImagePartner(i - 4).setVisible(true);
                } else {
                    getNamePartnerLabel(i - 4).setVisible(false);
                    getPanePartner(i - 4).setVisible(false);
                    getImagePartner(i - 4).setVisible(false);
                }
            }
            if (usuarioActual.getContactos().size() > 5) {
                NamePartnerChat.setText(usuarioActual.getContactos().get(5).getNombre());
                ImageProfilePartner.setImage(usuarioActual.getContactos().get(5).getImagen());
            }
        }
        posicionChat = !posicionChat;
    }

    @FXML
    void textFieldBuscar(ActionEvent event) {

    }

    @FXML
    void textFieldBuscarPorNombre(ActionEvent event) {

    }

    public void onChangeImageVendedor(MouseEvent mouseEvent) {
    }

    @FXML
    void initialize() {
        ArrayList<Vendedor> vendedores1 = new ArrayList<>();
        for (Vendedor vendedor : marketPlace.getListaVendedores()) {
            if (!usuarioActual.getContactos().contains(vendedor) && !vendedor.equals(usuarioActual)) {
                vendedores1.add(vendedor);
            }
        }
        listaComentarios = FXCollections.observableArrayList(usuarioActual.getListaComentarios());
        listaAddContactos = FXCollections.observableArrayList(vendedores1);
        listaRateContactos = FXCollections.observableArrayList(usuarioActual.getContactos());
        listaPayProducts = FXCollections.observableArrayList(usuarioActual.getCarrito());
        listaRequest = FXCollections.observableArrayList(usuarioActual.getSolicitudes());
        inicializarTablaComentarios();
        cambiarAtributos();
        iniciarChat();
        inicializarValores();
        incializarTablaAnContactos();
        inicializarRateContactos();
        inicializarCart();
        verSeleccionProducto();
        verSeleccionVendedorAdd();
        verSeleccionSolicitud();
        inicializarTablaRequest();
        labelChat.setText("Chat aquí\n" + "Cliente: Hola\n Vendedor: Hola, ¿en qué puedo ayudarte?");;
    }

    private void cambiarAtributos() {

        NameSalespersonLabel.setText(usuarioActual.getNombre());
        LastNameSalespersonLabel.setText(usuarioActual.getApellido());
        IDSalespersonLabel.setText(usuarioActual.getCedula());
        EmailSalespersonLabel.setText(usuarioActual.getCorreo());
        AddressSalespersonLabel.setText(usuarioActual.getDireccion());
        CODESalespersonLabel.setText(usuarioActual.getId());
        ImageProfilePartner.setImage(usuarioActual.getImagen());
        NumberLikes.setText(String.valueOf(usuarioActual.getLikes().size()));
        NumberSales.setText(String.valueOf(usuarioActual.getVentas().size()));
    }

    private void inicializarTablaComentarios() {
        ColumnDateStatistics.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        columnCommentStatistics.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContenido()));
        columnNameUserStatistics.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAutor().getNombre()));
        filteredListComentarios = new FilteredList<>(listaComentarios);
        TableStatistics.setItems(filteredListComentarios);
    }

    private void inicializarRateContactos() {
        ColumnNameRate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        ColumnRate.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getResenias())));
        filteredListaRateContactos = new FilteredList<>(listaRateContactos);
        TableRate.setItems(filteredListaRateContactos);
    }

    private void inicializarCart() {
        ColumnCodeCart.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        ColumnCategoryCart.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria()));
        columnNameCart.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnPriceCart.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
        filteredListaPayProducts = new FilteredList<>(listaPayProducts);
        TableCart.setItems(filteredListaPayProducts);
    }

    private void incializarTablaAnContactos() {
        ColumnName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columnLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        columnRate.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getResenias())));
        columnContactsInCommon.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNumeroContactosEnComun())));
        filteredListaAddContactos = new FilteredList<>(listaAddContactos);
        tableContacts.setItems(filteredListaAddContactos);
    }

    private void inicializarTablaRequest() {
        columnNameCart1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVendedor().getNombre()));
        TableCart1.setItems(listaRequest);
    }

    private void iniciarChat() {
        onButtonChangeChat.setVisible(usuarioActual.getContactos().size() > 5);


        if (usuarioActual.getContactos().isEmpty()) {
            for (int i = 1; i <= 5; i++) {
                getNamePartnerLabel(i).setVisible(false);
                getPanePartner(i).setVisible(false);
                getImagePartner(i).setVisible(false);
            }
            NamePartnerChat.setText("No tienes contactos");
            ImageProfilePartner.setVisible(false);
        } else {
            int contactosSize = usuarioActual.getContactos().size();
            for (int i = 0; i < 5; i++) {
                if (i < contactosSize) {
                    getNamePartnerLabel(i + 1).setText(usuarioActual.getContactos().get(i).getNombre());
                    getImagePartner(i + 1).setImage(usuarioActual.getContactos().get(i).getImagen());
                    getNamePartnerLabel(i + 1).setVisible(true);
                    getPanePartner(i + 1).setVisible(true);
                    getImagePartner(i + 1).setVisible(true);
                } else {
                    getNamePartnerLabel(i + 1).setVisible(false);
                    getPanePartner(i + 1).setVisible(false);
                    getImagePartner(i + 1).setVisible(false);
                }
            }
            NamePartnerChat.setText(usuarioActual.getContactos().get(0).getNombre());
            ImageProfilePartner.setImage(usuarioActual.getContactos().get(0).getImagen());
            chat = usuarioActual.getChats().get(0);
            cargarChat();
        }
    }

    private Label getNamePartnerLabel(int index) {
        return switch (index) {
            case 1 -> NamePartner1Label;
            case 2 -> NamePartner2Label;
            case 3 -> NamePartner3Label;
            case 4 -> NamePartner4Label;
            case 5 -> NamePartner5Label;
            default -> null;
        };
    }

    private Pane getPanePartner(int index) {
        return switch (index) {
            case 1 -> PanePartner1;
            case 2 -> PanePartner2;
            case 3 -> PanePartner3;
            case 4 -> PanePartner4;
            case 5 -> PanePartner5;
            default -> null;
        };
    }


    private ImageView getImagePartner(int index) {
        return switch (index) {
            case 1 -> ImagePartner1;
            case 2 -> ImagePartner2;
            case 3 -> ImagePartner3;
            case 4 -> ImagePartner4;
            case 5 -> ImagePartner5;
            default -> null;
        };
    }

    private void inicializarValores() {
        textFieldName.setText(usuarioActual.getNombre());
        textFieldLastName.setText(usuarioActual.getApellido());
        textFieldEmailAddress.setText(usuarioActual.getCorreo());
        textFieldID.setText(usuarioActual.getCedula());
        textFieldPassword.setText(usuarioActual.getContrasenia());
        textFieldAddress.setText(usuarioActual.getDireccion());
        CalificacionLabel.setText((usuarioActual.getResenias()) + "/5");
    }

    private void verSeleccionProducto() {
        TableCart.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
        });

    }

    private void verSeleccionVendedorAdd() {
        tableContacts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            vendedorSeleccionadoAdd = newSelection;
        });

    }

    private void verSeleccionSolicitud() {
        TableCart1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            solicitudSeleccionada = newSelection;
        });

    }

    @FXML
    public void onHboxChat1(MouseEvent mouseEvent) {
        if (!posicionChat) {
            chat = usuarioActual.getChats().get(0);
            cargarChat();
        } else {
            chat = usuarioActual.getChats().get(5);
            cargarChat();
        }
    }

    @FXML
    public void onHboxChat2(MouseEvent mouseEvent) {
        if (!posicionChat) {
            chat = usuarioActual.getChats().get(1);
            cargarChat();
        } else {
            chat = usuarioActual.getChats().get(6);
            cargarChat();
        }
    }

    @FXML
    public void onHboxChat3(MouseEvent mouseEvent) {
        if (!posicionChat) {
            chat = usuarioActual.getChats().get(2);
            cargarChat();
        } else {
            chat = usuarioActual.getChats().get(7);
            cargarChat();
        }
    }

    @FXML
    public void onHboxChat4(MouseEvent mouseEvent) {
        if (!posicionChat) {
            chat = usuarioActual.getChats().get(3);
            cargarChat();
        } else {
            chat = usuarioActual.getChats().get(8);
            cargarChat();
        }
    }

    @FXML
    public void onHboxChat5(MouseEvent mouseEvent) {
        if (!posicionChat) {
            chat = usuarioActual.getChats().get(4);
            cargarChat();
        } else {
            chat = usuarioActual.getChats().get(9);
            cargarChat();
        }
    }


    private void cargarChat() {
        if (chat != null) {
            labelChat.setText(chat.getMensajes());
        }
    }

    @FXML
    public void onButtonImprimirRecibo(ActionEvent actionEvent) {
        ReporteExportacion reporteExportacion = new ReporteExportacion();
        String ventas = usuarioActual.getVentas().toString();
        reporteExportacion.exportarReporte("Productos Vendidos",usuarioActual.getNombre(), ventas);
    }
}
