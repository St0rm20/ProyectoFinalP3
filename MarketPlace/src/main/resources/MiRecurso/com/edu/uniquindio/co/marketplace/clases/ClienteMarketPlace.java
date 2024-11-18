package com.edu.uniquindio.co.marketplace.clases;

import com.edu.uniquindio.co.marketplace.clases.interfaces.ObserverInicioSesion;
import com.edu.uniquindio.co.marketplace.clases.interfaces.ObserverRegistro;
import com.edu.uniquindio.co.marketplace.clases.market.Chat;
import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.market.Solicitud;
import com.edu.uniquindio.co.marketplace.clases.personas.Administrador;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import com.edu.uniquindio.co.marketplace.clases.util.UsuarioActual;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClienteMarketPlace extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/com/edu/uniquindio/co/marketplace/Principal.fxml"));
        stage.setTitle("Quick Trove");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        Stage stage = (Stage) scene.getWindow();
        stage.setWidth(600);
        stage.setHeight(400);
    }

    public static void setRoot(String fxml, double width, double height) throws IOException {
        scene.setRoot(loadFXML(fxml));
        Stage stage = (Stage) scene.getWindow();
        stage.setWidth(width);
        stage.setHeight(height);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClienteMarketPlace.class.getResource(fxml));
        return fxmlLoader.load();
    }

    //------------------------------t--- Sockets ----------------------------------\\
    private static final String HOST = "localhost";
    private static final int PUERTO = 9000;
    private static Socket socket;
    private static DataOutputStream out;
    private static DataInputStream in;
    private static ObjectOutputStream objectOut;
    private static ObjectInputStream objectIn;
    private static ObserverInicioSesion observerInicioSesion;
    private UsuarioActual usuarioActual;
    static MarketPlace marketPlace;
    static UsuarioActual usuario;
    static ObserverInicioSesion observer;
    static ObserverRegistro observerRegistro;
    static Vendedor vendedor;
    static Administrador administrador;
    static Solicitud solicitud;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                socket = new Socket(HOST, PUERTO);
                System.out.println("Conexión establecida con el servidor.");

                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                objectOut = new ObjectOutputStream(socket.getOutputStream());
                objectIn = new ObjectInputStream(socket.getInputStream());
                marketPlace = (MarketPlace) objectIn.readObject();
                MarketPlace.setInstance(marketPlace);
                marketPlace.setGuardarPersistencia(false);

                while (true) {
                    try {
                        System.out.println("Esperando mensaje...");
                        String mensaje = in.readUTF();
                        System.out.println(mensaje);

                        descifrarMensaje(mensaje);

                        System.out.println("in liberado");
                    } catch (EOFException e) {
                        System.err.println("Error en la conexión con el servidor: " + e.getMessage());
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error en la conexión con el servidor:");
                e.printStackTrace();
            }
        }).start();

        launch();
    }

    public static void enviarInicioSesion(String correo, String contrasena) {
        try {
            String mensaje = "HYM:inicioSesion#" + correo + "#" + contrasena;
            System.out.println("Mensaje enviado: " + mensaje);
            out.writeUTF(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void enviarRegistro(String name, String lastName, String id, String email, String password, String address) {
        try {//name, lastName, id, email, password, address
            //String nombre, String apellido, String cedula, String direccion, String correo, String contrasenia, Image imagen
            String mensaje = "HYM:registro#" + name + "#" + lastName + "#" + id + "#" + email + "#" + password + "#" + address;
            vendedor = new Vendedor(name, lastName, id, address, email, password, (Image) null);
            out.writeUTF(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void enviarSolicitud(Vendedor vendedor, Vendedor vendedor1) {
        try {
            String mensaje = "HYM:solicitud#" + vendedor.getId() + "#" + vendedor1.getId();
            solicitud = new Solicitud(vendedor, vendedor1);
            out.writeUTF(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void aceptarSolicitud(Solicitud solicitud) {
        try {
            String mensaje = "HYM:aceptarSolicitud#" + solicitud.getVendedor().getId() + "#" + solicitud.getSolicitado().getId();
            out.writeUTF(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void enviarRegistroAdmin(String name, String lastName, String id, String email, String password) {
        try {
            String mensaje = "HYM:Adminregistro#" + name + "#" + lastName + "#" + id + "#" + email + "#" + password;
            administrador = new Administrador(name, lastName, id, email, password, (Image) null);
            out.writeUTF(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void enviarMensaje(Chat chat, String mensaje) {
        try {
            String mensajeEnviar = "HYM:enviarMensaje#" + chat.getVendedor().getId() + "#" + chat.getVendedor2().getId() + "#" + mensaje;
            out.writeUTF(mensajeEnviar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void anadirProducto(String nombre, String descripcion, String categoria, double precio, String estado, Vendedor vendedor) {
        try {
            String mensaje = "HYM:anadirProducto#" + nombre + "#" + descripcion + "#" + categoria + "#" + precio + "#" + estado + "#" + vendedor.getId();
            out.writeUTF(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void descifrarMensaje(String mensaje) throws IOException {
        System.out.println("Mensaje recibido: " + mensaje);
        try {
            if (mensaje.startsWith("HYM:inicioSesion#")) {
                String[] partes = mensaje.split("#");
                if (partes.length >= 2) {
                    String codigo = partes[1];
                    System.out.println("Inicio de sesion en: " + codigo);
                    System.out.println(marketPlace.getListaVendedores());
                    if (codigo != null) {
                        Vendedor vendedor = marketPlace.obtenerVendedorPorCodigo(codigo);
                        System.out.println("Vendedor iniciado: " + vendedor);
                        if (vendedor != null) {
                            System.out.println("Vendedor: " + vendedor);
                            UsuarioActual.setPersona(vendedor);
                            usuario = UsuarioActual.getInstance(vendedor);
                            UsuarioActual.setTipo("Cliente");
                            if (observerInicioSesion != null) {
                                observerInicioSesion.update(usuario);
                            } else {
                                System.err.println("observerInicioSesion is not set.");
                            }
                            return;
                        }
                        Administrador administrador = marketPlace.obtenerAdministradorPorCodigo(codigo);
                        if (administrador != null) {
                            System.out.println("Administrador: " + administrador);
                            usuario = UsuarioActual.getInstance(administrador);
                            UsuarioActual.setTipo("Administrador");
                            if (observerInicioSesion != null) {
                                observerInicioSesion.update(usuario);
                            } else {
                                System.err.println("observerInicioSesion is not set.");
                            }
                        }
                    }
                }
            } else if (mensaje.startsWith("HYM:registro#")) {
                System.out.println("Inicia el registro");
                String[] partes = mensaje.split("#");
                System.out.println("Partes: " + partes[1]);
                System.out.println("Partes: " + partes[1] + " " + partes[2] + " " + partes[3] + " " + partes[4] + " " + partes[5] + " " + partes[6]);
                marketPlace.agregarVendedor(partes[1], partes[2], partes[7], partes[6], partes[4], partes[5], partes[3]);
                System.out.println(marketPlace.getListaVendedores());
                observerRegistro.update();
                System.out.println("Registro exitoso");
                return;

            } else if (mensaje.startsWith("HYM:Adminregistro#")) {
                observerRegistro.update();
                marketPlace.agregarAdministrador(administrador.getNombre(), administrador.getApellido(), administrador.getCedula(), administrador.getCorreo(), administrador.getContrasenia(), null);

            } else if (mensaje.startsWith("HYM:solicitud#")) {
                String[] partes = mensaje.split("#");
                String id = partes[1];
                String id1 = partes[2];
                marketPlace.enviarSolicitudVendedores(id, id1);

            } else if (mensaje.startsWith("HYM:aceptarSolicitud#")) {
                //   out.writeUTF("HYM:aceptarSolicitud#" + id + "#" + id1);
                String[] partes = mensaje.split("#");
                String id0 = partes[1];
                String id1 = partes[2];
                marketPlace.aceptarSolicitud(id0, id1);
            } else if (mensaje.startsWith("HYM:enviarMensaje#")) {
                //out.writeUTF("HYM:enviarMensaje#"+ id + "#"+ id1 + "#" + mensaje);
                String[] partes = mensaje.split("#");
                String id = partes[1];
                String id1 = partes[2];
                String mensaje1 = partes[3];
                marketPlace.enviarMensaje(id, id1, mensaje1);
            } else if (mensaje.startsWith("HYM:anadirProducto#")) {
                String[] partes = mensaje.split("#");
                if (partes.length >= 6) {
                    String nombre = partes[1];
                    String descripcion = partes[2];
                    String categoria = partes[3];
                    double precio = Double.parseDouble(partes[4]);
                    String estado = partes[5];
                    String id = partes[6];
                    System.out.println("Nombre: " + nombre + " Descripción: " + descripcion + " Categoría: " + categoria + " Precio: " + precio + " Estado: " + estado + " ID: " + id);
                    marketPlace.anadirProducto(nombre, descripcion, categoria, precio, estado, id);
                    // out.writeUTF("HYM:anadirProducto#" + nombre + "#" + descripcion + "#" + categoria + "#" + precio + "#" + estado);


                } else {
                    System.out.println("No se reconoce el comando: " + mensaje);

                }
            }
        } finally {
            System.out.println("Mensaje descifrado");
        }
    }


    public static void setObserverInicioSesion(ObserverInicioSesion observerInicioSesion) {
        ClienteMarketPlace.observerInicioSesion = observerInicioSesion;
    }

    public static void setObserverRegistro(ObserverRegistro observerRegistro) {
        ClienteMarketPlace.observerRegistro = observerRegistro;
    }
}

