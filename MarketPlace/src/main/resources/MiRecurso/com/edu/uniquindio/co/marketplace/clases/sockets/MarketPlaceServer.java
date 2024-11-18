package com.edu.uniquindio.co.marketplace.clases.sockets;

import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import javafx.scene.image.Image;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MarketPlaceServer {
    private ServerSocket server;
    private int puerto = 9000;
    private List<Vendedor> contactos = new ArrayList<>();

    public void iniciar() {
        try {
            server = new ServerSocket(puerto);
            System.out.println("Servidor iniciado en el puerto " + puerto);

            while (true) {
                Socket cliente = server.accept();
                new Thread(new ClienteHandler(cliente)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ClienteHandler implements Runnable {
        private Socket cliente;

        public ClienteHandler(Socket cliente) {
            this.cliente = cliente;
        }

        @Override
        public void run() {
            try (ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
                 ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream())) {

                String solicitud = (String) entrada.readObject();
                String respuesta = procesarSolicitud(solicitud);
                salida.writeObject(respuesta);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    cliente.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private String procesarSolicitud(String solicitud) {
            if (solicitud.equals("GET_PRODUCTS")) {
                return "Productos obtenidos";
            } else if (solicitud.startsWith("GET_PRODUCT_DETAILS")) {
                String productId = solicitud.split(":")[1];
                return "Detalles del Producto " + productId;
            }
            return "Solicitud no reconocida";
        }

        private void enviarListaContactos(ObjectOutputStream salida) throws IOException {
            MarketPlace marketPlace = MarketPlace.getInstance();
            List<Vendedor> listaVendedores = marketPlace.getListaVendedores();

            // Asegúrate de que Juan y Maria estén en la lista de contactos
            if (listaVendedores.stream().noneMatch(v -> v.getNombre().equals("Juan"))) {
                listaVendedores.add(new Vendedor("Juan", "Perez", "123", "Calle 1", "JuanPerez@gmail.com", "123", (Image) null));
            }
            if (listaVendedores.stream().noneMatch(v -> v.getNombre().equals("Maria"))) {
                listaVendedores.add(new Vendedor("Maria", "Perez", "123", "Calle 1", "MariaPerez@gmail.com", "123", (Image) null));
            }

            salida.writeObject(listaVendedores);
            salida.flush();
        }

    }

    public static void main(String[] args) throws IOException {
        MarketPlaceServer server = new MarketPlaceServer();
        server.iniciar();

        Vendedor vendedor = new Vendedor("Juan", "Perez", "123",
                "Calle 1", "juanperez@gmail.com", "123", (Image) null);
        server.contactos.add(vendedor);
        Vendedor vendedor2 = new Vendedor("Maria", "Perez", "1234",
                "Calle 1", "mariaperez@gmail.com", "1234", (Image) null);
        server.contactos.add(vendedor);

        // Crear y registrar un nuevo contacto
        Vendedor nuevoContacto = new Vendedor("Maria", "Lopez", "456",
                "Calle 2", "marialopez@gmail.com", "456", (Image) null);
        server.contactos.add(nuevoContacto);
    }


    private void aniadirContactos(){

    }
}