package com.edu.uniquindio.co.marketplace.clases.util;

import com.edu.uniquindio.co.marketplace.clases.market.Comentario;
import com.edu.uniquindio.co.marketplace.clases.market.Producto;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Persistencia {
    static ResourceBundle rutas = ResourceBundle.getBundle("rutas");

    // Method to save data to a file
    public static void guardarDatos(String rutaArchivo, List<Producto> productos) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            borrarContenidoTXT(rutaArchivo);
            for(Producto producto: productos ){
                writer.write("Producto:");
                writer.newLine();
                writer.write("Nombre: " + producto.getNombre());
                writer.newLine();
                writer.write("Código: " + producto.getCodigo());
                writer.newLine();
                writer.write("Descripción: " + producto.getDescripcion());
                writer.newLine();
                writer.write("Imagen: " + producto.getImagen());
                writer.newLine();
                writer.write("Fecha de Publicación: " + producto.getFechaPublicacion());
                writer.newLine();
                writer.write("Categoría: " + producto.getCategoria());
                writer.newLine();
                writer.write("Likes: " + producto.getLikes());
                writer.newLine();
                writer.write("Precio: $" + producto.getPrecio());
                writer.newLine();
                writer.write("Estado: " + producto.getEstado());
                writer.newLine();
                writer.write("Vendedor: " + (producto.getVendedor() != null ? producto.getVendedor().getNombre() : "N/A"));
                writer.newLine();

                writer.write("Comentarios:");
                for (Comentario comentario : producto.getComentarios()) {
                    writer.newLine();
                    writer.write(" - " + comentario.getContenido());
                }
                writer.newLine();
                writer.write("--------------------------------------------------");
                writer.newLine();
            }


        } catch (IOException e) {
            System.err.println("Error al guardar el producto en el archivo de texto: " + e.getMessage());
        }
    }

    // Method to load data from a file
    public static List<Producto> cargarDatos(String rutaArchivo) throws IOException, ClassNotFoundException {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (List<Producto>) ois.readObject();
        }
    }
    public static void guardarContadorID() {
        String rutaArchivo = rutas.getString("carpetaArchivos") + "contadorVendedor.txt";
        try {
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs(); // Crear directorios si no existen
                archivo.createNewFile(); // Crear el archivo si no existe
            }
            try (FileWriter writer = new FileWriter(rutaArchivo, false)) {
                writer.write(String.valueOf(Vendedor.getContador()));
            }
        } catch (IOException e) {
            System.err.println("Error al guardar el contador en el archivo: " + e.getMessage());
        }
    }

    public static int leerContadorID() {
        String rutaArchivo = rutas.getString("carpetaArchivos") + "contadorVendedor.txt";
        try {
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs(); // Crear directorios si no existen
                archivo.createNewFile(); // Crear el archivo si no existe
                return 0; // Retornar valor por defecto si el archivo no existe
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
                String line = reader.readLine();
                return line != null ? Integer.parseInt(line) : 0;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el contador del archivo: " + e.getMessage());
            return 0; // Retornar valor por defecto en caso de error
        }
    }

    public static void guardarContadorProducto() {
        String rutaArchivo = rutas.getString("carpetaArchivos") + "contadorProducto.txt";
        try {
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs(); // Crear directorios si no existen
                archivo.createNewFile(); // Crear el archivo si no existe
            }
            try (FileWriter writer = new FileWriter(rutaArchivo, false)) {
                writer.write(String.valueOf(Producto.getContador()));
            }
        } catch (IOException e) {
            System.err.println("Error al guardar el contador en el archivo: " + e.getMessage());
        }
    }

    public static int leerContadorProducto() {
        String rutaArchivo = rutas.getString("carpetaArchivos") + "contadorProducto.txt";
        try {
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs(); // Crear directorios si no existen
                archivo.createNewFile(); // Crear el archivo si no existe
                return 0; // Retornar valor por defecto si el archivo no existe
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
                String line = reader.readLine();
                return line != null ? Integer.parseInt(line) : 0;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el contador del archivo: " + e.getMessage());
            return 0; // Retornar valor por defecto en caso de error
        }
    }

    public static void borrarContenidoTXT(String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo, false)) {
            writer.write("");
        } catch (IOException e) {
            System.err.println("Error al borrar el contenido del archivo: " + e.getMessage());
        }
    }
}