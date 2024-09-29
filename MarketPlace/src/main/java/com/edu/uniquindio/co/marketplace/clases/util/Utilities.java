package com.edu.uniquindio.co.marketplace.clases.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class Utilities {
    public static Utilities instance;
    static ResourceBundle idioma = ResourceBundle.getBundle("MiRecurso/MiRecurso", new Locale("es", "CO"));


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



}
