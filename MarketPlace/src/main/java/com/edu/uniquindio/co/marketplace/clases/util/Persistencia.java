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
        try (FileWriter writer = new FileWriter(rutas.getString("carpetaArchivos") + "contadorVendedor.txt", false)) {
            writer.write(String.valueOf(Vendedor.getContador()));
        } catch (IOException e) {
            System.err.println("Error al guardar el contador en el archivo: " + e.getMessage());
        }
    }

    public static int leerContadorID() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutas.getString("carpetaArchivos") + "contadorVendedor.txt"))) {
            String line = reader.readLine();
            return line != null ? Integer.parseInt(line) : 0;
        } catch (IOException e) {
            System.err.println("Error al leer el contador del archivo: " + e.getMessage());
            throw e;
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