package com.edu.uniquindio.co.marketplace.clases.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;


public class Logger {
    static ResourceBundle rutas = ResourceBundle.getBundle("rutas");
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void registrarIngresoUsuario(String nombreUsuario) {
        String entradaLog = LocalDateTime.now().format(formatter) + " - Usuario ingresado: " + nombreUsuario;
        escribirEnArchivo(entradaLog);
    }

    // registrar acciones realizadas por el usuario
    public static void registrarAccionUsuario(String nombreUsuario, String accion, String nombreInterfaz) {
        String entradaLog = LocalDateTime.now().format(formatter) + " - Usuario: " + nombreUsuario + " - Acción: " + accion + " - Interfaz: " + nombreInterfaz;
        escribirEnArchivo(entradaLog);
    }

    // registrar excepciones personalizadas
    public static void registrarExcepcion(String nombreUsuario, String mensajeExcepcion) {
        String entradaLog = LocalDateTime.now().format(formatter) + " - Usuario: " + nombreUsuario + " - Excepción: " + mensajeExcepcion;
        escribirEnArchivo(entradaLog);
    }

    // privado para escribir en el archivo de log
    private static void escribirEnArchivo(String entradaLog) {
        File archivo = new File(rutas.getString("RUTA_ARCHIVO_LOG"));
        // Asegurarse de que los directorios padres existan
        archivo.getParentFile().mkdirs();
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, true))) {
            escritor.write(entradaLog);
            escritor.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}