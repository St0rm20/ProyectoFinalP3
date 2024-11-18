package com.edu.uniquindio.co.marketplace.clases;

import java.io.*;

public class TestSerializacion {
    public static void main(String[] args) {
        String archivo = "test.ser";

        // Serialización
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
            salida.writeObject(new String("Prueba de serialización"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialización
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            String objeto = (String) entrada.readObject();
            System.out.println("Objeto deserializado: " + objeto);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
