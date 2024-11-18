package com.edu.uniquindio.co.marketplace.clases.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;

public class ReporteExportacion {

    public static void exportarReporte(String titulo, String usuario, String contenido) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaActual = LocalDateTime.now().format(formatter);


        StringBuilder reporte = new StringBuilder();
        reporte.append("<Titulo>").append(titulo).append("\n");
        reporte.append("<Fecha>").append("Fecha: ").append(fechaActual).append("\n");
        reporte.append("<Usuario>").append("Reporte realizado por: ").append(usuario).append("\n\n");
        reporte.append("Información del reporte:\n");
        reporte.append(contenido).append("\n");


        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar reporte");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();

            if (!archivo.getName().endsWith(".txt")) {
                archivo = new File(archivo.getAbsolutePath() + ".txt");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                writer.write(reporte.toString());
                System.out.println("Reporte exportado exitosamente en: " + archivo.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Error al guardar el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Exportación cancelada por el usuario.");
        }
    }


}
