package com.edu.uniquindio.co.marketplace.clases.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.edu.uniquindio.co.marketplace.clases.ClienteMarketPlace;
import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.market.Producto;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PaginaProductosController {
    MarketPlace marketPlace = MarketPlace.getInstance();
    ArrayList <Producto> productos = new ArrayList<>();
    int numeroPaginas = 0;
    private int indicePaginaActual =0;
    @FXML
    private ResourceBundle resources;

    @FXML
    private VBox VBox1;

    @FXML
    private VBox Vbox2;

    @FXML
    private URL location;

    @FXML
    private Label GorraLabel;

    @FXML
    private Button buttonBackPage;

    @FXML
    private Button buttonBackProducts;

    @FXML
    private Button buttonGo;

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Button goToCreator;

    @FXML
    private ImageView gorrapng;

    @FXML
    private ImageView img;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView logopng;

    @FXML
    private Label nameLabel;

    @FXML
    private Label nameLabel1;

    @FXML
    private Button onButtonAddToCart;

    @FXML
    private Button onButtonAddToCart1;

    @FXML
    private Button onButtonAddToCart2;

    @FXML
    private Button onButtonSearch;

    @FXML
    private Button updatePage;

    @FXML
    private ImageView pngLove;

    @FXML
    private ImageView pngLove1;

    @FXML
    private ImageView pngLove11;

    @FXML
    private ImageView pngcomment;

    @FXML
    private ImageView pngcomment1;

    @FXML
    private ImageView pngcomment11;

    @FXML
    private Label priceLable;

    @FXML
    private Label priceLable1;

    @FXML
    private TextField textFieldBuscar;

    @FXML
    private Label textGetYourShopping;

    @FXML
    private Label textLanguage;

    @FXML
    private Label codigoPrincipal;

    @FXML
    private  Label categoriaLabel;

    @FXML
    private Label hourLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label likesLabel;
    @FXML
    private Label commentsLabel;

    @FXML
    private Label textMyAccount;

    @FXML
    private Label textQuickTrove;

    @FXML
    private Label textTrove;

    @FXML
    private Label codeProduct;

    @FXML
    private Label codeProduct2;

    @FXML
    void OnbuttonAddToCart(ActionEvent event) {

    }

    @FXML
    void OnbuttonAddToCart2(ActionEvent event) {

    }

    @FXML
    void onButtonBackPage(ActionEvent event) {
        if ((indicePaginaActual + 2) < productos.size()) {
            indicePaginaActual += 2;
            cargarProductos(indicePaginaActual);
            actualizarBotones();
        }

    }

    @FXML
    void onButtonBackProducts(ActionEvent event) {
        if (indicePaginaActual >= 2) {
            indicePaginaActual -= 2;
            cargarProductos(indicePaginaActual);
            actualizarBotones();
        }
    }

    @FXML
    void onButtonGoCreator(ActionEvent event) {

    }

    @FXML
    void onButtonSearch(ActionEvent event) {

    }

    @FXML
    void onMyAccount(MouseEvent event) throws IOException {
        ClienteMarketPlace.setRoot("/com/edu/uniquindio/co/marketplace/VendedorView.fxml");
    }

    @FXML
    void textFieldBuscar(ActionEvent event) {

    }

    @FXML
    void UpdatePage(ActionEvent event) {
        initialize();
    }
    @FXML
    void onCommentProduct(MouseEvent event) {

    }

    @FXML
    void onCommentProduct2(MouseEvent event) {

    }

    @FXML
    void onLikeProduct(MouseEvent event) {

    }

    @FXML
    void onLikeProduct2(MouseEvent event) {

    }



    @FXML
    void initialize() {
        Set<String> codigosProductos = new HashSet<>();
        for (Vendedor vendedor : marketPlace.getListaVendedores()) {
            for (Producto producto : vendedor.getProductos()) {
                if (!codigosProductos.contains(producto.getCodigo())) {
                    productos.add(producto);
                    codigosProductos.add(producto.getCodigo());
                }
            }
        }
        productos.sort(Comparator.comparing(Producto::getFechaPublicacion)); // Ordena los productos
        numeroPaginas = (int) Math.ceil(productos.size() / 2.0);

        cargarProductos(indicePaginaActual);
        actualizarBotones();
        System.out.println(productos);
    }

    private void cargarProductoPrincipal( Producto producto) {
        if (producto != null) {
            fruitNameLable.setText(producto.getNombre());
            gorrapng.setImage(producto.getImagen());
        }
        else{
            chosenFruitCard.setVisible(false);
            fruitNameLable.setText(producto.getNombre());
            GorraLabel.setText(String.valueOf(producto.getPrecio()));
            codigoPrincipal.setText(String.valueOf(producto.getCodigo()));
            dateLabel.setText(String.valueOf(producto.getFechaPublicacion()));
            categoriaLabel.setText(producto.getCategoria());
            likesLabel.setText(String.valueOf(producto.getLikes()));
            commentsLabel.setText(String.valueOf(producto.getComentarios().size()));
            gorrapng.setImage(producto.getImagen());
        }
    }

    private void actualizarBotones() {
        buttonBackPage.setDisable(indicePaginaActual == 0);
        buttonGo.setDisable(indicePaginaActual + 2 >= productos.size());
    }


    private void cargarProductos(int inicio) {
        // Verificar si hay productos
        if (productos.isEmpty()) {
            Vbox2.setVisible(false);
            VBox1.setVisible(false);
            return;
        }

        // Mostrar primer producto si existe
        if (inicio < productos.size()) {
            VBox1.setVisible(true);
            Producto producto1 = productos.get(inicio);
            nameLabel.setText(producto1.getNombre());
            priceLable.setText("$" + producto1.getPrecio());
            codeProduct.setText(producto1.getCodigo());
            img.setImage(producto1.getImagen());
        } else {
            VBox1.setVisible(false);
        }

        // Mostrar segundo producto si existe
        if (inicio + 1 < productos.size()) {
            Vbox2.setVisible(true);
            Producto producto2 = productos.get(inicio + 1);
            nameLabel1.setText(producto2.getNombre());
            priceLable1.setText("$" + producto2.getPrecio());
            codeProduct2.setText(producto2.getCodigo());
            img1.setImage(producto2.getImagen());
        } else {
            Vbox2.setVisible(false);
    }
    }
}
