package com.edu.uniquindio.co.marketplace.clases.personas;

import com.edu.uniquindio.co.marketplace.clases.enums.EstadoProducto;
import com.edu.uniquindio.co.marketplace.clases.interfaces.AdministrarProductosUsuario;
import com.edu.uniquindio.co.marketplace.clases.market.*;
import com.edu.uniquindio.co.marketplace.clases.util.Alerta;
import com.edu.uniquindio.co.marketplace.clases.util.Utilities;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Vendedor extends Persona implements AdministrarProductosUsuario {

    private String direccion;
    private String id;
    private  ArrayList<Comentario> listaComentarios;
    private  ArrayList<Vendedor> contactos;
    private static int contador;
    private  ArrayList<Producto> productos;
    private  ArrayList<Producto> likes;
    private ArrayList<Producto> carrito;
    private  ArrayList<Venta> ventas;
    private  ArrayList<Chat> chats;
    private ArrayList<Solicitud> solicitudes;
    private Muro muro;


    public Vendedor(String nombre, String apellido, String cedula, String direccion, String correo, String contrasenia, File file) {
        super(nombre, apellido, cedula, correo, contrasenia,file);
        this.direccion = direccion;
        id = String.valueOf(contador++);
        this.contactos = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.listaComentarios = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.chats = new ArrayList<>();
        this.carrito = new ArrayList<>();
        this.solicitudes = new ArrayList<>();
        muro = new Muro("", this);
    }

    public void agregarContacto(Vendedor contacto) throws IOException {
        if (contactos.contains(contacto)) {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElContactoYaEstaAgregado"));
        } else {
            if (contactos.size() >= 10) {
                Alerta.mostrarError(Utilities.getIdioma().getString("alertaNoPuedeAgregarMasDe10Contactos"));
                return;
            }
            contactos.add(contacto);
            new Chat(this, contacto);
        }
    }

    public void eliminarContacto(Vendedor contacto) throws IOException {
       if(contactos.contains(contacto)){
           contactos.remove(contacto);
         }else {
           Alerta.mostrarError(Utilities.getIdioma().getString("alertaElContactoNoExiste"));
       }
    }

    @Override
    public void agregarProducto(String nombre, String descripcion, String imagen, String categoria, double precio, EstadoProducto estado) {
        productos.add(new Producto(nombre, descripcion, imagen, categoria, precio, estado,this));

    }


    @Override
    public void eliminarProducto(Producto producto) throws IOException {
        if (productos.contains(producto)) {
            productos.remove(producto);
        } else {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElProductoNoExiste"));
        }
    }

    @Override
    public void modificarProducto(Producto producto, Producto nuevoProducto) {

        productos.set(productos.indexOf(producto), nuevoProducto);

    }

    public void agregarComentario(String comentario) {
        listaComentarios.add(new Comentario(comentario, this));
    }

    public void darLike(Producto producto) throws IOException {
        if (likes.contains(producto)) {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaYaLeDioLikeAEsteProducto"));
        } else {
            producto.darLike();
            likes.add(producto);
        }
    }

    public void agregarMensajeChat(Chat chat, String mensaje) {
        chat.enviarMensaje(mensaje);
    }

    public void comprarProducto(Producto producto) throws IOException {
        if(producto.getEstado().equals(EstadoProducto.VENDIDO)){
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElProductoYaFueVendido"));
            return;
        }
        producto.getVendedor().venderProducto(producto, this);
    }

    public void venderProducto(Producto producto, Vendedor comprador) throws IOException {
        if(contactos.contains(comprador)){
            producto.setEstado(EstadoProducto.VENDIDO);
            ventas.add(new Venta(producto, comprador));
        }else {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElCompradorNoEstaEnLaListaDeContactos"));
        }

    }

    public void eliminarComentario(Comentario comentario) throws IOException {
        if (listaComentarios.contains(comentario)) {
            listaComentarios.remove(comentario);
        } else {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElComentarioNoExiste"));
        }
    }

    public void agregarProductoAlCarrito(Producto producto) throws IOException {
        if (carrito.contains(producto)) {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElProductoYaEstaEnElCarrito"));
        } else {
            carrito.add(producto);
        }
    }

    public void eliminarProductoDelCarrito(Producto producto) throws IOException {
        if (carrito.contains(producto)) {
            carrito.remove(producto);
        } else {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElProductoNoEstaEnElCarrito"));
        }
    }

    public void enviarSolicitud(Vendedor vendedor) throws IOException {
        if (contactos.contains(vendedor)) {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaElContactoYaEstaEnLaListaDeContactos"));
        } else {
            for(Solicitud solicitud : vendedor.getSolicitudes()){
                if(solicitud.getSolicitado().equals(vendedor)){
                    Alerta.mostrarError(Utilities.getIdioma().getString("alertaYaEnvioUnaSolicitudAEsteVendedor"));
                    return;
                }
            }
            solicitudes.add(new Solicitud(this , vendedor));
        }
    }

    public void eliminarSolicitud(Solicitud solicitud) throws IOException {
        if (solicitudes.contains(solicitud)) {
            solicitudes.remove(solicitud);
        } else {
            Alerta.mostrarError(Utilities.getIdioma().getString("alertaLaSolicitudNoExiste"));
        }
    }
    //----------------------------Gets y Sets-------------------------------------
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Vendedor> getContactos() {
        return contactos;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public ArrayList<Producto> getLikes() {
        return likes;
    }

    public Muro getMuro() {
        return muro;
    }

    public void setMuro(Muro muro) {
        this.muro = muro;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Vendedor.contador = contador;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setListaComentarios(ArrayList<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public void setContactos(ArrayList<Vendedor> contactos) {
        this.contactos = contactos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public void setLikes(ArrayList<Producto> likes) {
        this.likes = likes;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    public ArrayList<Producto> getCarrito() {
        return carrito;
    }

    public void setCarrito(ArrayList<Producto> carrito) {
        this.carrito = carrito;
    }

    public ArrayList<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(ArrayList<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }
}
