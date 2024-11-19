package com.edu.uniquindio.co.marketplace.clases.interfaces;

import com.edu.uniquindio.co.marketplace.clases.market.Comentario;
import java.util.ArrayList;

public interface AdministrarComentarios {
    void eliminarComentario(Comentario comentario);
    ArrayList<Comentario> obtenerComentarios();

}
