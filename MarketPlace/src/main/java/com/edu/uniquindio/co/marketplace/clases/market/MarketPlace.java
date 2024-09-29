package com.edu.uniquindio.co.marketplace.clases.market;

import com.edu.uniquindio.co.marketplace.clases.enums.EstadoProducto;
import com.edu.uniquindio.co.marketplace.clases.interfaces.AdministrarComentarios;
import com.edu.uniquindio.co.marketplace.clases.interfaces.AdministrarProductos;
import com.edu.uniquindio.co.marketplace.clases.interfaces.AdministrarVendedores;
import com.edu.uniquindio.co.marketplace.clases.interfaces.Estadistica;
import com.edu.uniquindio.co.marketplace.clases.personas.Administrador;
import com.edu.uniquindio.co.marketplace.clases.personas.Vendedor;
import com.edu.uniquindio.co.marketplace.clases.util.Alerta;
import com.edu.uniquindio.co.marketplace.clases.util.Utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

public class MarketPlace implements AdministrarVendedores, AdministrarProductos, AdministrarComentarios, Estadistica {
    private String nombre;
    private ArrayList<Vendedor> listaVendedores;
    private ArrayList<Administrador> listaAdministradores;
    private static MarketPlace instance;
    private String claveSeguridad;

    private MarketPlace() {
        this.nombre = "Quick Trove";
        this.claveSeguridad = "Admin123";
        this.listaVendedores = new ArrayList<>();
        this.listaAdministradores = new ArrayList<>();
    }

    public static MarketPlace getInstance() {
        if (instance == null) {
            instance = new MarketPlace();
        }
        return instance;
    }

    public boolean validarClave(String clave) {
        return clave.equals(claveSeguridad);
    }

    @Override
    public void agregarVendedor(String nombre, String apellido, String cedula, String direccion, String correo, String contrasenia, File imagen) throws IOException {
        Vendedor vendedor = new Vendedor(nombre, apellido, cedula, direccion, correo, contrasenia, imagen);
        if (!existeVendedor(vendedor)) {

            listaVendedores.add(vendedor);
            Alerta.mostrarMensajeConfirmacion(Utilities.getIdioma().getString("alertaUsuarioCreadoConExito"));
        }
    }


    @Override
    public void eliminarVendedor(Vendedor vendedor) throws IOException {
        if (listaVendedores.contains(vendedor)) {
            listaVendedores.remove(vendedor);
        } else {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElVendedorNoExiste"));
        }
    }

    @Override
    public void modificarVendedor(Vendedor vendedor, Vendedor vendedorModificado) {
        if (listaVendedores.contains(vendedor)) {
            listaVendedores.set(listaVendedores.indexOf(vendedor), vendedorModificado);
        }
    }

    public boolean existeVendedor(Vendedor vendedor) throws IOException {
        boolean existeVendedor = buscarVendedorPorCedula(vendedor.getCedula()).isPresent()
                || buscarVendedorPorCorreo(vendedor.getCorreo()).isPresent();
        if (existeVendedor) {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaYaExisteUnUsuarioConEstaInformacion"));
            return true;
        } else {
            return false;
        }
    }

    public Optional<Vendedor> buscarVendedorPorCedula(String cedula) {
        Predicate<Vendedor> condicion = vendedor -> vendedor.getCedula().equals(cedula);
        return listaVendedores.stream().filter(condicion).findAny();
    }

    public Optional<Vendedor> buscarVendedorPorCorreo(String correo) {
        Predicate<Vendedor> condicion = vendedor -> vendedor.getCorreo().equals(correo);
        return listaVendedores.stream().filter(condicion).findAny();
    }

    public void agregarAdministrador(String nombre, String apellido, String cedula, String correo, String contrasenia,String claveSeguridad, File imagen) throws IOException {
        Administrador administrador = new Administrador(nombre, apellido, cedula, correo, contrasenia, imagen);
        if (validarClave(claveSeguridad)) {
            if(!existeAdministrador(administrador)){
                listaAdministradores.add(administrador);
                Alerta.mostrarMensajeConfirmacion(Utilities.getIdioma().getString("alertaUsuarioCreadoConExito"));
            }
        } else {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaClaveIncorrecta"));
        }

    }

    public void eliminarAdministrador(Administrador administrador) throws IOException {
        if (listaAdministradores.contains(administrador)) {
            listaAdministradores.remove(administrador);
        } else {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElAdministradorNoExiste"));
        }
    }

    public boolean existeAdministrador(Administrador administrador) throws IOException {
        boolean existeAdministrador = buscarAdministradorPorCedula(administrador.getCedula()).isPresent()
                || buscarVendedorPorCorreo(administrador.getCorreo()).isPresent();
        if (existeAdministrador) {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaYaExisteUnAdministradorConEstaInformacion"));
            return true;
        } else {
            return false;
        }
    }

    public Optional<Administrador> buscarAdministradorPorCedula(String cedula) {
        Predicate<Administrador> condicion = administrador -> administrador.getCedula().equals(cedula);
        return listaAdministradores.stream().filter(condicion).findAny();
    }

    public Optional<Administrador> buscarAdministradorPorCorreo(String correo) {
        Predicate<Administrador> condicion = administrador -> administrador.getCorreo().equals(correo);
        return listaAdministradores.stream().filter(condicion).findAny();
    }

    @Override
    public void eliminarProducto(Producto producto) {
        for (Vendedor vendedor : listaVendedores) {
            vendedor.getProductos().remove(producto);
        }
    }

    @Override
    public ArrayList<Producto> obtenerProducto() {
        ArrayList<Producto> productos = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores) {
            productos.addAll(vendedor.getProductos());
        }
        return productos;
    }

    @Override
    public void eliminarComentario(Comentario comentario) {
        for (Vendedor vendedor : listaVendedores) {
            vendedor.getListaComentarios().remove(comentario);
        }
    }

    @Override
    public ArrayList<Comentario> obtenerComentarios() {
        ArrayList<Comentario> comentarios = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores) {
            comentarios.addAll(vendedor.getListaComentarios());
        }
        return comentarios;
    }

    //----------------------------Gets y Sets-------------------------------------

    public ArrayList<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public ArrayList<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
        this.listaVendedores = listaVendedores;
    }

    public void setListaAdministradores(ArrayList<Administrador> listaAdministradores) {
        this.listaAdministradores = listaAdministradores;
    }

    public static void setInstance(MarketPlace instance) {
        MarketPlace.instance = instance;
    }

    public String getClaveSeguridad() {
        return claveSeguridad;
    }

    public void setClaveSeguridad(String claveSeguridad) {
        this.claveSeguridad = claveSeguridad;
    }

    @Override
    public int obtenerCantidadMensajesEnviados(Vendedor vendedor, Vendedor vendedor2) {
        for(Chat chat: vendedor.getChats()){
            if(chat.getVendedor2().equals(vendedor2)||chat.getVendedor().equals(vendedor2)){
                {
                    return chat.getMensajes().size();
                }
            }
        }
        return 0;
    }

    @Override
    public int obtenerCantidadProductosPublicadosPorFecha(LocalDate fecha) {
        int cantidad = 0;
        for (Vendedor vendedor : listaVendedores) {
            for (Producto producto : vendedor.getProductos()) {
                if (producto.getFechaPublicacion().toLocalDate().equals(fecha)) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    @Override
    public int obtenerCantidadProductosPublicadosPorVendedor(Vendedor vendedor) {
        int contador = 0;
        for (Producto producto : vendedor.getProductos()) {
            if (producto.getEstado().equals(EstadoProducto.PUBLICADO)) {
                contador++;
            }
        }
        return contador;
    }

    @Override
    public int obtenerCantidadContactosVendedor(Vendedor vendedor) {
        if (vendedor.getContactos() != null) {
            return vendedor.getContactos().size();
        }
        return 0;
    }

    @Override
    public ArrayList<Producto> obtenerProductosMasGustados() {
        ArrayList<Producto> productos = new ArrayList<>();
        for (Vendedor vendedor : listaVendedores) {
            productos.addAll(vendedor.getProductos());
        }
        productos.sort((p1, p2) -> Integer.compare(p2.getLikes(), p1.getLikes()));
        return new ArrayList<>(productos.subList(0, Math.min(productos.size(), 10)));
    }
}
