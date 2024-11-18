package com.edu.uniquindio.co.marketplace.clases.util;

import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import com.edu.uniquindio.co.marketplace.clases.personas.Persona;

public class UsuarioActual {

    private static UsuarioActual instance;
    private static Persona persona;
    private static String tipo;

    public UsuarioActual(Persona persona) {
        UsuarioActual.persona = persona;
    }

    public static UsuarioActual getInstance(Persona persona) {
        if (instance == null) {
            instance = new UsuarioActual(persona);
        }
        return instance;
    }

    public Persona getPersona() {
        return persona;
    }

    public static void setPersona(Persona persona) {
        UsuarioActual.persona = persona;
    }

    public static UsuarioActual getInstance() {
        return instance;
    }

    public static void setInstance(UsuarioActual instance) {
        UsuarioActual.instance = instance;
    }

    public static String getTipo() {
        return tipo;
    }

    public static void setTipo(String tipo) {
        UsuarioActual.tipo = tipo;
    }


}
