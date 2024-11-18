package com.edu.uniquindio.co.marketplace.clases.interfaces;

import com.edu.uniquindio.co.marketplace.clases.util.UsuarioActual;

import java.io.IOException;

public interface ObserverInicioSesion {
    void update(UsuarioActual persona) throws IOException;

}
