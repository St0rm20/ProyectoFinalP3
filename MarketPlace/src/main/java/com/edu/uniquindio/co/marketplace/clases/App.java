package com.edu.uniquindio.co.marketplace.clases;

import com.edu.uniquindio.co.marketplace.clases.market.MarketPlace;
import java.io.IOException;


import com.edu.uniquindio.co.marketplace.clases.enums.EstadoProducto;

public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
       MarketPlace marketPlace = MarketPlace.getInstance();
       marketPlace.iniciarDatos();
        System.out.println(marketPlace.getListaVendedores().get(0));
    }

}