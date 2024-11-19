package com.edu.uniquindio.co.marketplace.clases.sockets;

import com.edu.uniquindio.co.marketplace.clases.App5;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import com.edu.uniquindio.co.marketplace.clases.sockets.chat.ChatClient;
import com.edu.uniquindio.co.marketplace.clases.util.UsuarioActual;
import javafx.scene.image.Image;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
public class MarketPlaceClient {
    private Socket cliente;
    private int puerto = 9000;
    private String ip = "localhost"; // Cambia esto a la IP del servidor si está en otra máquina
    private ChatClient chatClient;

    public void iniciar() {
        try {
            cliente = new Socket(ip, puerto);
            System.out.println("Conectado al servidor de MarketPlace.");

            ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

            // Enviar solicitud al servidor
            salida.writeObject("GET_CONTACTS");

            // Leer y mostrar la lista de contactos
            Object response = entrada.readObject();
            if (response instanceof List) {
                List<Vendedor> contactos = (List<Vendedor>) response;
                mostrarContactos(contactos);
            } else {
                System.out.println("Error: El servidor no devolvió una lista de contactos.");
            }

            UsuarioActual.getInstance(new Vendedor("Maria", "Perez", "123", "Calle 1", "MariaPerez@gmail.com", "123", (Image) null));

            // Iniciar la aplicación JavaFX en un hilo separado
            new Thread(() -> {
                App5.launch(App5.class);
            }).start();

            // Iniciar el cliente de chat
            chatClient = new ChatClient(cliente);
            chatClient.start();

            // Listen for incoming friend requests
            new Thread(() -> {
                try {
                    while (true) {
                        Object incomingRequest = entrada.readObject();
                        if (incomingRequest instanceof String && ((String) incomingRequest).startsWith("FRIEND_REQUEST:")) {
                            String friendUsername = ((String) incomingRequest).split(":")[1];
                            System.out.println("Friend request received from " + friendUsername);
                            // Implement logic to handle the friend request
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarContactos(List<Vendedor> contactos) {
        // Implementa la lógica para mostrar los contactos en la interfaz de usuario
        for (Vendedor contacto : contactos) {
            System.out.println("Contacto: " + contacto.getNombre());
            // Aquí puedes añadir el código para añadir los contactos a la tabla de la interfaz gráfica
        }
    }
    public void close() {
        try {
            if (chatClient != null) {
                chatClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFriendRequest(String friendUsername) {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
            salida.writeObject("FRIEND_REQUEST:" + friendUsername);
            System.out.println("Friend request sent to " + friendUsername);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void acceptFriendRequest(String friendUsername) {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
            salida.writeObject("ACCEPT_FRIEND_REQUEST:" + friendUsername);
            System.out.println("Friend request from " + friendUsername + " accepted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rejectFriendRequest(String friendUsername) {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
            salida.writeObject("REJECT_FRIEND_REQUEST:" + friendUsername);
            System.out.println("Friend request from " + friendUsername + " rejected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MarketPlaceClient client = new MarketPlaceClient();
        client.iniciar();

        // Add shutdown hook to close the client properly
        Runtime.getRuntime().addShutdownHook(new Thread(client::close));
    }
}