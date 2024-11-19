package com.edu.uniquindio.co.marketplace.clases.sockets.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ChatClient(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);

        // Start a thread to listen for incoming messages
        new Thread(() -> {
            try {
                String message;
                while ((message = receiveMessage()) != null) {
                    System.out.println("Mensaje recibido: " + message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public String receiveMessage() throws IOException {
        return reader.readLine();
    }

    public void close() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
}