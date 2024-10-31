package com.edu.uniquindio.co.marketplace.clases.util;

import java.beans.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Utilities {
    public static Utilities instance;
    static ResourceBundle idioma = ResourceBundle.getBundle("MiRecurso/MiRecurso", new Locale("es", "CO"));
    static ResourceBundle rutas = ResourceBundle.getBundle("rutas");

    public static Utilities getInstance() {
        if (instance == null) {
            instance = new Utilities();
        }
        return instance;
    }

    public static ResourceBundle getIdioma() {
        return idioma;
    }

    public void cambiarIdioma() {
        if (idioma.getLocale().getLanguage().equals("es")) {
            idioma = ResourceBundle.getBundle("MiRecurso/MiRecurso", new Locale("en", "UK"));
        } else {
            idioma = ResourceBundle.getBundle("MiRecurso/MiRecurso", new Locale("es", "CO"));
        }
    }

    public static void serializarObjetoBinario(String nombreArchivo, Object objeto) throws IOException {
        ObjectOutputStream salida;
        salida = new ObjectOutputStream(new FileOutputStream(rutas.getString("carpetaPrincipal") + nombreArchivo));
        salida.writeObject(objeto);
        salida.close();
    }
    public static Object deserializarObjetoBinario(String nombreArchivo) throws IOException, ClassNotFoundException {
        Object objeto;
        ObjectInputStream entrada;
        entrada = new ObjectInputStream(new FileInputStream(nombreArchivo));
        objeto = entrada.readObject();
        entrada.close();
        return objeto;
    }

    public static void serializarObjetoXML(String nombreArchivo, Object objeto) throws IOException {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(rutas.getString("carpetaPrincipal") + nombreArchivo)))) {

            encoder.setPersistenceDelegate(LocalDate.class, new DefaultPersistenceDelegate() {
                @Override
                protected Expression instantiate(Object obj, Encoder enc) {
                    LocalDate localDate = (LocalDate) obj;
                    return new Expression(localDate, LocalDate.class, "of",
                            new Object[]{localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()});
                }
            });

            encoder.setPersistenceDelegate(LocalDateTime.class, new DefaultPersistenceDelegate() {
                @Override
                protected Expression instantiate(Object obj, Encoder enc) {
                    LocalDateTime localDateTime = (LocalDateTime) obj;
                    return new Expression(localDateTime, LocalDateTime.class, "of",
                            new Object[]{localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth(),
                                    localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond()});
                }
            });

            encoder.writeObject(objeto);
        }
    }

    public static Object deserializarObjetoXML(String nombreArchivo) throws IOException {
        XMLDecoder decodificador;
        Object objeto;
        decodificador = new XMLDecoder(new FileInputStream(nombreArchivo));
        objeto = decodificador.readObject();
        decodificador.close();
        return objeto;
    }

    public static void escribirArchivo(String rutaArchivo, List<String> lineas) throws IOException {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String linea : lineas) {
                escritor.write(linea);
                escritor.newLine();
            }
        }
    }

    public static List<String> leerArchivo(String rutaArchivo) throws IOException {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
    }


    public static void agregarArchivo(String rutaArchivo, String linea) throws IOException {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            escritor.write(linea);
            escritor.newLine();
        }
    }

    public static void crearArchivoSiNoExiste(String rutaArchivo) throws IOException {
        File archivo = new File(rutaArchivo);
        File parentDir = archivo.getParentFile();
        if (parentDir != null) {
            parentDir.mkdirs();
        }
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
    }

    public static void crearCopiaRespaldoXML (Object objeto) throws IOException {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy_HH_mm_ss");
        String formattedDateTime = now.format(formatter);

        serializarObjetoXML( "respaldo/vendedores_" + formattedDateTime + ".xml", objeto);
    }

    public static Properties cargarPropiedades(String rutaPropiedades) throws IOException {
        Properties propiedades = new Properties();
        try (FileInputStream input = new FileInputStream(rutaPropiedades)) {
            propiedades.load(input);
        }
        return propiedades;
    }

}