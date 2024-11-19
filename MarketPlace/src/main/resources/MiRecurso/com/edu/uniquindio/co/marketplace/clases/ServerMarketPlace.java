package com.edu.uniquindio.co.marketplace.clases;

import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.personas.Administrador;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import com.edu.uniquindio.co.marketplace.clases.util.Persistencia;
import javafx.scene.image.Image;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMarketPlace {
    private static final int PUERTO = 9000; // Puerto del servidor
    public static ArrayList<Socket> usuarios = new ArrayList<>(); // Lista de usuarios conectados
    public static ArrayList<ClienteHandler> clientes = new ArrayList<>(); // Lista de handlers
    private static MarketPlace  marketPlace = MarketPlace.getInstance();


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Desea iniciar el proyecto anterior?",
                "Iniciar Proyecto",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            marketPlace.iniciarDatos();
            System.out.println("Proyecto iniciado desde cero.");
        } else {
            System.out.println("No se inició un nuevo proyecto.");
        }
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(PUERTO);
                System.out.println("Servidor iniciado en el puerto " + PUERTO);

                while (true) {
                    Socket cliente = serverSocket.accept();
                    usuarios.add(cliente);

                    ClienteHandler handler = new ClienteHandler(cliente);
                    clientes.add(handler);
                    new Thread(handler).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static String inicioSesion(String correo, String contrasena) {
        for (Vendedor vendedor : marketPlace.getListaVendedores()) {
            if (vendedor.getCorreo().equals(correo) && vendedor.getContrasenia().equals(contrasena)) {
                return vendedor.getId();
            }
        }for (Administrador administrador : marketPlace.getListaAdministradores()) {
            if (administrador.getCorreo().equals(correo) && administrador.getContrasenia().equals(contrasena)) {
                return administrador.getCedula();
            }
        }
        return null;
    }

    public static void agregarVendedor(String nombre, String apellido, String id, String correo, String contrasena, String direccion) throws IOException {
        marketPlace.agregarVendedor(nombre, apellido, id, direccion, correo, contrasena, (Image) null);
        Vendedor vendedor = marketPlace.obtenerVendedorPorCodigo(String.valueOf(Persistencia.leerContadorID() -1));
        for(ClienteHandler cliente : clientes){
            cliente.enviarRegistro(vendedor);
        }
    }

    public static void agregarAdministrador(String nombre, String apellido, String id, String correo, String contrasena) throws IOException {
        marketPlace.agregarAdministrador(nombre, apellido, id, correo, contrasena, null);
    }

    public static void enviarSolicitud(String id, String id1) throws IOException {
        marketPlace.enviarSolicitudVendedores(id, id1);
        for (ClienteHandler cliente : clientes) {
            cliente.enviarSolicitud(id, id1);
        }
    }

    public static void aceptarSolicitud(String id, String id1) throws IOException {
        marketPlace.aceptarSolicitud(id, id1);
        for (ClienteHandler cliente : clientes) {
            cliente.enviarAceptacion(id, id1);
        }
    }

    public static void enviarMensaje(String id, String id1, String mensaje) throws IOException {
        marketPlace.enviarMensaje(id, id1,mensaje);
        for (ClienteHandler cliente : clientes){
            cliente.enviarMensaje(id, id1, mensaje);
        }

    }

    public static void anadirProducto(String nombre, String descripcion, String categoria, String precio, String estado, String id) {
        marketPlace.anadirProducto(nombre, descripcion, categoria, Double.parseDouble(precio), estado, id);
        for (ClienteHandler cliente : clientes) {
            cliente.enviarAnadirProducto(nombre, descripcion, categoria, precio, estado);
        }
    }
}
